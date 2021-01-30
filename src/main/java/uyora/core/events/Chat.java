package uyora.core.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import uyora.core.Main;
import uyora.core.chat.JSONHandler;

public class Chat implements Listener {

    private final Main main;

    public Chat(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        JSONHandler jsonHandler = new JSONHandler(player, main, event.getMessage());
        jsonHandler.sendMessage(event.getRecipients());
        event.setCancelled(true);
    }
}
