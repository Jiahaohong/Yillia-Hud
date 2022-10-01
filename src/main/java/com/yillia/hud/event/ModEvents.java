package com.yillia.hud.event;

import com.yillia.hud.YilliaHud;
import com.yillia.hud.energy.PlayerEnergy;
import com.yillia.hud.energy.PlayerEnergyProvider;
import com.yillia.hud.network.ModMessages;
import com.yillia.hud.network.packet.EnergyC2SPacket;
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
            if (!player.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).isPresent()) {
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
            if (!event.player.isCreative() && !event.player.isSpectator()) {
                event.player.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy -> {
                    // Player Sprinting
                    if (event.player.isSprinting()) {
                        energy.sprintTick = Math.min(energy.sprintTick + 1, energy.MAX_SPRINT_TICK);
                        if (energy.sprintTick == energy.MAX_SPRINT_TICK) {
                            energy.sprintTick = 0;
                            energy.subEnergy(1);
                            ModMessages.sendToPlayer(new EnergyC2SPacket(energy), (ServerPlayer) event.player);
                        }
                    } else {
                        energy.sprintTick = Math.max(energy.sprintTick - 1, 0);
                    }

                    //Player Swimming
                    if (event.player.isSwimming()) {
                        energy.swimTick = Math.min(energy.swimTick + 1, energy.MAX_SWIM_TICK);
                        if (energy.swimTick == energy.MAX_SWIM_TICK) {
                            energy.swimTick = 0;
                            energy.subEnergy(1);
                            ModMessages.sendToPlayer(new EnergyC2SPacket(energy), (ServerPlayer) event.player);
                        }
                    } else {
                        energy.swimTick = Math.max(energy.swimTick - 1, 0);
                    }


                });
            }

        }
    }

    @SubscribeEvent public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy -> {
                    ModMessages.sendToPlayer(new EnergyC2SPacket(energy), player);
                });
            }
        }
    }

}
