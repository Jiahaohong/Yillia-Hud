package com.yillia.hud.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
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
    private static final ResourceLocation ENERGY = new ResourceLocation(YilliaHud.MOD_ID,
            "textures/gui/energy.png");

    public static final IGuiOverlay HUD = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (gui.shouldDrawSurvivalElements()) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, ENERGY);

            //Player Energy Bar
            if (ClientEnergyData.getEnergy() >= 0) {
                int left = screenWidth / 2 + 26;
                int top = screenHeight / 2;
                int energy = ClientEnergyData.getEnergy();

                drawBg(poseStack, left, top);

                float part1 = (float)Math.min(energy, 25) / 25;
                float part2 = (float)Math.min(energy - 25, 25) / 25;
                float part3 = (float)Math.min(energy - 50, 25) / 25;
                float part4 = (float)Math.min(energy - 75, 25) / 25;
                drawUp(poseStack, left, top, part4);
                drawRight(poseStack, left, top, part3);
                drawDown(poseStack, left, top, part2);
                drawLeft(poseStack, left, top, part1);
            }

        }

    });

    private static void drawBg(PoseStack poseStack, int centerX, int centerY) {
        GuiComponent.blit(poseStack, centerX - 16 / 2 + 1, centerY - 16 / 2, 0, 15, 14, 14, 128, 128);
        GuiComponent.blit(poseStack, centerX - 16 / 2 + 2, centerY - 16 / 2 + 1, 15, 15, 14, 14, 128, 128);
        GuiComponent.blit(poseStack, centerX - 16 / 2 + 1, centerY - 16 / 2 + 2, 30, 15, 14, 14, 128, 128);
        GuiComponent.blit(poseStack, centerX - 16 / 2, centerY - 16 / 2 + 1, 45, 15, 14, 14, 128, 128);

    }

    private static void drawUp(PoseStack poseStack, int centerX, int centerY, float partial) {
        int offsetX = 14 - (int)(14*partial + 0.5);
        int offsetY = 0;
        int offsetU = 14 - (int)(14*partial + 0.5);
        int offsetV = 0;
        int length = (int)(14*partial + 0.5);
        int height = 14;
        GuiComponent.blit(poseStack,
                centerX - 16 / 2 + 1 + offsetX, centerY - 16 / 2 + offsetY,
                0 + offsetU, 0 + offsetV,
                length, height, 128, 128);
    }

    private static void drawRight(PoseStack poseStack, int centerX, int centerY, float partial) {
        int offsetX = 0;
        int offsetY = 14 - (int)(14*partial + 0.5);
        int offsetU = 0;
        int offsetV = 14 - (int)(14*partial + 0.5);
        int length = 14;
        int height = (int)(14*partial + 0.5);
        GuiComponent.blit(poseStack,
                centerX - 16 / 2 + 2 + offsetX, centerY - 16 / 2 + 1 + offsetY,
                15 + offsetU, 0 + offsetV,
                length, height, 128, 128);
    }

    private static void drawDown(PoseStack poseStack, int centerX, int centerY, float partial) {
        int offsetX = 0;
        int offsetY = 0;
        int offsetU = 0;
        int offsetV = 0;
        int length = (int)(14*partial + 0.5);
        int height = 14;
        GuiComponent.blit(poseStack,
                centerX - 16 / 2 + 1 + offsetX, centerY - 16 / 2 + 2 + offsetY,
                30 + offsetU, 0 + offsetV,
                length, height, 128, 128);
    }

    private static void drawLeft(PoseStack poseStack, int centerX, int centerY, float partial) {
        int offsetX = 0;
        int offsetY = 0;
        int offsetU = 0;
        int offsetV = 0;
        int length = 14;
        int height = (int)(14*partial + 0.5);
        GuiComponent.blit(poseStack,
                centerX - 16 / 2 + offsetX, centerY - 16 / 2 + 1 + offsetY,
                45 + offsetU, 0 + offsetV,
                length, height, 128, 128);
    }

}
