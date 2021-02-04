package uyora.core.inspect;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import uyora.core.Main;
import uyora.core.friends.FriendHandler;

public class InspectMenu implements Listener {

    private final Main main;
    private Player player;
    private Player viewer;

    public InspectMenu(Main main){
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    public Inventory menu(Player player, Player viewer){
        this.player = player;
        this.viewer = viewer;
        Inventory inv = Bukkit.createInventory(null, 54, main.TCC("&e&lInspecting &7" + player.getDisplayName()));
        InspectMenuItems items = new InspectMenuItems(main);
        ItemStack friendItem;

        //Head 13
        if (player.getEquipment().getHelmet() == null){
            inv.setItem(11, items.slot("Helmet"));
        } else {
            inv.setItem(11, player.getEquipment().getHelmet());
        }
        //Main Hand 21
        if (player.getEquipment().getItemInMainHand().getType() == Material.AIR){
            inv.setItem(19, items.slot("Main Hand"));
        } else {
            inv.setItem(19, player.getEquipment().getItemInMainHand());
        }
        //Chest 22
        if (player.getEquipment().getChestplate() == null){
            inv.setItem(20, items.slot("Chest"));
        } else {
            inv.setItem(20, player.getEquipment().getChestplate());
        }
        //Off Hand 23
        if (player.getEquipment().getItemInOffHand().getType() == Material.AIR){
            inv.setItem(21, items.slot("Off Hand"));
        } else {
            inv.setItem(21, player.getEquipment().getItemInOffHand());
        }
        //Legs 31
        if (player.getEquipment().getLeggings() == null){
            inv.setItem(29, items.slot("Legs"));
        } else {
            inv.setItem(29, player.getEquipment().getLeggings());
        }
        //Feet 40
        if (player.getEquipment().getBoots() == null){
            inv.setItem(38, items.slot("Feet"));
        } else {
            inv.setItem(38, player.getEquipment().getBoots());
        }
        viewer.openInventory(inv);
        viewer.playSound(viewer.getLocation(), Sound.BLOCK_CHEST_OPEN, 1,1);
        Bukkit.getScheduler().runTaskTimer(Main.getPlugin(Main.class), new Runnable() {
            @Override
            public void run() {

                if (viewer.getOpenInventory().getTopInventory().equals(inv)) {
                    InspectMenuItems items = new InspectMenuItems(main);
                    //Head 13
                    if (player.getEquipment().getHelmet() == null){
                        inv.setItem(11, items.slot("Helmet"));
                    } else {
                        inv.setItem(11, player.getEquipment().getHelmet());
                    }
                    //Main Hand 21
                    if (player.getEquipment().getItemInMainHand().getType() == Material.AIR){
                        inv.setItem(19, items.slot("Main Hand"));
                    } else {
                        inv.setItem(19, player.getEquipment().getItemInMainHand());
                    }
                    //Chest 22
                    if (player.getEquipment().getChestplate() == null){
                        inv.setItem(20, items.slot("Chest"));
                    } else {
                        inv.setItem(20, player.getEquipment().getChestplate());
                    }
                    //Off Hand 23
                    if (player.getEquipment().getItemInOffHand().getType() == Material.AIR){
                        inv.setItem(21, items.slot("Off Hand"));
                    } else {
                        inv.setItem(21, player.getEquipment().getItemInOffHand());
                    }
                    //Legs 31
                    if (player.getEquipment().getLeggings() == null){
                        inv.setItem(29, items.slot("Legs"));
                    } else {
                        inv.setItem(29, player.getEquipment().getLeggings());
                    }
                    //Feet 40
                    if (player.getEquipment().getBoots() == null){
                        inv.setItem(38, items.slot("Feet"));
                    } else {
                        inv.setItem(38, player.getEquipment().getBoots());
                    }
                }
            }
        },0,1);
        return inv;
    }

}
