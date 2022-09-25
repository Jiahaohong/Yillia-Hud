package com.yillia.hud.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.yillia.hud.YilliaHud;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class EnergyHudOverlay {
    private static final ResourceLocation FILLED_ENERGY = new ResourceLocation(YilliaHud.MOD_ID,
            "textures/gui/filled_energy.png");
    private static final ResourceLocation EMPTY_ENERGY = new ResourceLocation(YilliaHud.MOD_ID,
            "textures/gui/empty_energy.png");

    public static final IGuiOverlay HUD_ENERGY = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight / 2;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_ENERGY);
        //91 pixels -> 81 pixels
        GuiComponent.blit(poseStack, x + 10, y + 81, 0, 0, 81, 9, 81, 9);

        RenderSystem.setShaderTexture(0, FILLED_ENERGY);
//        GuiComponent.blit(poseStack, x - 94, y - 54, 0, 0, 182, 5, 182, 5);

    });

}
