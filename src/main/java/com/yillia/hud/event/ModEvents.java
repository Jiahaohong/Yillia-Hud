package com.yillia.hud.event;

import com.google.common.eventbus.Subscribe;
import com.yillia.hud.YilliaHud;
import com.yillia.hud.energy.PlayerEnergy;
import com.yillia.hud.energy.PlayerEnergyProvider;
import com.yillia.hud.network.ModMessages;
import com.yillia.hud.network.packet.EnergyC2SPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = YilliaHud.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachPlayerCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (player.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).isPresent()) {
                event.addCapability(new ResourceLocation(YilliaHud.MOD_ID, "properties"), new PlayerEnergyProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerEnergy.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy -> {
                if (energy.getEnergy() > 0) {
                    if (event.player.isSprinting()) {
                        energy.subEnergy(1);
                        event.player.sendSystemMessage(Component.literal("Energy:"+energy.getEnergy()));
                        ModMessages.sendToPlayer(new EnergyC2SPacket(energy.getEnergy()), (ServerPlayer) event.player);
                    }
                    System.out.print("sprint");
                }
            });
        }
    }

    @SubscribeEvent public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy -> {
                    ModMessages.sendToPlayer(new EnergyC2SPacket(energy.getEnergy()), player);
                });
            }
        }
    }

}
