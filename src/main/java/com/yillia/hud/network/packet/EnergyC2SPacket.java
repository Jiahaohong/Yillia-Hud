package com.yillia.hud.network.packet;

import com.yillia.hud.client.ClientEnergyData;
import com.yillia.hud.energy.PlayerEnergy;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergyC2SPacket {
    private final int energy;
    private final int sprintTick;
    private final int swimTick;

    public EnergyC2SPacket(PlayerEnergy energy) {
        this.energy = energy.getEnergy();
        this.sprintTick = energy.sprintTick;
        this.swimTick = energy.swimTick;
    }

    public EnergyC2SPacket(FriendlyByteBuf buf) {
        this.energy = buf.readInt();
        this.sprintTick = buf.readInt();
        this.swimTick = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.energy);
        buf.writeInt(this.sprintTick);
        buf.writeInt(this.swimTick);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientEnergyData.setEnergy(this.energy);
            ClientEnergyData.setSprintTick(this.sprintTick);
            ClientEnergyData.setSwimTick(this.swimTick);
        });
        return true;
    }

}
