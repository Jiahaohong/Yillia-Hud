package com.yillia.hud.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class PlayerEnergy {
    public final int BASE_MAX_ENERGY = 100;
    public int MAX_ENERGY = 100;
    public int MAX_SPRINT_TICK = 10;
    public int MAX_SWIM_TICK = 16;
    public int MAX_REST_TICK = 30;
    public int MAX_RECOVER_TICK = 5;
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

    public void setMaxEnergy(int max) {
        MAX_ENERGY = max;
    }

    public void addEnergy(int delta) {
        this.energy = Math.min(this.energy + delta, MAX_ENERGY);
    }

    public void subEnergy(int delta) {
        this.energy = Math.max(this.energy - delta, 0);
    }

    public void copyFrom(PlayerEnergy src) {
        this.MAX_ENERGY = src.MAX_ENERGY;
        this.MAX_SPRINT_TICK = src.MAX_SPRINT_TICK;
        this.MAX_SWIM_TICK = src.MAX_SWIM_TICK;
        this.MAX_REST_TICK = src.MAX_REST_TICK;
        this.MAX_RECOVER_TICK = src.MAX_RECOVER_TICK;
        this.energy = src.energy;
        this.sprintTick = src.sprintTick;
        this.swimTick = src.swimTick;
        this.restTick = src.restTick;
        this.recoverTick = src.recoverTick;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("max_energy", this.MAX_ENERGY);
        nbt.putInt("max_sprint_tick", this.MAX_SPRINT_TICK);
        nbt.putInt("max_swim_tick", this.MAX_SWIM_TICK);
        nbt.putInt("max_rest_tick", this.MAX_REST_TICK);
        nbt.putInt("max_recover_tick", this.MAX_RECOVER_TICK);

        nbt.putInt("energy", this.energy);
        nbt.putInt("sprintTick", this.sprintTick);
        nbt.putInt("swimTick", this.swimTick);
        nbt.putInt("restTick", this.restTick);
        nbt.putInt("recoverTick", this.recoverTick);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.MAX_ENERGY = nbt.getInt("max_energy");
        this.MAX_SPRINT_TICK = nbt.getInt("max_sprint_tick");
        this.MAX_SWIM_TICK = nbt.getInt("max_swim_tick");
        this.MAX_REST_TICK = nbt.getInt("max_rest_tick");
        this.MAX_RECOVER_TICK = nbt.getInt("max_recover_tick");

        this.energy = nbt.getInt("energy");
        this.sprintTick = nbt.getInt("sprintTick");
        this.swimTick = nbt.getInt("swimTick");
        this.restTick = nbt.getInt("restTick");
        this.recoverTick = nbt.getInt("recoverTick");
    }

    public void loadBuf(FriendlyByteBuf buf) {
        this.MAX_ENERGY = buf.readInt();
        this.energy = buf.readInt();
        this.sprintTick = buf.readInt();
        this.swimTick = buf.readInt();
        this.restTick = buf.readInt();
        this.recoverTick = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.MAX_ENERGY);
        buf.writeInt(this.energy);
        buf.writeInt(this.sprintTick);
        buf.writeInt(this.swimTick);
        buf.writeInt(this.restTick);
        buf.writeInt(this.recoverTick);
    }

}