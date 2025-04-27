package ru.healthanmary.groupmetamanager;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ru.healthanmary.groupmetamanager.placeholders.*;

public final class GroupMetaManager extends JavaPlugin {
    private boolean setupPlugins() {
        PluginManager pluginManager = getServer().getPluginManager();
        return pluginManager.getPlugin("PlaceholderAPI") != null || pluginManager.getPlugin("LuckPerms") != null;
    }
    @Override
    public void onEnable() {
        if (!setupPlugins()) {
            getServer().getLogger().warning("Plugin is disabled. One of the next plugins isn't loaded: PlaceholderAPI, LuckPerms");
            getServer().getPluginManager().disablePlugin(this);
        }
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new MainPlaceholder().register();
        }
    }

    @Override
    public void onDisable() {
    }
}
