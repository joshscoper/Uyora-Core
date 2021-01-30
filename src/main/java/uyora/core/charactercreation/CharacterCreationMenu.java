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
            CharacterFileManager charFile = new CharacterFileManager(main, i, player);
            if (charFile.characterExists()){
                item = new ItemStack(Material.PAPER);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(main.TCC("&3&lCharacter &7[&b" + i + "&7]"));
                ArrayList<String> lore = new ArrayList();
                lore.add(main.TCC("&e&lClass: &7" + charFile.getClazz()));
                lore.add(main.TCC("&e&lLevel: &7" + charFile.getCombatLevel()));
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
