package uyora.core.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import uyora.core.Main;
import uyora.core.inventoryItems.CharacterInfo;
import uyora.core.playermenu.NavigationMenu;

public class ItemInteract implements Listener {

    private final Main main;

    public ItemInteract(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = player.getEquipment().getItemInMainHand();
        CharacterInfo infoItems = new CharacterInfo(main);

        //Info Book
        if (item.equals(infoItems.infoBook())){
            if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
                NavigationMenu menu = new NavigationMenu(main);
                event.setCancelled(true);
                player.openInventory(menu.menu(player));
            }
        }
    }


}
