package uyora.core.charactercreation;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import uyora.core.Main;

public class CharacterDeleteConfirmationMenu {

    private Main main;
    public int character;

    public CharacterDeleteConfirmationMenu(Main main){
        this.main = main;
        this.character = character;
    }

    public Inventory menu(){
        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, main.TCC("            &eAre you sure?"));
        inv.setItem(0, main.getMenuItems().blank("black"));
        inv.setItem(2, main.getMenuItems().blank("black"));
        inv.setItem(4, main.getMenuItems().blank("black"));
        inv.setItem(1, main.getMenuItems().confirm());
        inv.setItem(3, main.getMenuItems().cancel());
        return inv;
    }

}
