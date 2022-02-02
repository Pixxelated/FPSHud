package me.pixxel.fps.mixins;

import me.pixxel.fps.FpsMod;
import me.pixxel.fps.config.FpsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow public abstract TextRenderer getTextRenderer();

    @Inject(at = @At("TAIL"), method = "render")
    public void renderFps(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        FpsConfig config = FpsMod.CONFIG;

        if (!mc.options.debugEnabled && config.enabled && config.opacity > 3 &&FpsMod.SHOW_FPS) {

            String fps = ((MinecraftClientAccessor) mc).getCurrentFps() + " FPS";
            float textPosX = 4, textPosY = 4;

            double guiScale = mc.getWindow().getScaleFactor();
            if (guiScale > 0) {
                textPosX /= guiScale;
                textPosY /= guiScale;
            }

            TextRenderer renderer = mc.textRenderer;
            int textColor = config.color | ((config.opacity & 0xFF) << 24);

            if (config.shadow && config.bg) { // With shadow, with bg
                DrawableHelper.fill(matrices, 4, 4, 53, 22, new Color(0, 0, 0, 150).getRGB());
                renderer.drawWithShadow(matrices, fps, 29 - renderer.getWidth(fps) / 2, 9, textColor);
            } else if (!config.shadow && !config.bg) { // Without shadow, without bg
                renderer.draw(matrices, fps, 4, 4, textColor);
            } else if (!config.shadow && config.bg) { //Without shadow, with bg
                DrawableHelper.fill(matrices, 4, 4, 53, 22, new Color(0, 0, 0, 150).getRGB());
                renderer.draw(matrices, fps, 29 - renderer.getWidth(fps) / 2, 9, textColor);
            } else if (!config.bg && config.shadow){ // With shadow, without bg
                renderer.drawWithShadow(matrices, fps, 4, 4, textColor);
            }
        }

    }

}