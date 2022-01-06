package me.secretagent.floorchairlobby.listeners;

import me.secretagent.floorchairbase.api.FloorChairAPI;
import me.secretagent.floorchairbase.api.user.rank.FloorChairRank;
import me.secretagent.floorchairlobby.FloorChairLobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        FloorChairAPI api = new FloorChairAPI();
        event.setCancelled(true);
        Bukkit.broadcastMessage(FloorChairLobby.getPrefix(api.getUser(event.getPlayer()).getRank()) + event.getPlayer().getName() + ChatColor.RESET + ": " + event.getMessage());
    }

}
