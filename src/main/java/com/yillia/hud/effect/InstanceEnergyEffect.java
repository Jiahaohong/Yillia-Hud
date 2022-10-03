package com.yillia.hud.effect;

import com.yillia.hud.data.PlayerEnergyProvider;
import com.yillia.hud.network.packet.EnergyC2SPacket;
import com.yillia.hud.register.ModMessages;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public class InstanceEnergyEffect extends MobEffect {
    public InstanceEnergyEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity entity1, @Nullable Entity entity2, LivingEntity livingEntity, int amplifier1, double amplifier2) {
        if (!livingEntity.level.isClientSide()) {
            if (livingEntity instanceof Player player) {
                player.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy -> {
                    energy.addEnergy(20*(int)(amplifier2+1));
                    ModMessages.sendToPlayer(new EnergyC2SPacket(energy), (ServerPlayer) player);
                });
            }
        }
        super.applyInstantenousEffect(entity1, entity2, livingEntity, amplifier1, amplifier2);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

}
