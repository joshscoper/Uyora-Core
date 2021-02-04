package uyora.core.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import uyora.core.Main;
import uyora.core.inventoryItems.CharacterInfo;

public class Drop implements Listener {

    private final Main main;

    public Drop(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        ItemStack dropped = event.getItemDrop().getItemStack();

        //Blacklisted Drops
        CharacterInfo infoItems = new CharacterInfo(main);
        if (dropped.equals(infoItems.infoBook())){
            event.setCancelled(true);
        }
    }

}
