package uyora.core.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import uyora.core.Main;

public class Quit implements Listener {

    private final Main main;

    public Quit(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        //Quit Message
        event.setQuitMessage(main.TCC("&8" + player.getDisplayName() + " &chas left Uyora!"));
    }



}
