package com.yillia.hud.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.yillia.hud.YilliaHud;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;


public class EnergyHudOverlay {
    private static final ResourceLocation ENERGY0 = new ResourceLocation(YilliaHud.MOD_ID,
            "textures/gui/energy0.png");
    private static final ResourceLocation ENERGY1 = new ResourceLocation(YilliaHud.MOD_ID,
            "textures/gui/energy1.png");

    public static final IGuiOverlay HUD = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (gui.shouldDrawSurvivalElements()) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            //Player Energy Bar
            if (ClientEnergyData.getEnergy() >= 0) {
                int left = screenWidth / 2 + 26;
                int top = screenHeight / 2;
                int energy = ClientEnergyData.getEnergy();

                drawCircle(poseStack, left, top, 1, ClientEnergyData.getEnergy(), ClientEnergyData.getMaxEnergy());
            }

        }

    });

    private static void drawCircle(PoseStack poseStack, int centerX, int centerY, int scale, int energy, int maxEnergy) {
        switch (scale) {
            case 0: RenderSystem.setShaderTexture(0, ENERGY0); break;
            case 1: RenderSystem.setShaderTexture(0, ENERGY1); break;
            default: return;
        }
        int sizeXY = 16 + scale * 6;
        int quarterEnergy = ClientEnergyData.getMaxEnergy() / 4;
        if (maxEnergy >= quarterEnergy) {
            drawLeft(poseStack, centerX, centerY, sizeXY, (float)Math.min(energy, quarterEnergy) / quarterEnergy);
        }
        if (maxEnergy >= 2 * quarterEnergy) {
            drawDown(poseStack, centerX, centerY, sizeXY,  (float)Math.min(energy - quarterEnergy, quarterEnergy) / quarterEnergy);
        }
        if (maxEnergy >= 3 * quarterEnergy) {
            drawRight(poseStack, centerX, centerY, sizeXY, (float)Math.min(energy - 2 * quarterEnergy, quarterEnergy) / quarterEnergy);
        }
        if (maxEnergy >= 4 * quarterEnergy) {
            drawUp(poseStack, centerX, centerY, sizeXY, (float)Math.min(energy - 3 * quarterEnergy, quarterEnergy) / quarterEnergy);
        }
    }

    private static void drawUp(PoseStack poseStack, int centerX, int centerY, int sizeXY, float partial) {
        int sizeUV = sizeXY - 2;
        int offsetX = sizeUV - (int)(sizeUV*partial + 0.5);
        int offsetY = 0;
        int u = 0;
        int v = 0;
        int offsetU = sizeUV - (int)(sizeUV*partial + 0.5);
        int offsetV = 0;
        int length = (int)(sizeUV*partial + 0.5);
        int height = sizeUV;
        GuiComponent.blit(poseStack,
                centerX - sizeXY / 2 + 1, centerY - sizeXY / 2,
                u, v + sizeXY - 1, sizeUV, sizeUV, 128, 128);
        GuiComponent.blit(poseStack,
                centerX - sizeXY / 2 + 1 + offsetX, centerY - sizeXY / 2 + offsetY,
                u + offsetU, v + offsetV,
                length, height, 128, 128);
    }

    private static void drawRight(PoseStack poseStack, int centerX, int centerY, int sizeXY, float partial) {
        int sizeUV = sizeXY - 2;
        int offsetX = 0;
        int offsetY = sizeUV - (int)(sizeUV*partial + 0.5);
        int u = sizeXY - 1;
        int v = 0;
        int offsetU = 0;
        int offsetV = sizeUV - (int)(sizeUV*partial + 0.5);
        int length = sizeUV;
        int height = (int)(sizeUV*partial + 0.5);
        GuiComponent.blit(poseStack,
                centerX - sizeXY / 2 + 2, centerY - sizeXY / 2 + 1,
                u, v + sizeXY - 1, sizeUV, sizeUV, 128, 128);
        GuiComponent.blit(poseStack,
                centerX - sizeXY / 2 + 2 + offsetX, centerY - sizeXY / 2 + 1 + offsetY,
                u + offsetU, v + offsetV,
                length, height, 128, 128);
    }

    private static void drawDown(PoseStack poseStack, int centerX, int centerY, int sizeXY, float partial) {
        int sizeUV = sizeXY - 2;
        int offsetX = 0;
        int offsetY = 0;
        int u = 2 * (sizeXY - 1);
        int v = 0;
        int offsetU = 0;
        int offsetV = 0;
        int length = (int)(sizeUV*partial + 0.5);
        int height = sizeUV;
        GuiComponent.blit(poseStack,
                centerX - sizeXY / 2 + 1, centerY - sizeXY / 2 + 2,
                u, v + sizeXY - 1, sizeUV, sizeUV, 128, 128);
        GuiComponent.blit(poseStack,
                centerX - sizeXY / 2 + 1 + offsetX, centerY - sizeXY / 2 + 2 + offsetY,
                u + offsetU, v + offsetV,
                length, height, 128, 128);
    }

    private static void drawLeft(PoseStack poseStack, int centerX, int centerY, int sizeXY, float partial) {
        int sizeUV = sizeXY - 2;
        int offsetX = 0;
        int offsetY = 0;
        int u = 3 * (sizeXY - 1);
        int v = 0;
        int offsetU = 0;
        int offsetV = 0;
        int length = sizeUV;
        int height = (int)(sizeUV*partial + 0.5);
        GuiComponent.blit(poseStack,
                centerX - sizeXY / 2, centerY - sizeXY / 2 + 1,
                u, v + sizeXY - 1, sizeUV, sizeUV, 128, 128);
        GuiComponent.blit(poseStack,
                centerX - sizeXY / 2 + offsetX, centerY - sizeXY / 2 + 1 + offsetY,
                u + offsetU, v + offsetV,
                length, height, 128, 128);
    }

}
