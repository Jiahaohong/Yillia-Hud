package com.yillia.hud.register;

import com.yillia.hud.YilliaHud;
import com.yillia.hud.effect.EnergyBoostEffect;
import com.yillia.hud.effect.InstanceEnergyEffect;
import com.yillia.hud.effect.QuickRecoveryEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, YilliaHud.MOD_ID);

    public static final RegistryObject<MobEffect> ENERGY_BOOST = EFFECTS.register("energy_boost",
            () -> new EnergyBoostEffect(MobEffectCategory.BENEFICIAL, 3124687));

    public static final RegistryObject<MobEffect> QUICK_RECOVERY = EFFECTS.register("quick_recovery",
            () -> new QuickRecoveryEffect(MobEffectCategory.BENEFICIAL, 3124687));

    public static final RegistryObject<MobEffect> INSTANCE_ENERGY = EFFECTS.register("instance_energy",
            () -> new InstanceEnergyEffect(MobEffectCategory.BENEFICIAL, 3124687));

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
