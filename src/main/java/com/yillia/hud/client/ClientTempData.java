package com.yillia.hud.client;

import com.yillia.hud.data.PlayerTemp;

public class ClientTempData {
    private static PlayerTemp tempData = new PlayerTemp();

    public static void set(PlayerTemp src) {
        tempData.copyFrom(src);
    }

    public static int getTemp() {
        return tempData.getTemp();
    }

    public static int getMax() {
        return tempData.MAX_TEMP;
    }

    public static int getMin() {
        return tempData.MIN_TEMP;
    }

    public static int getMinHotTemp() {
        return tempData.MIN_HOT_TEMP;
    }

    public static int getMaxColdTemp() {
        return tempData.MAX_COLD_TEMP;
    }
}
