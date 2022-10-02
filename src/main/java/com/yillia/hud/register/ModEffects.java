package com.yillia.hud.register;

import com.yillia.hud.YilliaHud;
import com.yillia.hud.effect.EnergyBoostEffect;
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

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
