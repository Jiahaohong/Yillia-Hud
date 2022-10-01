package com.yillia.hud.energy;

import net.minecraft.nbt.CompoundTag;

public class PlayerSprintTick {
    private int sprintTick;
    public final int MAX_TICK = 100;

    public PlayerSprintTick() {
        sprintTick = MAX_TICK;
    }

    public int getSprintTick() {
        return sprintTick;
    }

    public void addSprintTick(int delta) {
        this.sprintTick = Math.min(this.sprintTick + delta, MAX_TICK);
    }

    public void subSprintTick(int delta) {
        this.sprintTick = Math.max(this.sprintTick - delta, 0);
    }

    public void copyFrom(PlayerSprintTick src) {
        this.sprintTick = src.sprintTick;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("sprintTick", this.sprintTick);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.sprintTick = nbt.getInt("sprintTick");
    }
}
