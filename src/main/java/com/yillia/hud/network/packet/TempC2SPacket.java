package com.yillia.hud.network.packet;

import com.yillia.hud.client.ClientTempData;
import com.yillia.hud.data.PlayerTemp;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TempC2SPacket {
    private final PlayerTemp tempData = new PlayerTemp();

    public TempC2SPacket(PlayerTemp src) {
        this.tempData.copyFrom(src);
    }

    public TempC2SPacket(FriendlyByteBuf buf) {
        this.tempData.loadBuf(buf);
    }

    public void toBytes(FriendlyByteBuf buf) {
        this.tempData.toBytes(buf);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientTempData.set(this.tempData);
        });
        return true;
    }
}
