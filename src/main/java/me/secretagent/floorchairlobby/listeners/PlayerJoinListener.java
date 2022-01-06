package me.secretagent.floorchairlobby.listeners;

import me.secretagent.floorchairbase.api.user.FloorChairUser;
import me.secretagent.floorchairlobby.FloorChairLobby;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final FloorChairLobby plugin = FloorChairLobby.getPlugin();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.addScoreboard(event.getPlayer());
        Player player = event.getPlayer();
        FloorChairUser user = plugin.getApi().getUser(player);
        player.setPlayerListName(FloorChairLobby.getPrefix(user.getRank()) + player.getName());
        player.setPlayerListHeader(ChatColor.YELLOW + "" + ChatColor.BOLD + "FloorChair MC");
        player.setPlayerListFooter(ChatColor.YELLOW + "play.floorchair.net");
    }

}
