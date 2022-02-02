package me.pixxel.fps;

import me.pixxel.fps.config.FpsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class FpsMod implements ClientModInitializer {

    public String MOD_ID = "fps-hud";
    public String NAME = "FPS Hud", VERSION = "v1.0", AUTHOR = "Pixxel", NAMEVER = NAME + VERSION;
    public static final Logger LOGGER = LoggerFactory.getLogger("fps");
    public static Boolean SHOW_FPS;
    public static FpsConfig CONFIG;

    @Override
    public void onInitializeClient() {

        LOGGER.info("Initializing " + NAMEVER + " by " + AUTHOR);

        AutoConfig.register(FpsConfig.class, GsonConfigSerializer::new);
            CONFIG = AutoConfig.getConfigHolder(FpsConfig.class).getConfig();

            KeyBinding binding_toggleOverlay = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.fps.toggleFpsOverlay", InputUtil.Type.KEYSYM, GLFW.GLFW_DONT_CARE, "key.fps.category"));

            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                while (binding_toggleOverlay.wasPressed() && !CONFIG.holdKeyToShowFps) {
                    CONFIG.enabled = !CONFIG.enabled;
                }
                if (CONFIG.holdKeyToShowFps) {
                    SHOW_FPS = binding_toggleOverlay.isPressed();
                } else {
                    SHOW_FPS = CONFIG.enabled;
                }
            });
        }

    }
