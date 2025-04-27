package ru.healthanmary.groupmetamanager.api;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collection;

public class GroupMetaAPI {
    private static String getPrefix(Player player, String perm) {
        LuckPerms lp = LuckPermsProvider.get();
        User user = lp.getUserManager().getUser(player.getUniqueId());
        Group group = lp.getGroupManager().getGroup(user.getPrimaryGroup());
        Collection<PermissionNode> nodes = group.getNodes(NodeType.PERMISSION);

        if (!nodes.isEmpty()) {
            for (PermissionNode permNode : nodes) {
                if (permNode.getKey().startsWith(perm)) {
                    String prefix = permNode.getKey().replace(perm, "");
                    return prefix.isEmpty() ? "" : " " + prefix;
                }
            }
        } else {
            return "";
        }
        return "";
    }
    private static String getSuffix(Player player, String perm) {
        LuckPerms lp = LuckPermsProvider.get();
        User user = lp.getUserManager().getUser(player.getUniqueId());
        Group group = lp.getGroupManager().getGroup(user.getPrimaryGroup());
        Collection<PermissionNode> nodes = group.getNodes(NodeType.PERMISSION);

        if (!nodes.isEmpty()) {
            for (PermissionNode permNode : nodes) {
                if (permNode.getKey().startsWith(perm)) {
                    String prefix = permNode.getKey().replace(perm, "");
                    return prefix.isEmpty() ? "" : " " + prefix;
                }
            }
        } else {
            return "";
        }
        return "";
    }
    public static String getRemainingTime(Player player) {
        LuckPerms lp = LuckPermsProvider.get();
        User user = lp.getUserManager().getUser(player.getUniqueId());
        Group group = lp.getGroupManager().getGroup(user.getPrimaryGroup());

        if (group.getName().equalsIgnoreCase("default")) return "";
        return user.getNodes().stream()
                .filter(node -> node.getKey().replace("group.", "").equalsIgnoreCase(user.getPrimaryGroup()))
                .filter(node -> node.getType() == NodeType.INHERITANCE)
                .findFirst()
                .map(node -> {
                    if (node.hasExpiry()) {
                        return node.getExpiryDuration().toDays() + 1 + "д.";
                    } else {
                        return "∞";
                    }
                })
                .orElse("∞");
    }

    public static String getTabPrefix(Player player) {
        return getPrefix(player, "tabprefix.");
    }
    public static String getChatPrefix(Player player) {
        return getPrefix(player, "chatprefix.");
    }
    public static String getHeadPrefix(Player player) {
        return getPrefix(player, "headprefix.");
    }
    public static String getBoardPrefix(Player player) {
        String suffix = getSuffix(player, "boardsuffix.");
        return suffix.isEmpty() ? " Отсутствует" : suffix;
    }
    public static String getTabSuffix(Player player) {
        return getSuffix(player, "tabsuffix.");
    }
    public static String getChatSuffix(Player player) {
        return getSuffix(player, "chatsuffix.");
    }
    public static String getHeadSuffix(Player player) {
        return getSuffix(player, "headsuffix.");
    }
    public static String getBoardSuffix(Player player) {
        String suffix = getSuffix(player, "boardsuffix.");
        return suffix.isEmpty() ? " Отсутствует" : suffix;
    }
}
