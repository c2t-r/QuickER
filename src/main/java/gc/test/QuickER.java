package gc.test;

import emu.grasscutter.plugin.Plugin;
import gc.test.commands.QuickERCommand;

public final class QuickER extends Plugin {
    /* Turn the plugin into a singleton. */
    private static QuickER instance;

    public static QuickER getInstance() {
        return instance;
    }
    
    @Override public void onLoad() {
        // Set the plugin instance.
        instance = this;
    }

    @Override public void onEnable() {
        // Register commands.
        this.getHandle().registerCommand(new QuickERCommand());

        // Log a plugin status message.
        this.getLogger().info("QuickER has been enabled");
    }

    @Override public void onDisable() {
        // Log a plugin status message.
        this.getLogger().info("QuickER died XD");
    }

}
