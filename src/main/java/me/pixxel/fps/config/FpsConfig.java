package me.pixxel.fps.config;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "fps")
public class FpsConfig implements ConfigData {

    public boolean enabled = true;

    public int leftOffset = 4;

    public int topOffset = 4;

    public boolean shadow = false;

    @ConfigEntry.ColorPicker
    public int color = 0xFFFFFF;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int opacity = 255;

    public boolean bg = false;

    @ConfigEntry.ColorPicker
    public int bgColor = 0x000000;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int bgOpacity = 100;

    public boolean holdKeyToShowFps = false;
}