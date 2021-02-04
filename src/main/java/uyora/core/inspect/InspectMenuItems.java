package uyora.core.inspect;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uyora.core.Main;

public class InspectMenuItems {

    private final Main main;

    public InspectMenuItems(Main main){
        this.main = main;
    }

    public ItemStack slot(String slot){
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(main.TCC("&7&o" + slot));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack addFriend(){
        ItemStack item = new ItemStack(Material.GREEN_WOOL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(main.TCC("&a&lAdd Friend"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack removeFriend(){
        ItemStack item = new ItemStack(Material.RED_WOOL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(main.TCC("&c&lRemove Friend"));
        item.setItemMeta(meta);
        return item;
    }


}
