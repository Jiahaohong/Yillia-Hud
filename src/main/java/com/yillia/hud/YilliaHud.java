package com.yillia.hud;

import com.yillia.hud.network.ModMessages;
import com.yillia.hud.register.ModEffects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(YilliaHud.MOD_ID)
public class YilliaHud
{
    public static final String MOD_ID = "yillia_hud";

    public YilliaHud()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEffects.register(modEventBus);

        modEventBus.addListener(ModMessages::register);

        MinecraftForge.EVENT_BUS.register(this);

    }
}
