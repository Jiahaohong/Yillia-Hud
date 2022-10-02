package com.yillia.hud.client;

import com.yillia.hud.energy.PlayerEnergy;

public class ClientEnergyData {
    private static int energy;
    private static int max_energy;
    private static int sprintTick;
    private static int swimTick;
    private static int restTick;
    private static int recoverTick;

    public static void setEnergy(int energy) {
        ClientEnergyData.energy = energy;
    }

    public static int getEnergy() {
        return energy;
    }

    public static void setMaxEnergy(int max) {
        ClientEnergyData.max_energy = max;
    }

    public static int getMaxEnergy() {
        return max_energy;
    }

    public static void setSprintTick(int sprintTick) {
        ClientEnergyData.sprintTick = sprintTick;
    }

    public static int getSprintTick() {
        return sprintTick;
    }

    public static void setSwimTick(int swimTick) {
        ClientEnergyData.swimTick = swimTick;
    }

    public static int getSwimTick() {
        return swimTick;
    }

    public static void setRestTick(int restTick) {
        ClientEnergyData.restTick = restTick;
    }

    public static int getRestTick() {
        return restTick;
    }

    public static void setRecoverTick(int recoverTick) {
        ClientEnergyData.recoverTick = recoverTick;
    }

    public static int getRecoverTick() {
        return recoverTick;
    }
}
