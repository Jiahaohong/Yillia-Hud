package com.yillia.hud.effect;

import com.yillia.hud.data.PlayerEnergyProvider;
import com.yillia.hud.register.ModMessages;
import com.yillia.hud.network.packet.EnergyC2SPacket;
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
                    energy.setMaxEnergy(energy.BASE_MAX_ENERGY + 40*(amplifier+1));
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

    public void removeAttributeModifiers(LivingEntity livingEntity, AttributeMap attributeMap, int p_19438_) {
        super.removeAttributeModifiers(livingEntity, attributeMap, p_19438_);
        livingEntity.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy -> {
            energy.setMaxEnergy(energy.BASE_MAX_ENERGY);
            ModMessages.sendToPlayer(new EnergyC2SPacket(energy), (ServerPlayer) livingEntity);
        });

    }

}
