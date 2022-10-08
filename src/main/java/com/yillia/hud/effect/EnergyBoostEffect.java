package com.yillia.hud.effect;

import com.yillia.hud.data.PlayerEnergy;
import com.yillia.hud.data.PlayerEnergyProvider;
import com.yillia.hud.network.packet.EnergyC2SPacket;
import com.yillia.hud.register.ModMessages;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class EnergyBoostEffect extends MobEffect {
    public EnergyBoostEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level.isClientSide()) {
            if (livingEntity instanceof Player player) {
                player.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy -> {
                    energy.MAX_ENERGY = PlayerEnergy.BASE_MAX_ENERGY + PlayerEnergy.BASE_MAX_ENERGY / 4 *(amplifier+1);
                    energy.MAX_ENERGY = Math.min(energy.MAX_ENERGY, 3 * PlayerEnergy.BASE_MAX_ENERGY);
                    ModMessages.sendToPlayer(new EnergyC2SPacket(energy), (ServerPlayer) player);
                });
            }
        }
        super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    public void removeAttributeModifiers(@NotNull LivingEntity livingEntity, @NotNull AttributeMap attributeMap, int p_19438_) {
        super.removeAttributeModifiers(livingEntity, attributeMap, p_19438_);
        livingEntity.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy -> {
            energy.MAX_ENERGY = PlayerEnergy.BASE_MAX_ENERGY;
            if (energy.getEnergy() > PlayerEnergy.BASE_MAX_ENERGY) {
                energy.subEnergy(energy.getEnergy() - PlayerEnergy.BASE_MAX_ENERGY);
            }
            ModMessages.sendToPlayer(new EnergyC2SPacket(energy), (ServerPlayer) livingEntity);
        });

    }

}
