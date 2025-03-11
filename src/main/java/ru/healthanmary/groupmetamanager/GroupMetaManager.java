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
            getServer().getLogger().warning("Плагин выключен. Один из следующих плагинов отсутствует: PlaceholderAPI, LuckPerms");
            getServer().getPluginManager().disablePlugin(this);
        }
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();
            // зачем нужно - хз, но пуст будет
        }
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new TabPrefixPlaceholder().register();
            new BoardPrefixPlaceholder().register();
            new ChatPrefixPlaceholder().register();
            new HeadPrefixPlaceholder().register();
//            new BoardSuffixPlaceholder().register();
//            new ChatSuffixPlaceholder().register();
//            new TabSuffixPlaceholder().register();
//            new HeadSuffixPlaceholder().register();
        }
    }

    @Override
    public void onDisable() {
    }
}
