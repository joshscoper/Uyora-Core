package uyora.core.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import uyora.core.Main;
import uyora.core.filemanagement.CharacterFileManager;
import uyora.core.filemanagement.PlayerFileManager;

public class Quit implements Listener {

    private final Main main;

    public Quit(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        PlayerFileManager manager = new PlayerFileManager(main, player);

        //Quit Message
        event.setQuitMessage(main.TCC("&8" + player.getDisplayName() + " &chas left Uyora!"));
        CharacterFileManager characterFileManager = new CharacterFileManager(main, manager.getActive(), player);
        characterFileManager.setLocation();
        manager.getData().set("Active_Character", 1);
    }



}
