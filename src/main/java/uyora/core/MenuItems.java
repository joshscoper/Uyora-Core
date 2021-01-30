package uyora.core;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuItems {

    private final Main main;

    public MenuItems(Main main){
        this.main = main;
    }

    public ItemStack blank(String color){
        String material = color.toUpperCase() + "_STAINED_GLASS_PANE";
        ItemStack item = new ItemStack(Material.valueOf(material));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createCharacter(){
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(main.TCC("&2&lCreate New Character"));
        item.setItemMeta(meta);
        return item;
    }




}
