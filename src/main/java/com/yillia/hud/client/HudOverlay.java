package com.yillia.hud.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.yillia.hud.YilliaHud;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.common.ForgeMod;


public class HudOverlay {
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
                        GuiComponent.blit(poseStack, left - (i % 10) * 8, top - (j / 10) * 10, 0, 0, 7, 9, 77, 9);
                    } else if (i == full) {
                        GuiComponent.blit(poseStack, left - (i % 10) * 8, top - (j / 10) * 10, 70-partial*7, 0, 7, 9, 77, 9);
                    } else {
                        GuiComponent.blit(poseStack, left - (i % 10) * 8, top - (j / 10) * 10, 70, 0, 7, 9, 77, 9);
                    }
                    j++;
                }
            }

            //Player Air Bar & Exp Bar
            if (mc.getCameraEntity() instanceof Player player) {
                RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);

                //Air Bar
                int air = player.getAirSupply();
                if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) || air < 300)
                {
                    int full = Mth.ceil((double) (air - 2) * 10.0D / 300.0D);
                    int partial = Mth.ceil((double) air * 10.0D / 300.0D) - full;

                    int left = screenWidth / 2 + 91;
                    int top = screenHeight - 39 - ((j-1) / 10 + 1) * 10;
                    for (int i = 0; i < full + partial; ++i)
                    {
                        GuiComponent.blit(poseStack, left - i * 8 - 9, top, (i < full ? 16 : 25), 18, 9, 9, 256, 256);
                    }
                }

                //Exp Bar
                int expNeed = player.getXpNeededForNextLevel();
                if (expNeed > 0) {
                    int k = (int)(player.experienceProgress * 91.0F);
                    int left = screenWidth / 2 - 91;
                    int top = screenHeight - 32 + 3;
                    GuiComponent.blit(poseStack, left, top, 0, 64, 91, 5, 256, 256);
                    if (k > 0) {
                        GuiComponent.blit(poseStack, left, top, 0, 69, k, 5, 256, 256);
                    }
                }
                if (player.experienceLevel > 0) {
                    String s = "" + player.experienceLevel;
                    int i1 = screenWidth / 2 - 91 - font.width(s);
                    int j1 = screenHeight - 32 + 2;
//                    int i1 = (screenWidth - font.width(s)) / 2;
//                    int j1 = screenHeight - 31 - 4;
                    font.draw(poseStack, s, (float)(i1 + 1), (float)j1, 0);
                    font.draw(poseStack, s, (float)(i1 - 1), (float)j1, 0);
                    font.draw(poseStack, s, (float)i1, (float)(j1 + 1), 0);
                    font.draw(poseStack, s, (float)i1, (float)(j1 - 1), 0);
                    font.draw(poseStack, s, (float)i1, (float)j1, 8453920);
                }

            }

            //Player Temp Bar

        }

    });

}
