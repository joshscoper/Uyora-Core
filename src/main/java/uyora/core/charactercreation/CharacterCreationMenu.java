package uyora.core.charactercreation;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uyora.core.Main;
import uyora.core.filemanagement.CharacterFileManager;

import javax.naming.InvalidNameException;
import java.util.ArrayList;

public class CharacterCreationMenu {

    private final Main main;

    public CharacterCreationMenu(Main main){
        this.main = main;
    }

    public Inventory menu(Player player){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, main.TCC("     &a&lSelect Your Character"));
        ItemStack item;
        for (int i = 0; i < inventory.getSize(); i++){
            CharacterFileManager charFile = new CharacterFileManager(main, i + 1, player);
            if (charFile.characterExists()){
                item = new ItemStack(Material.PAPER);
                ItemMeta meta = item.getItemMeta();
                int character = i + 1;
                meta.setDisplayName(main.TCC("&3&lCharacter &7[&b" + character + "&7]"));
                ArrayList<String> lore = new ArrayList();
                String clazz = charFile.getClazz();
                if (clazz != null){
                    lore.add(main.TCC("&a&lClass &7» &e" + clazz));
                } else {
                    lore.add(main.TCC("&a&lClass &7» &cError Loading Information"));
                }
                lore.add(main.TCC("&a&lLevel &7» &e" + charFile.getLevel()));
                lore.add(main.TCC("&cRight-Click to delete"));
                meta.setLore(lore);
                item.setItemMeta(meta);

            } else {
                item = main.getMenuItems().createCharacter();
            }
            inventory.setItem(i, item);
        }
        return inventory;
    }


}
