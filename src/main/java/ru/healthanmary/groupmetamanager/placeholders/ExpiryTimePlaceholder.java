package ru.healthanmary.groupmetamanager.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExpiryTimePlaceholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "expiry-group-time";
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
}
