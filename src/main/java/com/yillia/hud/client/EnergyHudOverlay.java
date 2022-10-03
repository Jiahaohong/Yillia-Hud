package com.yillia.hud.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.yillia.hud.YilliaHud;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.common.ForgeMod;

import static net.minecraft.client.gui.GuiComponent.GUI_ICONS_LOCATION;

public class EnergyHudOverlay {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final ResourceLocation MOD_ICON = new ResourceLocation(YilliaHud.MOD_ID,
            "textures/gui/mod_icon.png");

    public static final IGuiOverlay HUD_ENERGY = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (gui.shouldDrawSurvivalElements()) {
            int x = screenWidth / 2;
            int y = screenHeight / 2;

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, MOD_ICON);


            int j = 0;
            if (ClientEnergyData.getEnergy() >= 0) {
                int full = ClientEnergyData.getEnergy() / 10;
                int partial = ClientEnergyData.getEnergy() % 10;

                for (int i = 0; i < ClientEnergyData.getMaxEnergy() / 10; i++) {
                    if (i <= full - 1) {
                        GuiComponent.blit(poseStack, x + 11 + 72 - (i % 10) * 8, screenHeight - 39 - (j / 10) * 10, 0, 0, 7, 9, 77, 9);
                    } else if (i == full) {
                        GuiComponent.blit(poseStack, x + 11 + 72 - (i % 10) * 8, screenHeight - 39 - (j / 10) * 10, 70-partial*7, 0, 7, 9, 77, 9);
                    } else {
                        GuiComponent.blit(poseStack, x + 11 + 72 - (i % 10) * 8, screenHeight - 39 - (j / 10) * 10, 70, 0, 7, 9, 77, 9);
                    }
                    j++;
                }
            }

            if (mc.getCameraEntity() instanceof Player player) {
                int air = player.getAirSupply();

                int left = screenWidth / 2 + 91;
                int top = screenHeight - 39 - ((j-1) / 10 + 1) * 10;

                RenderSystem.setShaderTexture(0, GUI_ICONS_LOCATION);

                if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) || air < 300)
                {
                    int full = Mth.ceil((double) (air - 2) * 10.0D / 300.0D);
                    int partial = Mth.ceil((double) air * 10.0D / 300.0D) - full;
                    for (int i = 0; i < full + partial; ++i)
                    {
                        GuiComponent.blit(poseStack, left - i * 8 - 9, top, (i < full ? 16 : 25), 18, 9, 9, 256, 256);
                    }
                }
            }
        }

    });

}
