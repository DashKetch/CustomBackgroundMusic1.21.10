package org.essentials.custom_background_music;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = "custom_background_music", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindings {

    // Key mapping is lazily constructed so it doesn’t exist until registration
    public static final Lazy<KeyMapping> OPEN_MUSIC_GUI = Lazy.of(() -> {
        KeyMapping mapping = new KeyMapping(
                "key.custom_background_music.open_music_gui",  // translation key
                InputConstants.Type.KEYSYM,                   // keyboard input
                GLFW.GLFW_KEY_P,                              // default key
                "key.categories.misc"                         // category (string)
        );

        // Optional: only active when GUI screens are open
        mapping.setKeyConflictContext(KeyConflictContext.GUI);

        return mapping;
    });

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_MUSIC_GUI.get());
    }
}
