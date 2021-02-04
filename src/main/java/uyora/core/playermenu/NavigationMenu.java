package uyora.core.playermenu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import uyora.core.Main;

public class NavigationMenu {

    private final Main main;

    public NavigationMenu(Main main){
        this.main = main;
    }

    public Inventory menu(Player player){
        Inventory inv = Bukkit.createInventory(null, 27, main.TCC("         &3&lNavigation Menu"));
        NavigationItems items = new NavigationItems(main);
        inv.setItem(10, items.playerAttributes(player));
        inv.setItem(12, items.settings());
        inv.setItem(14, items.changeCharacter());
        inv.setItem(16, items.store());
        return inv;
    }

}
