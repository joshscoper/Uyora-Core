package uyora.core.settingsmenu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import uyora.core.Main;


public class SettingsMenu {

    private final Main main;

    public SettingsMenu(Main main){
        this.main = main;
    }

    public Inventory menu(Player player){
        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, main.TCC("              &7&lSettings"));
        SettingsItems items = new SettingsItems(main, player);
        inv.setItem(0, items.hidePlayers());
        inv.setItem(2, items.pvp());
        inv.setItem(4, items.streamerMode());
        return inv;
    }
}
