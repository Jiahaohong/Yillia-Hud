package com.yillia.hud.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class PlayerTemp {
    public final int MAX_TEMP = 45;
    public final int MIN_TEMP = -45;
    public int MIN_HOT_TEMP = 20;
    public int MAX_COLD_TEMP = -20;
    private int temp;
    public final int MAX_TICK = 140;
    public int coldTick;
    public int hotTick;

    public PlayerTemp() {
        temp = 0;
        coldTick = 0;
        hotTick = 0;
    }

    public int getTemp() {
        return this.temp;
    }

    public void addTemp(int delta) {
        this.temp = Math.min(this.temp + delta, MAX_TEMP);
    }

    public void subTemp(int delta) {
        this.temp = Math.max(this.temp - delta, MIN_TEMP);
    }

    public void copyFrom(PlayerTemp src) {
        this.MIN_HOT_TEMP = src.MIN_HOT_TEMP;
        this.MAX_COLD_TEMP = src.MAX_COLD_TEMP;
        this.temp = src.temp;
        this.coldTick = src.coldTick;
        this.hotTick = src.hotTick;
    }

    public void saveNBTData(CompoundTag nbt) {
         nbt.putInt("MIN_HOT_TEMP", this.MIN_HOT_TEMP);
         nbt.putInt("MAX_COLD_TEMP", this.MAX_COLD_TEMP);
         nbt.putInt("temp", this.temp);
         nbt.putInt("coldTick", this.coldTick);
         nbt.putInt("hotTick", this.hotTick);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.MIN_HOT_TEMP = nbt.getInt("MIN_HOT_TEMP");
        this.MAX_COLD_TEMP = nbt.getInt("MAX_COLD_TEMP");
        this.temp = nbt.getInt("temp");
        this.coldTick = nbt.getInt("coldTick");
        this.hotTick = nbt.getInt("hotTick");
    }

    public void loadBuf(FriendlyByteBuf buf) {
        this.MIN_HOT_TEMP = buf.readInt();
        this.MAX_COLD_TEMP = buf.readInt();
        this.temp = buf.readInt();
        this.coldTick = buf.readInt();
        this.hotTick = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.MIN_HOT_TEMP);
        buf.writeInt(this.MAX_COLD_TEMP);
        buf.writeInt(this.temp);
        buf.writeInt(this.coldTick);
        buf.writeInt(this.hotTick);
    }
}
