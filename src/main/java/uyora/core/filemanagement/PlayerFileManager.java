package uyora.core.filemanagement;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import uyora.core.Main;

import java.io.File;
import java.io.IOException;

public class PlayerFileManager {

    private final Main main;


    public PlayerFileManager(Main main){
        this.main = main;
    }

    public void createPlayerFile(Player player){
        File file = new File(main.getDataFolder() + File.separator  + "Player Data", String.valueOf(player.getUniqueId()));
        if (!file.exists()){
            file.mkdirs();
        }
    }

    public File getPlayerFile(Player player){
        return new File(main.getDataFolder() + File.separator  + "Player Data", String.valueOf(player.getUniqueId()));
    }

    public FileConfiguration getPlayerConfig(Player player) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(getPlayerFile(player));
        return configuration;
    }

    public void loadPlayerFile(Player player){
        File file = getPlayerFile(player);
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        createPlayerFile(player);
        config.set("Name", player.getName());
        config.set("Characters", 0);
        config.set("Active_Character", 0);
        saveFile(config, file);
    }

    public void saveFile(FileConfiguration configuration, File file){
        try {
            configuration.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getCharacters(Player player){
        return getPlayerConfig(player).getInt("Characters");
    }

    public void addCharacter(Player player){
        int characters = getCharacters(player) + 1;
        setActiveCharacter(player, characters);
        getPlayerConfig(player).set("Characters", characters);
        saveFile(getPlayerConfig(player), getPlayerFile(player));
    }

    public int getActiveCharacter(Player player){
        return getPlayerConfig(player).getInt("Active_Character");
    }

    public void setActiveCharacter(Player player, int character){
        FileConfiguration configuration = getPlayerConfig(player);
        File file = getPlayerFile(player);
        configuration.set("Active_Character", character);
        saveFile(configuration, file);
    }


}
