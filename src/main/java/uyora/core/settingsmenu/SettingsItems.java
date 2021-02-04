package uyora.core.settingsmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uyora.core.Main;
import uyora.core.filemanagement.PlayerFileManager;

import java.util.ArrayList;

public class SettingsItems {

    private final Main main;
    private PlayerFileManager manager;

    public SettingsItems(Main main, Player player){
        this.main = main;
        manager = new PlayerFileManager(main, player);
    }

    public ItemStack pvp(){
        ItemStack item;
        if (manager.getData().getBoolean("Settings.PvP") == false || manager.getData().get("Settings.PvP") == null){
            item = new ItemStack(Material.GRAY_TERRACOTTA);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(main.TCC("&c&lPvP"));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(main.TCC("&7Disabled"));
            meta.setLore(lore);
            item.setItemMeta(meta);
        } else {
            item = new ItemStack(Material.GREEN_TERRACOTTA);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(main.TCC("&c&lPvP"));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(main.TCC("&aEnabled"));
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    public ItemStack hidePlayers(){
        ItemStack item;
        if (manager.getData().getBoolean("Settings.Hide_Players") == false || manager.getData().get("Settings.Hide_Players") == null){
            item = new ItemStack(Material.GRAY_TERRACOTTA);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(main.TCC("&e&lHide Players"));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(main.TCC("&7Disabled"));
            meta.setLore(lore);
            item.setItemMeta(meta);
        } else {
            item = new ItemStack(Material.GREEN_TERRACOTTA);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(main.TCC("&e&lHide Players"));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(main.TCC("&aEnabled"));
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    public ItemStack streamerMode(){
        ItemStack item;
        if (manager.getData().getBoolean("Settings.Streamer_Mode") == false || manager.getData().get("Settings.Streamer_Mode") == null){
            item = new ItemStack(Material.GRAY_TERRACOTTA);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(main.TCC("&d&lStreamer Mode"));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(main.TCC("&7Disabled"));
            meta.setLore(lore);
            item.setItemMeta(meta);
        } else {
            item = new ItemStack(Material.GREEN_TERRACOTTA);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(main.TCC("&d&lStreamer Mode"));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(main.TCC("&aEnabled"));
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

}
