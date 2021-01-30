package uyora.core.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import uyora.core.Main;
import uyora.core.charactercreation.CharacterCreationMenu;

public class Join implements Listener {

    private final Main main;

    public Join(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        //Join Message
        event.setJoinMessage(main.TCC("&8" + player.getDisplayName() + "&a has joined Uyora!"));

        //Create Player File
        if (!main.getPlayerFileManager().getPlayerFile(player).exists()){main.getPlayerFileManager().createPlayerFile(player);}

        //Character Selection
        player.openInventory(main.getCharacterCreationMenu().menu(player));
    }



}
