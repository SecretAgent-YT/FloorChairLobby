package me.secretagent.floorchairlobby.listeners;

import me.secretagent.floorchairbase.api.user.rank.FloorChairRank;
import me.secretagent.floorchairlobby.FloorChairLobby;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        FloorChairRank rank = FloorChairLobby.getPlugin().getApi().getUser(player).getRank();
        if (rank.getLevel() >= 3 && rank.getLevel() != 4) return;
        event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "You are not allowed to place blocks!");
    }

}
