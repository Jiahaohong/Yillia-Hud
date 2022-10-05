package com.yillia.hud.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yillia.hud.YilliaHud;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;


public class EnergyHudOverlay {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final Font font = mc.font;
    private static final ResourceLocation MOD_ICON = new ResourceLocation(YilliaHud.MOD_ID,
            "textures/gui/mod_icon.png");

    public static final IGuiOverlay HUD = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (gui.shouldDrawSurvivalElements()) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            int j = 0; //Count how many icons have been drawn

            //Player Energy Bar
            if (ClientEnergyData.getEnergy() >= 0) {
                RenderSystem.setShaderTexture(0, MOD_ICON);
                int full = ClientEnergyData.getEnergy() / 10;
                int partial = ClientEnergyData.getEnergy() % 10;

                int left = screenWidth / 2 + 83;
                int top = screenHeight - 39;
                for (int i = 0; i < ClientEnergyData.getMaxEnergy() / 10; i++) {
                    if (i <= full - 1) {
                        GuiComponent.blit(poseStack, left - (i % 10) * 8, top - (j / 10) * 10, 0, 0, 7, 9, 91, 86);
                    } else if (i == full) {
                        GuiComponent.blit(poseStack, left - (i % 10) * 8, top - (j / 10) * 10, 70-partial*7, 0, 7, 9, 91, 86);
                    } else {
                        GuiComponent.blit(poseStack, left - (i % 10) * 8, top - (j / 10) * 10, 70, 0, 7, 9, 91, 86);
                    }
                    j++;
                }
            }

        }

    });

}
