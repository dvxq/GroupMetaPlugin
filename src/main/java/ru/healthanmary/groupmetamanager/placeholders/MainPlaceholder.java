package ru.healthanmary.groupmetamanager.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.healthanmary.groupmetamanager.api.GroupMetaAPI;

import java.util.Collection;

public class MainPlaceholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "groupmeta";
    }

    @Override
    public @NotNull String getAuthor() {
        return "author";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }
    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        switch (params) {
            case "tabprefix": return GroupMetaAPI.getTabPrefix(player);
            case "tabsuffix": return GroupMetaAPI.getTabSuffix(player);
            case "chatsuffix": return GroupMetaAPI.getChatSuffix(player);
            case "chatprefix": return GroupMetaAPI.getChatPrefix(player);
            case "boardprefix": return GroupMetaAPI.getBoardPrefix(player);
            case "boardsuffix": return GroupMetaAPI.getBoardSuffix(player);
            case "headprefix": return GroupMetaAPI.getHeadPrefix(player);
            case "headsuffix": return GroupMetaAPI.getHeadSuffix(player);
            case "remaining_time": return GroupMetaAPI.getRemainingTime(player);
            default: return "";
        }
    }
}
