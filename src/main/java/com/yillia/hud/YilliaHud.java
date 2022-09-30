package com.yillia.hud;

import com.yillia.hud.network.ModMessages;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(YilliaHud.MOD_ID)
public class YilliaHud
{
    public static final String MOD_ID = "yillia_hud";

    public YilliaHud()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(ModMessages::register);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
