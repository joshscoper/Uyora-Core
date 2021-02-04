package uyora.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uyora.core.charactercreation.CharacterCreationMenu;
import uyora.core.events.*;
import uyora.core.filemanagement.PlayerFileManager;
import uyora.core.inspect.InspectMenu;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Main extends JavaPlugin {

    private MenuItems menuItems;
    private CharacterCreationMenu characterCreationMenu;
    public HashMap<Player, Integer> confirmDeleteMap;
    private InspectMenu inspectMenu;

    @Override
    public void onEnable() {
        // Plugin startup logic
        characterCreationMenu = new CharacterCreationMenu(this);
        menuItems = new MenuItems(this);
        confirmDeleteMap = new HashMap<Player,Integer>();
        new Join(this);
        new Quit(this);
        new Chat(this);
        new InventoryInteract(this);
        new NPCInteract(this);
        new Drop(this);
        new ItemInteract(this);
        new Death(this);
        inspectMenu = new InspectMenu(this);
        settingsCheck();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public String TCC(String string){
        if (Bukkit.getVersion().contains("1.16")){
            Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
            Matcher match = pattern.matcher(string);
            while (match.find()){
                String color = string.substring(match.start(), match.end());
                string = string.replace(color, net.md_5.bungee.api.ChatColor.of(color)+ "");
                match = pattern.matcher(string);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', string);
    }


    public void settingsCheck(){
            //Hide Players
        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    hidePlayers(player);
                    streamerMode(player);
                }
            }
        },20,20);
    }

    public void hidePlayers(Player player){
        PlayerFileManager manager = new PlayerFileManager(this,player);
        if (manager.getData().getBoolean("Settings.Hide_Players") == true){
            for (Player p : Bukkit.getOnlinePlayers()){
                if (p != player) {
                    player.hidePlayer(this, p);
                }
            }
        } else {
            for (Player p : Bukkit.getOnlinePlayers()){
                if (p != player) {
                    player.showPlayer(this, p);
                }
            }
        }
    }

    public void streamerMode(Player player){
        PlayerFileManager manager = new PlayerFileManager(this, player);
        for (Player p : Bukkit.getOnlinePlayers()){
            String name;
            if (p != player) {
                if (manager.getData().getBoolean("Settings.Streamer_Mode") == true) {
                        name = "&k" + p.getDisplayName();
                } else {
                        name = p.getDisplayName().replaceAll("&k", "");
                }
                p.setDisplayName(name);
            }
        }
    }

    public MenuItems getMenuItems(){return menuItems;}
    public CharacterCreationMenu getCharacterCreationMenu(){return characterCreationMenu;}
    public HashMap<Player,Integer> getConfirmDeleteMap(){return confirmDeleteMap;}
    public InspectMenu getInspectMenu(){return inspectMenu;}
}
