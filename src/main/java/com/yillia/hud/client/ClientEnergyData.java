package com.yillia.hud.client;

import com.yillia.hud.data.PlayerEnergy;

public class ClientEnergyData {
    private static PlayerEnergy energyData = new PlayerEnergy();

    public static void set(PlayerEnergy src) {
        energyData.copyFrom(src);
    }

    public static int getEnergy() {
        return energyData.getEnergy();
    }

    public static int getMaxEnergy() {
        return energyData.MAX_ENERGY;
    }
}
