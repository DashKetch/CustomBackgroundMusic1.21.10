package org.essentials.custom_background_music;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(CustomBackgroundMusic.MODID)
public class CustomBackgroundMusic {
    public static final String MODID = "custom_background_music";
    public static final Logger LOGGER = LoggerFactory.getLogger(CustomBackgroundMusic.MODID);

    public CustomBackgroundMusic(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Custom Background Music Mod initializing...");

        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);

        // Register keybindings on the mod event bus
        modEventBus.addListener(KeyBindings::registerBindings);

        // Tick listener for detecting key presses
        NeoForge.EVENT_BUS.addListener(this::onClientTick);
    }

    private void onClientTick(ClientTickEvent.Post event) {
        // Must use .get() because OPEN_MUSIC_GUI is Lazy<KeyMapping>
        if (KeyBindings.OPEN_MUSIC_GUI.get().consumeClick()) {
            MusicGuiScreen.open();
        }
    }
}
