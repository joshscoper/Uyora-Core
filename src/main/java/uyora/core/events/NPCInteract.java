package uyora.core.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import uyora.core.Main;
import uyora.core.charactercreation.CharacterCreationMenu;
import uyora.core.filemanagement.CharacterFileManager;
import uyora.core.filemanagement.PlayerFileManager;
import uyora.core.inspect.InspectMenu;

public class NPCInteract implements Listener {

    private final Main main;

    public NPCInteract(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        Entity clicked = event.getRightClicked();
        if (clicked instanceof Villager){
            if (clicked.getName().equals(main.TCC("&7&lPaladin"))){
                PlayerFileManager playerFileManager = new PlayerFileManager(main, player);
                CharacterFileManager characterFileManager = new CharacterFileManager(main, playerFileManager.getActive(), player);
                String cls = ChatColor.stripColor(clicked.getName());
                characterFileManager.setClazz(cls);
                player.sendMessage(main.TCC("&9&lUyora &7» &aYou have chosen to be a &e&lPaladin&a!"));
            }
        }
        if (clicked instanceof Player) {
            if (event.getHand().equals(EquipmentSlot.HAND)) {
                if (Bukkit.getOnlinePlayers().contains(clicked)) {
                    InspectMenu menu = new InspectMenu(main);
                    player.openInventory(menu.menu((Player) clicked, player));
                }
                if (clicked.getName().equals(main.TCC("&9&lZara")) && !Bukkit.getOnlinePlayers().contains(clicked)) {
                    CharacterCreationMenu menu = new CharacterCreationMenu(main);
                    player.openInventory(menu.menu(player));
                }
                if (clicked.getName().equals(main.TCC("&2&lInn Keeper")) && !Bukkit.getOnlinePlayers().contains(clicked)) {
                    PlayerFileManager manager = new PlayerFileManager(main, player);
                    CharacterFileManager characterFileManager = new CharacterFileManager(main, manager.getActive(), player);
                    characterFileManager.setInn();
                    player.sendMessage(main.TCC("&9&lUyora &7» &eYour Inn location has been set!"));
                }
            }
        }
    }



}
