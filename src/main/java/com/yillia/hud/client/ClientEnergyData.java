package com.yillia.hud.client;

import com.yillia.hud.energy.PlayerEnergy;

public class ClientEnergyData {
    private static int playerEnergy;

    public static void set(int energy) {
        ClientEnergyData.playerEnergy = energy;
    }

    public static int getPlayerEnergy() {
        return playerEnergy;
    }

    public static int getPlayerMaxEnergy() {
        return new PlayerEnergy().MAX_ENERGY;
    }
}
