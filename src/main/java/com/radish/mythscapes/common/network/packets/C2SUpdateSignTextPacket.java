package com.radish.mythscapes.common.network.packets;

import com.radish.mythscapes.common.network.work.ServerPacketWork;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SUpdateSignTextPacket {

    public final String[] signText;
    public final BlockPos pos;

    public C2SUpdateSignTextPacket(BlockPos signPos, String first, String second, String third, String fourth) {
        this.pos = signPos;
        this.signText = new String[] {first, second, third, fourth};;
    }

    @SuppressWarnings("all")
    public static void handle(C2SUpdateSignTextPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isServer()) {
            context.enqueueWork(() -> ServerPacketWork.handleUpdateSignText(message, context.getSender()));
        }
        context.setPacketHandled(true);
    }

    public static C2SUpdateSignTextPacket decode(PacketBuffer buffer) {
        // Need to specify max length in readString() as the method variant without
        // parameters exists only on the client.
        return new C2SUpdateSignTextPacket(buffer.readBlockPos(), buffer.readString(32767), buffer.readString(32767), buffer.readString(32767), buffer.readString(32767));
    }

    public static void encode(C2SUpdateSignTextPacket packet, PacketBuffer buffer) {
        buffer.writeBlockPos(packet.pos);

        for (String s : packet.signText)
            buffer.writeString(s);
    }
}
