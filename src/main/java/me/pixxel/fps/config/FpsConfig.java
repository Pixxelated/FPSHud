package me.pixxel.fps.config;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "fps")
public class FpsConfig implements ConfigData {

    public boolean enabled = true;

    public boolean shadow = false;

    public boolean bg = false;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int opacity = 255;

    @ConfigEntry.ColorPicker
    public int color = 0xFFFFFF;

    public boolean holdKeyToShowFps = false;
}