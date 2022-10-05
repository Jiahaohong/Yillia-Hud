package com.yillia.hud.network.packet;

import com.yillia.hud.client.ClientEnergyData;
import com.yillia.hud.data.PlayerEnergy;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergyC2SPacket {
    private final PlayerEnergy energyData = new PlayerEnergy();

    public EnergyC2SPacket(PlayerEnergy src) {
        this.energyData.copyFrom(src);
    }

    public EnergyC2SPacket(FriendlyByteBuf buf) {
        this.energyData.loadBuf(buf);
    }

    public void toBytes(FriendlyByteBuf buf) {
        this.energyData.toBytes(buf);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientEnergyData.set(this.energyData);
        });
        return true;
    }
}
