package com.yillia.hud.energy;

import net.minecraft.nbt.CompoundTag;

public class PlayerEnergy {
    public final int MAX_ENERGY = 100;
    public final int MAX_SPRINT_TICK = 10;
    public final int MAX_SWIM_TICK = 16;
    public final int MAX_REST_TICK = 30;
    public final int MAX_RECOVER_TICK = 8;
    private int energy;
    public int sprintTick;
    public int swimTick;
    public int restTick;
    public int recoverTick;

    public PlayerEnergy() {
        energy = MAX_ENERGY;
        sprintTick = 0;
        swimTick = 0;
        restTick = 0;
        recoverTick = 0;
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
        this.swimTick = src.swimTick;
        this.restTick = src.restTick;
        this.recoverTick = src.recoverTick;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("energy", this.energy);
        nbt.putInt("sprintTick", this.sprintTick);
        nbt.putInt("swimTick", this.swimTick);
        nbt.putInt("restTick", this.restTick);
        nbt.putInt("recoverTick", this.recoverTick);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.energy = nbt.getInt("energy");
        this.sprintTick = nbt.getInt("sprintTick");
        this.swimTick = nbt.getInt("swimTick");
        this.restTick = nbt.getInt("restTick");
        this.recoverTick = nbt.getInt("recoverTick");
    }

}
