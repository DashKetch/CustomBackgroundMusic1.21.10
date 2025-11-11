package org.essentials.custom_background_music;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static KeyMapping OPEN_MUSIC_GUI;

    @SuppressWarnings("unused")
    public static void register(RegisterKeyMappingsEvent event) {
        OPEN_MUSIC_GUI = new KeyMapping(
                "key.custom_background_music.open_gui",
                KeyConflictContext.UNIVERSAL,
                InputConstants.getKey(GLFW.GLFW_KEY_M, 0),
                "key.categories.custom_background_music"
        );
        event.register(OPEN_MUSIC_GUI);
    }
}