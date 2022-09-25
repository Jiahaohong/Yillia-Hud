package com.yillia.hud.energy;

import net.minecraft.nbt.CompoundTag;

public class PlayerEnergy {
    private int energy;
    private final int MAX_ENERGY = 100;

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
        this.energy = src.getEnergy();
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("energy", this.energy);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.energy = nbt.getInt("energy");
    }

}
