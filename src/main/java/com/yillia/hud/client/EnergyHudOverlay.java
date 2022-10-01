package com.yillia.hud.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yillia.hud.YilliaHud;
import com.yillia.hud.energy.PlayerEnergy;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class EnergyHudOverlay {
    private static final ResourceLocation MOD_ICON = new ResourceLocation(YilliaHud.MOD_ID,
            "textures/gui/mod_icon.png");

    public static final IGuiOverlay HUD_ENERGY = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight / 2;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, MOD_ICON);


        for (int i = 0; i < 10; i++) {
            GuiComponent.blit(poseStack, x + 11 + 72 - i * 8, screenHeight - 39, 70, 0, 7, 9, 77, 9);
        }


        if (ClientEnergyData.getPlayerEnergy() > 0) {
            int full = ClientEnergyData.getPlayerEnergy() / 10;
            int half = ClientEnergyData.getPlayerEnergy() - full * 10;

            for (int i = 0; i < 10; i++) {
                if (i <= full - 1) {
                    GuiComponent.blit(poseStack, x + 11 + 72 - i * 8, screenHeight - 39, 0, 0, 7, 9, 77, 9);
                } else if (i == full) {
                    GuiComponent.blit(poseStack, x + 11 + 72 - i * 8, screenHeight - 39, 70-half*7, 0, 7, 9, 77, 9);
                } else {
                    GuiComponent.blit(poseStack, x + 11 + 72 - i * 8, screenHeight - 39, 70, 0, 7, 9, 77, 9);

                }
            }
        }

    });

}
