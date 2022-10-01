package com.yillia.hud.event;

import com.google.common.eventbus.Subscribe;
import com.yillia.hud.YilliaHud;
import com.yillia.hud.client.EnergyHudOverlay;
import com.yillia.hud.energy.PlayerEnergyProvider;
import com.yillia.hud.network.ModMessages;
import com.yillia.hud.network.packet.EnergyC2SPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = YilliaHud.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
    }

    @Mod.EventBusSubscriber(modid = YilliaHud.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerGuiOverlay(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("energy", EnergyHudOverlay.HUD_ENERGY);
        }
    }
}
