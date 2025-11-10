package org.essentials.custom_background_music;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent; // ⬅️ NEW IMPORT
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Custom_background_music.MODID)
public class Custom_background_music {
    public static final String MODID = "custom_background_music";
    public static final Logger LOGGER = LoggerFactory.getLogger(Custom_background_music.MODID);

    public Custom_background_music(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Custom Background Music Mod initializing...");

        // Register the key mapping handler on the MOD Event Bus
        modEventBus.addListener(this::onRegisterKeyMappings); // ⬅️ ADD THIS LINE

        // Register config
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);

        // Register client tick event (handled on the NeoForge Event Bus)
        NeoForge.EVENT_BUS.addListener(this::onClientTick);
    }

    // 1. New method to handle the key registration
    private void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        LOGGER.debug("Registering Key Mapping: {}", KeyBindings.OPEN_MUSIC_GUI.getName());
        event.register(KeyBindings.OPEN_MUSIC_GUI);
    }

    // 2. Your existing client tick logic (handled on the NeoForge Event Bus)
    private void onClientTick(ClientTickEvent.Post event) {
        if (KeyBindings.OPEN_MUSIC_GUI.consumeClick()) {
            MusicGuiScreen.open();
        }
    }
}