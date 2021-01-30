package uyora.core.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import uyora.core.Main;
import uyora.core.charactercreation.CharacterCreationMenu;

public class NPCInteract implements Listener {

    private final Main main;

    public NPCInteract(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        Entity clicked = event.getRightClicked();
        if (clicked instanceof Player){
            if (clicked.getName().contains("Zara") && !Bukkit.getOnlinePlayers().contains(clicked)){
                CharacterCreationMenu menu = new CharacterCreationMenu(main);
                player.openInventory(menu.menu(player));
            }
        }
    }



}
