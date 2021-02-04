package uyora.core.playermenu;

import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import uyora.core.Main;

import java.util.ArrayList;

public class NavigationItems {

    private final Main main;

    public NavigationItems(Main main){
        this.main = main;
    }

    public ItemStack playerAttributes(Player player){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(player);
        meta.setDisplayName(main.TCC("&e&lCharacter Information"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack settings(){
        ItemStack item = new ItemStack(Material.MUSIC_DISC_MALL);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.values());
        meta.setDisplayName(main.TCC("&7&lSettings"));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack changeCharacter(){
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(main.TCC("&b&lChange Character"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(main.TCC("&aRequires &9&lAdventurer&r &aor higher to access."));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack store(){
        ItemStack item = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(main.TCC("&6&lStore"));
        item.setItemMeta(meta);
        return item;
    }

}
