package ru.healthanmary.groupmetamanager.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class TabPrefixPlaceholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "groupmeta-tab-prefix";
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
        LuckPerms lp = LuckPermsProvider.get();
        User user = lp.getUserManager().getUser(player.getUniqueId());
        Group group = lp.getGroupManager().getGroup(user.getPrimaryGroup());
        String targetPermission = "tabprefix.";
        Collection<PermissionNode> nodes = group.getNodes(NodeType.PERMISSION);

        if (!nodes.isEmpty()) {
            for (PermissionNode permNode : nodes) {
                if (permNode.getKey().startsWith(targetPermission)) {
                    String prefix = permNode.getKey().replace(targetPermission, "");
                    return prefix.isEmpty() ? "" : " " + prefix;
                }
            }
        } else {
            return "";
        }
        return "";
    }
}
