package uyora.core.inventoryItems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uyora.core.Main;

public class CharacterInfo {

    private final Main main;

    public CharacterInfo(Main main){
        this.main = main;
    }

    public ItemStack infoBook(){
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(main.TCC("&e&lCharacter Menu"));
        meta.addItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }

}
