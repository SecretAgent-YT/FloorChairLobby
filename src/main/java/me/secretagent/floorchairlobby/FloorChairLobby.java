package me.secretagent.floorchairlobby;

import dev.jcsoftware.jscoreboards.JPerPlayerScoreboard;
import me.secretagent.floorchairbase.api.FloorChairAPI;
import me.secretagent.floorchairbase.api.user.FloorChairUser;
import me.secretagent.floorchairbase.api.user.rank.FloorChairRank;
import me.secretagent.floorchairlobby.listeners.BlockBreakListener;
import me.secretagent.floorchairlobby.listeners.BlockPlaceListener;
import me.secretagent.floorchairlobby.listeners.PlayerChatListener;
import me.secretagent.floorchairlobby.listeners.PlayerJoinListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;

public final class FloorChairLobby extends JavaPlugin {

    private JPerPlayerScoreboard scoreboard;
    private static FloorChairLobby plugin;
    private FloorChairAPI api;

    @Override
    public void onEnable() {
        plugin = this;
        api = new FloorChairAPI(floorChairAPI -> {
            enableScoreboard();
        });
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
    }

    private void enableScoreboard() {
        scoreboard = new JPerPlayerScoreboard(
                (player) -> "&e&lFloorChair MC",
                (player) -> {
                    FloorChairUser user = api.getUser(player);
                    return Arrays.asList("&bRank: " + getPrefix(user.getRank()).replace(" ", ""), "&bXP: &a" + user.getXP(), "&eplay.floorchair.net");
                }
        );
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                scoreboard.updateScoreboard();
            }
        }.runTaskTimer(this, 100L, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void addScoreboard(Player player) {
        scoreboard.addPlayer(player);
    }

    public static FloorChairLobby getPlugin() {
        return plugin;
    }


    public static String getPrefix(FloorChairRank rank) {
        switch (rank) {
            case DEFAULT:
                return ChatColor.translateAlternateColorCodes('&', "&8[DEFAULT] ");
            case FLOOR:
                return ChatColor.translateAlternateColorCodes('&', "&6[FLOOR] ");
            case CHAIR:
                return ChatColor.translateAlternateColorCodes('&', "&2[CHAIR] ");
            case HELPER:
                return ChatColor.translateAlternateColorCodes('&', "&5[HELPER] ");
            case BUILDER:
                return ChatColor.translateAlternateColorCodes('&', "&b[BUILDER] ");
            case ADMIN:
                return ChatColor.translateAlternateColorCodes('&', "&c[ADMIN] ");
            case OWNER:
                return ChatColor.translateAlternateColorCodes('&', "&4[OWNER] ");
        }
        return null;
    }

    public FloorChairAPI getApi() {
        return api;
    }

}
