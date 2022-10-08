package com.yillia.hud.event;

import com.yillia.hud.YilliaHud;
import com.yillia.hud.data.PlayerEnergy;
import com.yillia.hud.data.PlayerEnergyProvider;
import com.yillia.hud.network.packet.EnergyC2SPacket;
import com.yillia.hud.register.ModMessages;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
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
    private static final Minecraft mc = Minecraft.getInstance();

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
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy -> {
                    ModMessages.sendToPlayer(new EnergyC2SPacket(energy), player);
                });
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerEnergy.class);
    }

    @SubscribeEvent
    public static void deactivateHunger(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            event.player.getFoodData().setSaturation(0);
            event.player.getFoodData().setFoodLevel(10);
        }
    }

    @SubscribeEvent
    public static void cancelVanillaRender(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay() == VanillaGuiOverlay.FOOD_LEVEL.type()
            || event.getOverlay() == VanillaGuiOverlay.AIR_LEVEL.type()
            || event.getOverlay() == VanillaGuiOverlay.EXPERIENCE_BAR.type()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            if (!event.player.isCreative() && !event.player.isSpectator()) {
                event.player.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy -> {
                    boolean isSPrint = true;
                    boolean isSwim = true;
                    // Player Sprinting
                    if (event.player.isSprinting()) {
                        energy.subEnergy(energy.sprintConsume);
                        ModMessages.sendToPlayer(new EnergyC2SPacket(energy), (ServerPlayer) event.player);
                    } else {
                        isSPrint = false;
                    }

                    //Player Swimming
                    if (event.player.isSwimming()) {
                        energy.subEnergy(energy.swimConsume);
                        ModMessages.sendToPlayer(new EnergyC2SPacket(energy), (ServerPlayer) event.player);
                    } else {
                        isSwim = false;
                    }

                    //Player Resting
                    if (!isSPrint && !isSwim) {
                        energy.restTick = Math.min(energy.restTick + 1, energy.REST_TICK);
                        if (energy.restTick == energy.REST_TICK) {
                            energy.addEnergy(energy.recoverSpeed);
                            ModMessages.sendToPlayer(new EnergyC2SPacket(energy), (ServerPlayer) event.player);
                        }
                    } else {
                        energy.restTick = Math.max(energy.restTick - 1, 0);
                    }

                });
            }

        }
    }
}
