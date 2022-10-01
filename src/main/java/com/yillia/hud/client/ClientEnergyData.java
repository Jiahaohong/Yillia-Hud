package com.yillia.hud.client;

import com.yillia.hud.energy.PlayerEnergy;

public class ClientEnergyData {
    private static int energy;
    private static int sprintTick;

    public static void setEnergy(int energy) {
        ClientEnergyData.energy = energy;
    }

    public static int getEnergy() {
        return energy;
    }

    public static int getPlayerMaxEnergy() {
        return new PlayerEnergy().MAX_ENERGY;
    }

    public static void setSprintTick(int sprintTick) {
        ClientEnergyData.sprintTick = sprintTick;
    }

    public static int getSprintTick() {
        return sprintTick;
    }

    public static int getMaxSprintTIck() {
        return new PlayerEnergy().MAX_SPRINT_TICK;
    }
}
