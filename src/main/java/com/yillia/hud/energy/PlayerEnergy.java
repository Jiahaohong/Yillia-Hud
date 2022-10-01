package com.yillia.hud.energy;

import net.minecraft.nbt.CompoundTag;

public class PlayerEnergy {
    public final int MAX_ENERGY = 100;
    public final int MAX_SPRINT_TICK = 10;
    public final int MAX_SWIM_TICK = 30;
    private int energy;
    public int sprintTick;
    public int swimTick;

    public PlayerEnergy() {
        energy = MAX_ENERGY;
        sprintTick = 0;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void addEnergy(int delta) {
        this.energy = Math.min(this.energy + delta, MAX_ENERGY);
    }

    public void subEnergy(int delta) {
        this.energy = Math.max(this.energy - delta, 0);
    }

    public void copyFrom(PlayerEnergy src) {
        this.energy = src.energy;
        this.sprintTick = src.sprintTick;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("energy", this.energy);
        nbt.putInt("sprintTick", this.sprintTick);
        nbt.putInt("swimTick", this.swimTick);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.energy = nbt.getInt("energy");
        this.sprintTick = nbt.getInt("sprintTick");
        this.swimTick = nbt.getInt("swimTick");
    }

}
