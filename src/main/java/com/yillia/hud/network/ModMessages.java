package com.yillia.hud.network;

import com.yillia.hud.YilliaHud;
import com.yillia.hud.network.packet.EnergyC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetID = 0;
    private static int id() {
        return packetID += 1;
    }

    public static void register(final FMLCommonSetupEvent event) {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(YilliaHud.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(EnergyC2SPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnergyC2SPacket::new)
                .encoder(EnergyC2SPacket::toBytes)
                .consumerMainThread(EnergyC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
