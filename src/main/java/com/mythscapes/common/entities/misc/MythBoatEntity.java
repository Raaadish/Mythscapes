package com.mythscapes.common.entities.misc;

import com.mythscapes.register.MythBlocks;
import com.mythscapes.register.MythItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.client.CSteerBoatPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

//
//  COPY PASTA FROM BoatEntity.java
//
public class MythBoatEntity extends BoatEntity {

    public enum Type {
        WOLT(MythBlocks.WOLT_PLANKS.get(), "wolt");
        //VIRIDIAN(MythBlocks.VIRIDIAN_PLANKS.get(), "viridian");

        private final String name;
        private final Block block;

        Type(Block block, String name) {
            this.name = name;
            this.block = block;
        }

        public String getName() {
            return this.name;
        }

        public Block asPlank() {
            return this.block;
        }

        public String toString() {
            return this.name;
        }

        /**
         * Get a boat type by it's enum ordinal
         *
         * @return
         */
        public static MythBoatEntity.Type byId(int id) {
            MythBoatEntity.Type[] types = values();
            if (id < 0 || id >= types.length) {
                id = 0;
            }
            return types[id];
        }

        public static MythBoatEntity.Type getTypeFromString(String name) {
            MythBoatEntity.Type[] types = values();

            for (int i = 0; i < types.length; ++i) {
                if (types[i].getName().equals(name)) {
                    return types[i];
                }
            }
            return types[0];
        }
    }

    public Item getItemBoat() {
        return MythItems.WOLT_BOAT.get();

        /*
        switch(this.getMythBoatType()) {
            case WOLT:
            default:
                return MythItems.WOLT_BOAT.get();
            case VIRIDIAN:
                return MythItems.VIRIDIAN_BOAT.get();
        }
         */
    }

    private static final DataParameter<Integer> TIME_SINCE_HIT;
    private static final DataParameter<Integer> FORWARD_DIRECTION;
    private static final DataParameter<Float> DAMAGE_TAKEN;
    private static final DataParameter<Integer> BOAT_TYPE;
    private static final DataParameter<Boolean> LEFT_PADDLE;
    private static final DataParameter<Boolean> RIGHT_PADDLE;
    private static final DataParameter<Integer> ROCKING_TICKS;
    private final float[] paddlePositions;
    private float momentum;
    private float outOfControlTicks;
    private float deltaRotation;
    private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private double lerpYaw;
    private double lerpPitch;
    private boolean leftInputDown;
    private boolean rightInputDown;
    private boolean forwardInputDown;
    private boolean backInputDown;
    private double waterLevel;
    private float boatGlide;
    private BoatEntity.Status status;
    private BoatEntity.Status previousStatus;
    private double lastYd;
    private boolean rocking;
    private boolean downwards;
    private float rockingIntensity;
    private float rockingAngle;
    private float prevRockingAngle;

    public MythBoatEntity(EntityType<? extends BoatEntity> type, World world) {
        super(type, world);
        this.paddlePositions = new float[2];
        this.preventEntitySpawning = true;
    }

    public MythBoatEntity(World world, double x, double y, double z) {
        this(EntityType.BOAT, world);
        this.setPosition(x, y, z);
        this.setMotion(Vector3d.ZERO);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    protected float getEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height;
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected void registerData() {
        this.dataManager.register(TIME_SINCE_HIT, 0);
        this.dataManager.register(FORWARD_DIRECTION, 1);
        this.dataManager.register(DAMAGE_TAKEN, 0.0F);
        this.dataManager.register(BOAT_TYPE, BoatEntity.Type.OAK.ordinal());
        this.dataManager.register(LEFT_PADDLE, false);
        this.dataManager.register(RIGHT_PADDLE, false);
        this.dataManager.register(ROCKING_TICKS, 0);
    }

    public boolean canCollide(Entity entity) {
        return func_242378_a(this, entity);
    }

    public static boolean func_242378_a(Entity passenger, Entity entity) {
        return (entity.func_241845_aY() || entity.canBePushed()) && !passenger.isRidingSameEntity(entity);
    }

    public boolean func_241845_aY() {
        return true;
    }

    public boolean canBePushed() {
        return true;
    }

    protected Vector3d func_241839_a(Direction.Axis axis, TeleportationRepositioner.Result result) {
        return LivingEntity.func_242288_h(super.func_241839_a(axis, result));
    }

    public double getMountedYOffset() {
        return -0.1D;
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (!this.world.isRemote && !this.removed) {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
            this.markVelocityChanged();
            boolean flag = source.getTrueSource() instanceof PlayerEntity && ((PlayerEntity) source.getTrueSource()).abilities.isCreativeMode;
            if (flag || this.getDamageTaken() > 40.0F) {
                if (!flag && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                    this.entityDropItem(this.getItemBoat());
                }

                this.remove();
            }

            return true;
        } else {
            return true;
        }
    }

    public void onEnterBubbleColumnWithAirAbove(boolean downwards) {
        if (!this.world.isRemote) {
            this.rocking = true;
            this.downwards = downwards;
            if (this.getRockingTicks() == 0) {
                this.setRockingTicks(60);
            }
        }

        this.world.addParticle(ParticleTypes.SPLASH, this.getPosX() + (double) this.rand.nextFloat(), this.getPosY() + 0.7D, this.getPosZ() + (double) this.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
        if (this.rand.nextInt(20) == 0) {
            this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), this.getSplashSound(), this.getSoundCategory(), 1.0F, 0.8F + 0.4F * this.rand.nextFloat(), false);
        }

    }

    public void applyEntityCollision(Entity entityIn) {
        if (entityIn instanceof BoatEntity) {
            if (entityIn.getBoundingBox().minY < this.getBoundingBox().maxY) {
                super.applyEntityCollision(entityIn);
            }
        } else if (entityIn.getBoundingBox().minY <= this.getBoundingBox().minY) {
            super.applyEntityCollision(entityIn);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public void performHurtAnimation() {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0F);
    }

    public boolean canBeCollidedWith() {
        return !this.removed;
    }

    @OnlyIn(Dist.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.lerpX = x;
        this.lerpY = y;
        this.lerpZ = z;
        this.lerpYaw = (double) yaw;
        this.lerpPitch = (double) pitch;
        this.lerpSteps = 10;
    }

    public Direction getAdjustedHorizontalFacing() {
        return this.getHorizontalFacing().rotateY();
    }

    public void tick() {
        this.previousStatus = this.status;
        this.status = this.getBoatStatus();
        if (this.status != BoatEntity.Status.UNDER_WATER && this.status != BoatEntity.Status.UNDER_FLOWING_WATER) {
            this.outOfControlTicks = 0.0F;
        } else {
            ++this.outOfControlTicks;
        }

        if (!this.world.isRemote && this.outOfControlTicks >= 60.0F) {
            this.removePassengers();
        }

        if (this.getTimeSinceHit() > 0) {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }

        if (this.getDamageTaken() > 0.0F) {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }

        super.tick();
        this.tickLerp();
        if (this.canPassengerSteer()) {
            if (this.getPassengers().isEmpty() || !(this.getPassengers().get(0) instanceof PlayerEntity)) {
                this.setPaddleState(false, false);
            }

            this.updateMotion();
            if (this.world.isRemote) {
                this.controlBoat();
                this.world.sendPacketToServer(new CSteerBoatPacket(this.getPaddleState(0), this.getPaddleState(1)));
            }

            this.move(MoverType.SELF, this.getMotion());
        } else {
            this.setMotion(Vector3d.ZERO);
        }

        this.updateRocking();

        for (int i = 0; i <= 1; ++i) {
            if (this.getPaddleState(i)) {
                if (!this.isSilent() && (double) (this.paddlePositions[i] % 6.2831855F) <= 0.7853981852531433D && ((double) this.paddlePositions[i] + 0.39269909262657166D) % 6.2831854820251465D >= 0.7853981852531433D) {
                    SoundEvent soundevent = this.getPaddleSound();
                    if (soundevent != null) {
                        Vector3d vector3d = this.getLook(1.0F);
                        double d0 = i == 1 ? -vector3d.z : vector3d.z;
                        double d1 = i == 1 ? vector3d.x : -vector3d.x;
                        this.world.playSound((PlayerEntity) null, this.getPosX() + d0, this.getPosY(), this.getPosZ() + d1, soundevent, this.getSoundCategory(), 1.0F, 0.8F + 0.4F * this.rand.nextFloat());
                    }
                }

                this.paddlePositions[i] = (float) ((double) this.paddlePositions[i] + 0.39269909262657166D);
            } else {
                this.paddlePositions[i] = 0.0F;
            }
        }

        this.doBlockCollisions();
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), EntityPredicates.pushableBy(this));
        if (!list.isEmpty()) {
            boolean flag = !this.world.isRemote && !(this.getControllingPassenger() instanceof PlayerEntity);

            for (int j = 0; j < list.size(); ++j) {
                Entity entity = (Entity) list.get(j);
                if (!entity.isPassenger(this)) {
                    if (flag && this.getPassengers().size() < 2 && !entity.isPassenger() && entity.getWidth() < this.getWidth() && entity instanceof LivingEntity && !(entity instanceof WaterMobEntity) && !(entity instanceof PlayerEntity)) {
                        entity.startRiding(this);
                    } else {
                        this.applyEntityCollision(entity);
                    }
                }
            }
        }

    }

    private void updateRocking() {
        int k;
        if (this.world.isRemote) {
            k = this.getRockingTicks();
            if (k > 0) {
                this.rockingIntensity += 0.05F;
            } else {
                this.rockingIntensity -= 0.1F;
            }

            this.rockingIntensity = MathHelper.clamp(this.rockingIntensity, 0.0F, 1.0F);
            this.prevRockingAngle = this.rockingAngle;
            this.rockingAngle = 10.0F * (float) Math.sin((double) (0.5F * (float) this.world.getGameTime())) * this.rockingIntensity;
        } else {
            if (!this.rocking) {
                this.setRockingTicks(0);
            }

            k = this.getRockingTicks();
            if (k > 0) {
                --k;
                this.setRockingTicks(k);
                int j = 60 - k - 1;
                if (j > 0 && k == 0) {
                    this.setRockingTicks(0);
                    Vector3d vector3d = this.getMotion();
                    if (this.downwards) {
                        this.setMotion(vector3d.add(0.0D, -0.7D, 0.0D));
                        this.removePassengers();
                    } else {
                        this.setMotion(vector3d.x, this.isPassenger(PlayerEntity.class) ? 2.7D : 0.6D, vector3d.z);
                    }
                }

                this.rocking = false;
            }
        }

    }

    @Nullable
    protected SoundEvent getPaddleSound() {
        switch (this.getBoatStatus()) {
            case IN_WATER:
            case UNDER_WATER:
            case UNDER_FLOWING_WATER:
                return SoundEvents.ENTITY_BOAT_PADDLE_WATER;
            case ON_LAND:
                return SoundEvents.ENTITY_BOAT_PADDLE_LAND;
            case IN_AIR:
            default:
                return null;
        }
    }

    private void tickLerp() {
        if (this.canPassengerSteer()) {
            this.lerpSteps = 0;
            this.setPacketCoordinates(this.getPosX(), this.getPosY(), this.getPosZ());
        }

        if (this.lerpSteps > 0) {
            double d0 = this.getPosX() + (this.lerpX - this.getPosX()) / (double) this.lerpSteps;
            double d1 = this.getPosY() + (this.lerpY - this.getPosY()) / (double) this.lerpSteps;
            double d2 = this.getPosZ() + (this.lerpZ - this.getPosZ()) / (double) this.lerpSteps;
            double d3 = MathHelper.wrapDegrees(this.lerpYaw - (double) this.rotationYaw);
            this.rotationYaw = (float) ((double) this.rotationYaw + d3 / (double) this.lerpSteps);
            this.rotationPitch = (float) ((double) this.rotationPitch + (this.lerpPitch - (double) this.rotationPitch) / (double) this.lerpSteps);
            --this.lerpSteps;
            this.setPosition(d0, d1, d2);
            this.setRotation(this.rotationYaw, this.rotationPitch);
        }

    }

    public void setPaddleState(boolean left, boolean right) {
        this.dataManager.set(LEFT_PADDLE, left);
        this.dataManager.set(RIGHT_PADDLE, right);
    }

    @OnlyIn(Dist.CLIENT)
    public float getRowingTime(int side, float limbSwing) {
        return this.getPaddleState(side) ? (float) MathHelper.clampedLerp((double) this.paddlePositions[side] - 0.39269909262657166D, (double) this.paddlePositions[side], (double) limbSwing) : 0.0F;
    }

    private BoatEntity.Status getBoatStatus() {
        BoatEntity.Status boatentity$status = this.getUnderwaterStatus();
        if (boatentity$status != null) {
            this.waterLevel = this.getBoundingBox().maxY;
            return boatentity$status;
        } else if (this.checkInWater()) {
            return BoatEntity.Status.IN_WATER;
        } else {
            float f = this.getBoatGlide();
            if (f > 0.0F) {
                this.boatGlide = f;
                return BoatEntity.Status.ON_LAND;
            } else {
                return BoatEntity.Status.IN_AIR;
            }
        }
    }

    public float getWaterLevelAbove() {
        AxisAlignedBB axisalignedbb = this.getBoundingBox();
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.ceil(axisalignedbb.maxX);
        int k = MathHelper.floor(axisalignedbb.maxY);
        int l = MathHelper.ceil(axisalignedbb.maxY - this.lastYd);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        label39:
        for (int k1 = k; k1 < l; ++k1) {
            float f = 0.0F;

            for (int l1 = i; l1 < j; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutable.setPos(l1, k1, i2);
                    FluidState fluidstate = this.world.getFluidState(blockpos$mutable);
                    if (fluidstate.isTagged(FluidTags.WATER)) {
                        f = Math.max(f, fluidstate.getActualHeight(this.world, blockpos$mutable));
                    }

                    if (f >= 1.0F) {
                        continue label39;
                    }
                }
            }

            if (f < 1.0F) {
                return (float) blockpos$mutable.getY() + f;
            }
        }

        return (float) (l + 1);
    }

    public float getBoatGlide() {
        AxisAlignedBB axisalignedbb = this.getBoundingBox();
        AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY - 0.001D, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        int i = MathHelper.floor(axisalignedbb1.minX) - 1;
        int j = MathHelper.ceil(axisalignedbb1.maxX) + 1;
        int k = MathHelper.floor(axisalignedbb1.minY) - 1;
        int l = MathHelper.ceil(axisalignedbb1.maxY) + 1;
        int i1 = MathHelper.floor(axisalignedbb1.minZ) - 1;
        int j1 = MathHelper.ceil(axisalignedbb1.maxZ) + 1;
        VoxelShape voxelshape = VoxelShapes.create(axisalignedbb1);
        float f = 0.0F;
        int k1 = 0;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for (int l1 = i; l1 < j; ++l1) {
            for (int i2 = i1; i2 < j1; ++i2) {
                int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
                if (j2 != 2) {
                    for (int k2 = k; k2 < l; ++k2) {
                        if (j2 <= 0 || k2 != k && k2 != l - 1) {
                            blockpos$mutable.setPos(l1, k2, i2);
                            BlockState blockstate = this.world.getBlockState(blockpos$mutable);
                            if (!(blockstate.getBlock() instanceof LilyPadBlock) && VoxelShapes.compare(blockstate.getCollisionShape(this.world, blockpos$mutable).withOffset((double) l1, (double) k2, (double) i2), voxelshape, IBooleanFunction.AND)) {
                                f += blockstate.getSlipperiness(this.world, blockpos$mutable, this);
                                ++k1;
                            }
                        }
                    }
                }
            }
        }

        return f / (float) k1;
    }

    private boolean checkInWater() {
        AxisAlignedBB axisalignedbb = this.getBoundingBox();
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.ceil(axisalignedbb.maxX);
        int k = MathHelper.floor(axisalignedbb.minY);
        int l = MathHelper.ceil(axisalignedbb.minY + 0.001D);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
        boolean flag = false;
        this.waterLevel = 4.9E-324D;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutable.setPos(k1, l1, i2);
                    FluidState fluidstate = this.world.getFluidState(blockpos$mutable);
                    if (fluidstate.isTagged(FluidTags.WATER)) {
                        float f = (float) l1 + fluidstate.getActualHeight(this.world, blockpos$mutable);
                        this.waterLevel = Math.max((double) f, this.waterLevel);
                        flag |= axisalignedbb.minY < (double) f;
                    }
                }
            }
        }

        return flag;
    }

    @Nullable
    private BoatEntity.Status getUnderwaterStatus() {
        AxisAlignedBB axisalignedbb = this.getBoundingBox();
        double d0 = axisalignedbb.maxY + 0.001D;
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.ceil(axisalignedbb.maxX);
        int k = MathHelper.floor(axisalignedbb.maxY);
        int l = MathHelper.ceil(d0);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
        boolean flag = false;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutable.setPos(k1, l1, i2);
                    FluidState fluidstate = this.world.getFluidState(blockpos$mutable);
                    if (fluidstate.isTagged(FluidTags.WATER) && d0 < (double) ((float) blockpos$mutable.getY() + fluidstate.getActualHeight(this.world, blockpos$mutable))) {
                        if (!fluidstate.isSource()) {
                            return BoatEntity.Status.UNDER_FLOWING_WATER;
                        }

                        flag = true;
                    }
                }
            }
        }

        return flag ? BoatEntity.Status.UNDER_WATER : null;
    }

    private void updateMotion() {
        double d0 = -0.03999999910593033D;
        double d1 = this.hasNoGravity() ? 0.0D : -0.03999999910593033D;
        double d2 = 0.0D;
        this.momentum = 0.05F;
        if (this.previousStatus == BoatEntity.Status.IN_AIR && this.status != BoatEntity.Status.IN_AIR && this.status != BoatEntity.Status.ON_LAND) {
            this.waterLevel = this.getPosYHeight(1.0D);
            this.setPosition(this.getPosX(), (double) (this.getWaterLevelAbove() - this.getHeight()) + 0.101D, this.getPosZ());
            this.setMotion(this.getMotion().mul(1.0D, 0.0D, 1.0D));
            this.lastYd = 0.0D;
            this.status = BoatEntity.Status.IN_WATER;
        } else {
            if (this.status == BoatEntity.Status.IN_WATER) {
                d2 = (this.waterLevel - this.getPosY()) / (double) this.getHeight();
                this.momentum = 0.9F;
            } else if (this.status == BoatEntity.Status.UNDER_FLOWING_WATER) {
                d1 = -7.0E-4D;
                this.momentum = 0.9F;
            } else if (this.status == BoatEntity.Status.UNDER_WATER) {
                d2 = 0.009999999776482582D;
                this.momentum = 0.45F;
            } else if (this.status == BoatEntity.Status.IN_AIR) {
                this.momentum = 0.9F;
            } else if (this.status == BoatEntity.Status.ON_LAND) {
                this.momentum = this.boatGlide;
                if (this.getControllingPassenger() instanceof PlayerEntity) {
                    this.boatGlide /= 2.0F;
                }
            }

            Vector3d vector3d = this.getMotion();
            this.setMotion(vector3d.x * (double) this.momentum, vector3d.y + d1, vector3d.z * (double) this.momentum);
            this.deltaRotation *= this.momentum;
            if (d2 > 0.0D) {
                Vector3d vector3d1 = this.getMotion();
                this.setMotion(vector3d1.x, (vector3d1.y + d2 * 0.06153846016296973D) * 0.75D, vector3d1.z);
            }
        }

    }

    private void controlBoat() {
        if (this.isBeingRidden()) {
            float f = 0.0F;
            if (this.leftInputDown) {
                --this.deltaRotation;
            }

            if (this.rightInputDown) {
                ++this.deltaRotation;
            }

            if (this.rightInputDown != this.leftInputDown && !this.forwardInputDown && !this.backInputDown) {
                f += 0.005F;
            }

            this.rotationYaw += this.deltaRotation;
            if (this.forwardInputDown) {
                f += 0.04F;
            }

            if (this.backInputDown) {
                f -= 0.005F;
            }

            this.setMotion(this.getMotion().add((double) (MathHelper.sin(-this.rotationYaw * 0.017453292F) * f), 0.0D, (double) (MathHelper.cos(this.rotationYaw * 0.017453292F) * f)));
            this.setPaddleState(this.rightInputDown && !this.leftInputDown || this.forwardInputDown, this.leftInputDown && !this.rightInputDown || this.forwardInputDown);
        }

    }

    public void updatePassenger(Entity passenger) {
        if (this.isPassenger(passenger)) {
            float f = 0.0F;
            float f1 = (float) ((this.removed ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());
            if (this.getPassengers().size() > 1) {
                int i = this.getPassengers().indexOf(passenger);
                if (i == 0) {
                    f = 0.2F;
                } else {
                    f = -0.6F;
                }

                if (passenger instanceof AnimalEntity) {
                    f = (float) ((double) f + 0.2D);
                }
            }

            Vector3d vector3d = (new Vector3d((double) f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * 0.017453292F - 1.5707964F);
            passenger.setPosition(this.getPosX() + vector3d.x, this.getPosY() + (double) f1, this.getPosZ() + vector3d.z);
            passenger.rotationYaw += this.deltaRotation;
            passenger.setRotationYawHead(passenger.getRotationYawHead() + this.deltaRotation);
            this.applyYawToEntity(passenger);
            if (passenger instanceof AnimalEntity && this.getPassengers().size() > 1) {
                int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
                passenger.setRenderYawOffset(((AnimalEntity) passenger).renderYawOffset + (float) j);
                passenger.setRotationYawHead(passenger.getRotationYawHead() + (float) j);
            }
        }

    }

    public Vector3d func_230268_c_(LivingEntity livingEntity) {
        Vector3d vector3d = func_233559_a_(this.getWidth() * MathHelper.SQRT_2, livingEntity.getWidth(), this.rotationYaw);
        double d0 = this.getPosX() + vector3d.x;
        double d1 = this.getPosZ() + vector3d.z;
        BlockPos blockpos = new BlockPos(d0, this.getBoundingBox().maxY, d1);
        BlockPos blockpos1 = blockpos.down();

        if (!this.world.hasWater(blockpos1)) {
            double d2 = (double) blockpos.getY() + this.world.func_242403_h(blockpos);
            double d3 = (double) blockpos.getY() + this.world.func_242403_h(blockpos1);

            for (Pose pose : livingEntity.getAvailablePoses()) {
                Vector3d vector3d1 = TransportationHelper.func_242381_a(this.world, d0, d2, d1, livingEntity, pose);
                if (vector3d1 != null) {
                    livingEntity.setPose(pose);
                    return vector3d1;
                }

                Vector3d vector3d2 = TransportationHelper.func_242381_a(this.world, d0, d3, d1, livingEntity, pose);
                if (vector3d2 != null) {
                    livingEntity.setPose(pose);
                    return vector3d2;
                }
            }
        }

        return super.func_230268_c_(livingEntity);
    }

    protected void applyYawToEntity(Entity entityToUpdate) {
        entityToUpdate.setRenderYawOffset(this.rotationYaw);
        float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
        float f1 = MathHelper.clamp(f, -105.0F, 105.0F);
        entityToUpdate.prevRotationYaw += f1 - f;
        entityToUpdate.rotationYaw += f1 - f;
        entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
    }

    @OnlyIn(Dist.CLIENT)
    public void applyOrientationToEntity(Entity entityToUpdate) {
        this.applyYawToEntity(entityToUpdate);
    }

    protected void writeAdditional(CompoundNBT compound) {
        compound.putString("Type", this.getBoatType().getName());
    }

    protected void readAdditional(CompoundNBT compound) {
        if (compound.contains("Type", 8)) {
            this.setBoatType(BoatEntity.Type.getTypeFromString(compound.getString("Type")));
        }

    }

    public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) {
        if (player.isSecondaryUseActive()) {
            return ActionResultType.PASS;
        } else if (this.outOfControlTicks < 60.0F) {
            if (!this.world.isRemote) {
                return player.startRiding(this) ? ActionResultType.CONSUME : ActionResultType.PASS;
            } else {
                return ActionResultType.SUCCESS;
            }
        } else {
            return ActionResultType.PASS;
        }
    }

    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        this.lastYd = this.getMotion().y;
        if (!this.isPassenger()) {
            if (onGroundIn) {
                if (this.fallDistance > 3.0F) {
                    if (this.status != BoatEntity.Status.ON_LAND) {
                        this.fallDistance = 0.0F;
                        return;
                    }

                    this.onLivingFall(this.fallDistance, 1.0F);
                    if (!this.world.isRemote && !this.removed) {
                        this.remove();
                        if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                            int j;
                            for (j = 0; j < 3; ++j) {
                                this.entityDropItem(this.getBoatType().asPlank());
                            }

                            for (j = 0; j < 2; ++j) {
                                this.entityDropItem(Items.STICK);
                            }
                        }
                    }
                }

                this.fallDistance = 0.0F;
            } else if (!this.world.getFluidState(this.getPosition().down()).isTagged(FluidTags.WATER) && y < 0.0D) {
                this.fallDistance = (float) ((double) this.fallDistance - y);
            }
        }

    }

    public boolean getPaddleState(int side) {
        return this.dataManager.get(side == 0 ? LEFT_PADDLE : RIGHT_PADDLE) && this.getControllingPassenger() != null;
    }

    public void setDamageTaken(float damageTaken) {
        this.dataManager.set(DAMAGE_TAKEN, damageTaken);
    }

    public float getDamageTaken() {
        return this.dataManager.get(DAMAGE_TAKEN);
    }

    public void setTimeSinceHit(int timeSinceHit) {
        this.dataManager.set(TIME_SINCE_HIT, timeSinceHit);
    }

    public int getTimeSinceHit() {
        return this.dataManager.get(TIME_SINCE_HIT);
    }

    private void setRockingTicks(int ticks) {
        this.dataManager.set(ROCKING_TICKS, ticks);
    }

    private int getRockingTicks() {
        return (Integer) this.dataManager.get(ROCKING_TICKS);
    }

    @OnlyIn(Dist.CLIENT)
    public float getRockingAngle(float partialTicks) {
        return MathHelper.lerp(partialTicks, this.prevRockingAngle, this.rockingAngle);
    }

    public void setForwardDirection(int forwardDirection) {
        this.dataManager.set(FORWARD_DIRECTION, forwardDirection);
    }

    public int getForwardDirection() {
        return this.dataManager.get(FORWARD_DIRECTION);
    }

    public void setBoatType(MythBoatEntity.Type boatType) {
        this.dataManager.set(BOAT_TYPE, boatType.ordinal());
    }

    public BoatEntity.Type getBoatType() {
        return BoatEntity.Type.byId(this.dataManager.get(BOAT_TYPE));
    }

    protected boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < 2 && !this.areEyesInFluid(FluidTags.WATER);
    }

    @Nullable
    public Entity getControllingPassenger() {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : (Entity) list.get(0);
    }

    @OnlyIn(Dist.CLIENT)
    public void updateInputs(boolean leftInputDown, boolean rightInputDown, boolean forwardInputDown, boolean backInputDown) {
        this.leftInputDown = leftInputDown;
        this.rightInputDown = rightInputDown;
        this.forwardInputDown = forwardInputDown;
        this.backInputDown = backInputDown;
    }

    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public boolean canSwim() {
        return this.status == BoatEntity.Status.UNDER_WATER || this.status == BoatEntity.Status.UNDER_FLOWING_WATER;
    }

    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        if (this.canPassengerSteer() && this.lerpSteps > 0) {
            this.lerpSteps = 0;
            this.setPositionAndRotation(this.lerpX, this.lerpY, this.lerpZ, (float) this.lerpYaw, (float) this.lerpPitch);
        }

    }

    static {
        TIME_SINCE_HIT = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.VARINT);
        FORWARD_DIRECTION = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.VARINT);
        DAMAGE_TAKEN = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.FLOAT);
        BOAT_TYPE = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.VARINT);
        LEFT_PADDLE = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.BOOLEAN);
        RIGHT_PADDLE = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.BOOLEAN);
        ROCKING_TICKS = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.VARINT);
    }
}
