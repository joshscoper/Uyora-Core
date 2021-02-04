package uyora.core.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import uyora.core.Main;
import uyora.core.charactercreation.CharacterCreationMenu;
import uyora.core.filemanagement.PlayerFileManager;
import uyora.core.inventoryItems.CharacterInfo;
import uyora.core.scoreboard.ScoreboardHandler;

public class Join implements Listener {

    private final Main main;

    public Join(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (!player.hasPermission("uyora.staff")) {
            player.teleport(Bukkit.getWorld("Uyora").getSpawnLocation());
        }
        //Join Message
        event.setJoinMessage(main.TCC("&8" + player.getDisplayName() + "&a has joined Uyora!"));

        //Load Player File
        PlayerFileManager manager = new PlayerFileManager(main, player);
        manager.loadPlayerFile();
        manager.setActive(player, 0);

        //Player Items
        CharacterInfo infoItems = new CharacterInfo(main);
        Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
            @Override
            public void run() {
                if (player.getInventory().getItem(8) != infoItems.infoBook()){
                    player.getInventory().setItem(8, infoItems.infoBook());
                }
            }
        },20);

        //Scoreboard
        ScoreboardHandler scoreboard = new ScoreboardHandler(main, player);
        scoreboard.updater(player);
    }



}
