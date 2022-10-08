package com.yillia.hud.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class PlayerEnergy {
    public static final int BASE_MAX_ENERGY = 1000;
    public static final int BASE_SPRINT_CONSUME = 10;
    public static final int BASE_SWIM_CONSUME = 10;
    public static final int BASE_REST_TICK = 20;
    public static final int BASE_RECOVER_SPEED = 1;
    public int MAX_ENERGY = BASE_MAX_ENERGY;
    public int SPRINT_CONSUME = BASE_SPRINT_CONSUME;
    public int SWIM_CONSUME = BASE_SWIM_CONSUME;
    public int REST_TICK = BASE_REST_TICK;
    public int RECOVER_SPEED = BASE_RECOVER_SPEED;
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
        this.SPRINT_CONSUME = src.SPRINT_CONSUME;
        this.SWIM_CONSUME = src.SWIM_CONSUME;
        this.REST_TICK = src.REST_TICK;
        this.RECOVER_SPEED = src.RECOVER_SPEED;

        this.energy = src.energy;
        this.sprintConsume = src.sprintConsume;
        this.swimConsume = src.swimConsume;
        this.restTick = src.restTick;
        this.recoverSpeed = src.recoverSpeed;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("MAX_ENERGY", this.MAX_ENERGY);
        nbt.putInt("SPRINT_CONSUME", this.SPRINT_CONSUME);
        nbt.putInt("SWIM_CONSUME", this.SWIM_CONSUME);
        nbt.putInt("REST_TICK", this.REST_TICK);
        nbt.putInt("RECOVER_SPEED", this.RECOVER_SPEED);

        nbt.putInt("energy", this.energy);
        nbt.putInt("sprintTick", this.sprintConsume);
        nbt.putInt("swimTick", this.swimConsume);
        nbt.putInt("restTick", this.restTick);
        nbt.putInt("recoverTick", this.recoverSpeed);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.MAX_ENERGY = nbt.getInt("MAX_ENERGY");
        this.SPRINT_CONSUME = nbt.getInt("SPRINT_CONSUME");
        this.SWIM_CONSUME = nbt.getInt("SWIM_CONSUME");
        this.REST_TICK = nbt.getInt("REST_TICK");
        this.RECOVER_SPEED = nbt.getInt("RECOVER_SPEED");

        this.energy = nbt.getInt("energy");
        this.sprintConsume = nbt.getInt("sprintTick");
        this.swimConsume = nbt.getInt("swimTick");
        this.restTick = nbt.getInt("restTick");
        this.recoverSpeed = nbt.getInt("recoverTick");
    }

    public void loadBuf(FriendlyByteBuf buf) {
        this.MAX_ENERGY = buf.readInt();
        this.SPRINT_CONSUME = buf.readInt();
        this.SWIM_CONSUME = buf.readInt();
        this.REST_TICK = buf.readInt();
        this.RECOVER_SPEED = buf.readInt();

        this.energy = buf.readInt();
        this.sprintConsume = buf.readInt();
        this.swimConsume = buf.readInt();
        this.restTick = buf.readInt();
        this.recoverSpeed = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.MAX_ENERGY);
        buf.writeInt(this.SPRINT_CONSUME);
        buf.writeInt(this.SWIM_CONSUME);
        buf.writeInt(this.REST_TICK);
        buf.writeInt(this.RECOVER_SPEED);

        buf.writeInt(this.energy);
        buf.writeInt(this.sprintConsume);
        buf.writeInt(this.swimConsume);
        buf.writeInt(this.restTick);
        buf.writeInt(this.recoverSpeed);
    }

}
