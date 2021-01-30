package uyora.core.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import uyora.core.Main;

public class InventoryInteract implements Listener {

    private final Main main;

    public InventoryInteract(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onInteract(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory clicked = event.getInventory();

        //Character Creation/Selection Menu
        Inventory charCreate = main.getCharacterCreationMenu().menu(player);
        if (event.getView().getTitle().equalsIgnoreCase(main.TCC("     &a&lSelect Your Character"))){
            event.setCancelled(true);
            //TODO Do Stuff
            player.closeInventory();
            if (event.getCurrentItem().equals(main.getMenuItems().createCharacter())) {
                player.sendMessage(main.TCC("&e&lDEBUG &7» &aJosh is working on this feature!"));
            }
        }


    }


}
