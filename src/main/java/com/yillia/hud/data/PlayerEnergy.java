package com.yillia.hud.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class PlayerEnergy {
    public static final int BASE_MAX_ENERGY = 1440;
    public static final int BASE_SPRINT_CONSUME = 5;
    public static final int BASE_SWIM_CONSUME = 5;
    public static final int BASE_REST_TICK = 60;
    public static final int BASE_RECOVER_SPEED = 8;
    public int MAX_ENERGY = BASE_MAX_ENERGY;
    private int energy;
    public int sprintConsume;
    public int swimConsume;
    public int restTick;
    public int recoverSpeed;

    public PlayerEnergy() {
        energy = MAX_ENERGY;
        sprintConsume = BASE_SPRINT_CONSUME;
        swimConsume = BASE_SWIM_CONSUME;
        restTick = BASE_REST_TICK;
        recoverSpeed = BASE_RECOVER_SPEED;
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
        this.MAX_ENERGY = src.MAX_ENERGY;

        this.energy = src.energy;
        this.sprintConsume = src.sprintConsume;
        this.swimConsume = src.swimConsume;
        this.restTick = src.restTick;
        this.recoverSpeed = src.recoverSpeed;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("MAX_ENERGY", this.MAX_ENERGY);

        nbt.putInt("energy", this.energy);
        nbt.putInt("sprintTick", this.sprintConsume);
        nbt.putInt("swimTick", this.swimConsume);
        nbt.putInt("restTick", this.restTick);
        nbt.putInt("recoverTick", this.recoverSpeed);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.MAX_ENERGY = nbt.getInt("MAX_ENERGY");

        this.energy = nbt.getInt("energy");
        this.sprintConsume = nbt.getInt("sprintTick");
        this.swimConsume = nbt.getInt("swimTick");
        this.restTick = nbt.getInt("restTick");
        this.recoverSpeed = nbt.getInt("recoverTick");
    }

    public void loadBuf(FriendlyByteBuf buf) {
        this.MAX_ENERGY = buf.readInt();

        this.energy = buf.readInt();
        this.sprintConsume = buf.readInt();
        this.swimConsume = buf.readInt();
        this.restTick = buf.readInt();
        this.recoverSpeed = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.MAX_ENERGY);

        buf.writeInt(this.energy);
        buf.writeInt(this.sprintConsume);
        buf.writeInt(this.swimConsume);
        buf.writeInt(this.restTick);
        buf.writeInt(this.recoverSpeed);
    }

}
