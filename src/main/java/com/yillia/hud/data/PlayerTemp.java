package com.yillia.hud.data;

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
}
