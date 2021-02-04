package uyora.core.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import uyora.core.Main;
import uyora.core.filemanagement.CharacterFileManager;
import uyora.core.filemanagement.PlayerFileManager;

public class Death implements Listener {

    private final Main main;

    public Death(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        PlayerFileManager manager = new PlayerFileManager(main, player);
        CharacterFileManager characterFileManager = new CharacterFileManager(main, manager.getActive(), player);
        player.spigot().respawn();
        if (characterFileManager.getInn() != null) {
            player.teleport(characterFileManager.getInn());
            player.sendMessage(main.TCC("&9&lUyora &7» &cYou've been respawned at your Inn."));
        } else {
            player.teleport(Bukkit.getWorld("Uyora").getSpawnLocation());
            player.sendMessage(main.TCC("&9&lUyora &7» &cYou don't have an Inn set so you've been sent back to spawn."));
            player.sendMessage(main.TCC("&cYou should speak to an &2&lInn Keeper&c to set your respawn location."));
        }
    }

}
