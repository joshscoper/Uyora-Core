package uyora.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.plugin.java.JavaPlugin;
import uyora.core.charactercreation.CharacterCreationMenu;
import uyora.core.events.Chat;
import uyora.core.events.InventoryInteract;
import uyora.core.events.Join;
import uyora.core.events.Quit;
import uyora.core.filemanagement.PlayerFileManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Main extends JavaPlugin {

    private PlayerFileManager playerFileManager;
    private MenuItems menuItems;
    private CharacterCreationMenu characterCreationMenu;

    @Override
    public void onEnable() {
        // Plugin startup logic
        playerFileManager = new PlayerFileManager(this);
        characterCreationMenu = new CharacterCreationMenu(this);
        menuItems = new MenuItems(this);
        new Join(this);
        new Quit(this);
        new Chat(this);
        new InventoryInteract(this);
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

    public PlayerFileManager getPlayerFileManager(){return playerFileManager;}
    public MenuItems getMenuItems(){return menuItems;}
    public CharacterCreationMenu getCharacterCreationMenu(){return characterCreationMenu;}
}
