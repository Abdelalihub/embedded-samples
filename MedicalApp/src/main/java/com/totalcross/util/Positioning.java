package com.totalcross.util;

import totalcross.sys.Settings;

public class Positioning {
    public static int getWidthDP(int value) {
        return value*960/Settings.screenWidth;
    }
    public static int getHeightDP(int value) {
        return value*640/Settings.screenHeight;
    }
}