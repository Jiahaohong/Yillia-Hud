package com.yillia.hud.network.packet;

import com.yillia.hud.client.ClientEnergyData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergyC2SPacket {
    private final int energy;

    public EnergyC2SPacket(int energy) {
        this.energy = energy;
    }

    public EnergyC2SPacket(FriendlyByteBuf buf) {
        this.energy = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.energy);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientEnergyData.set(this.energy);
        });
        return true;
    }

}
