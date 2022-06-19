package com.semivanilla.gpclaimlimits.listener;

import com.semivanilla.gpclaimlimits.config.Config;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.events.ClaimCreatedEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class GPListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onClaimCreatedEvent(ClaimCreatedEvent event) {

        if (!(event.getCreator() instanceof Player player)) return;

        if (player.hasPermission("semivanilla.claimlimit.bypass")) return;

        int numberOfClaimsAfterEvent = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId()).getClaims().size() + 1;
        int claimLimitFromPermission = -1;

        for (PermissionAttachmentInfo permissionAttachmentInfo : player.getEffectivePermissions()) {

            if (!permissionAttachmentInfo.getPermission().startsWith("semivanilla.claimlimit.")) continue;

            try {
                int claimLimitFromPermissionTemp = Integer.parseInt(permissionAttachmentInfo.getPermission().substring(23));
                if (claimLimitFromPermissionTemp > claimLimitFromPermission)
                    claimLimitFromPermission = claimLimitFromPermissionTemp;
            } catch (NumberFormatException ignored) {
                // log
            }

        }

        if (claimLimitFromPermission == -1) {
            return;
        }

        if (numberOfClaimsAfterEvent == claimLimitFromPermission) {
            player.sendRichMessage(Config.claimLimitReached);
        } else if (numberOfClaimsAfterEvent > claimLimitFromPermission) {
            player.sendRichMessage(Config.claimAboveLimit);
            event.setCancelled(true);
        }

    }

}
