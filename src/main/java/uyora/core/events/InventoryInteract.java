package uyora.core.events;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import uyora.core.Main;
import uyora.core.charactercreation.CharacterCreationMenu;
import uyora.core.charactercreation.CharacterDeleteConfirmationMenu;
import uyora.core.filemanagement.CharacterFileManager;
import uyora.core.filemanagement.PlayerFileManager;
import uyora.core.inventoryItems.CharacterInfo;
import uyora.core.settingsmenu.SettingsMenu;


public class InventoryInteract implements Listener {

    private final Main main;

    public InventoryInteract(Main main){
        this.main = main;
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onInteract(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory clicked = event.getInventory();
        PlayerFileManager manager = new PlayerFileManager(main, player);
        CharacterInfo infoItems = new CharacterInfo(main);

        //Settings Menu
        if (event.getView().getTitle().equals(main.TCC("              &7&lSettings"))){
            event.setCancelled(true);
            if (event.getSlot() == 0){
                if (manager.getData().getBoolean("Settings.Hide_Players") == false){
                    manager.setHidePlayers(true);
                    player.sendMessage(main.TCC("&9&lUyora &7» &eHide Players has been Enabled!"));
                } else {
                    manager.setHidePlayers(false);
                    player.sendMessage(main.TCC("&9&lUyora &7» &eHide Players has been Disabled!"));
                }
                player.openInventory(new SettingsMenu(main).menu(player));
            }
            if (event.getSlot() == 2){
                if (manager.getData().getBoolean("Settings.PvP") == false){
                    manager.setPvP(true);
                    player.sendMessage(main.TCC("&9&lUyora &7» &ePvP has been Enabled!"));
                } else {
                    manager.setPvP(false);
                    player.sendMessage(main.TCC("&9&lUyora &7» &ePvP has been Disabled!"));
                }
                player.openInventory(new SettingsMenu(main).menu(player));
            }
            if (event.getSlot() == 4){
                if (manager.getData().getBoolean("Settings.Streamer_Mode") == false){
                    manager.setStreamerMode(true);
                    player.sendMessage(main.TCC("&9&lUyora &7» &eStreamer Mode has been Enabled!"));
                } else {
                    manager.setStreamerMode(false);
                    player.sendMessage(main.TCC("&9&lUyora &7» &eStreamer Mode has been Disabled!"));
                }
                player.openInventory(new SettingsMenu(main).menu(player));
            }

        }

        //Navigation Menu
        if (event.getView().getTitle().equals(main.TCC("         &3&lNavigation Menu"))){
            event.setCancelled(true);
            if (event.getSlot() == 12){
                SettingsMenu menu = new SettingsMenu(main);
                player.openInventory(menu.menu(player));
            }
            if (event.getSlot() == 14){
                if (player.hasPermission("uyora.navmenu.characterswap")) {
                    manager.setActive(player, 0);
                    player.openInventory(main.getCharacterCreationMenu().menu(player));
                } else {
                    player.sendMessage(main.TCC("&9&lUyora &7» &aThis feature can be unlocked with the &9&lAdventurer &arank."));
                }
            }
            if (event.getSlot() == 16){
                TextComponent store = new TextComponent(main.TCC("&9&lUyora &7» &f&l[LINK]"));
                store.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://uyoraservernetwork.buycraft.net/"));
                player.spigot().sendMessage(store);
                player.closeInventory();
            }
        }

        //Character Creation/Selection Menu
        Inventory charCreate = main.getCharacterCreationMenu().menu(player);
        if (event.getCurrentItem().equals(infoItems.infoBook())){
            event.setCancelled(true);
            event.getCursor().setType(null);
        }
        if (event.getView().getTitle().equalsIgnoreCase(main.TCC("     &a&lSelect Your Character"))){
            event.setCancelled(true);
            player.closeInventory();
            if (event.getCurrentItem().equals(main.getMenuItems().createCharacter())) {
                player.teleport(Bukkit.getWorld("Uyora").getSpawnLocation());
                player.sendMessage(main.TCC("&9&lUyora &7» &aCharacter " + (manager.getCharacters() + 1) + " has been created!"));
                CharacterFileManager characterFileManager = new CharacterFileManager(main, manager.getCharacters() + 1, player);
                characterFileManager.createCharacterFile();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1,1);
            } else {
                if (event.getClickedInventory().equals(event.getView().getTopInventory())) {
                    CharacterFileManager characterFileManager = new CharacterFileManager(main, event.getSlot() + 1, player);
                    if (event.getClick().isRightClick()) {
                        CharacterDeleteConfirmationMenu confirm = new CharacterDeleteConfirmationMenu(main);
                        main.confirmDeleteMap.put(player, event.getSlot() + 1);
                        player.openInventory(confirm.menu());
                    } else {
                        manager.setActive(player, event.getSlot() + 1);
                        player.teleport(characterFileManager.getLocation());
                        player.sendMessage(main.TCC("&9&lUyora &7» &aCharacter " + (event.getSlot() + 1) + " has been loaded!"));
                    }
                }
            }
        }

        if (event.getView().getTitle().equalsIgnoreCase( main.TCC("            &eAre you sure?"))){
            event.setCancelled(true);
            CharacterCreationMenu menu = new CharacterCreationMenu(main);
            if (event.getSlot() == 1 && event.getClickedInventory() == event.getView().getTopInventory()){
                CharacterFileManager characterFileManager = new CharacterFileManager(main, main.confirmDeleteMap.get(player), player);
                characterFileManager.deleteFile();
                player.openInventory(menu.menu(player));
                player.sendMessage(main.TCC("&9&lUyora &7» &cCharacter " + main.confirmDeleteMap.get(player) + " has been deleted!"));
                player.playSound(player.getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_BREAK, 1,1);
                main.confirmDeleteMap.remove(player);
            }
            if (event.getSlot() == 3 && event.getClickedInventory() == event.getView().getTopInventory()){
                player.closeInventory();
                player.openInventory(menu.menu(player));
                main.confirmDeleteMap.remove(player);
            }
        }


    }



}
