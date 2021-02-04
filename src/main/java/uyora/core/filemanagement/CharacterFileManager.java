package uyora.core.filemanagement;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.FileUtil;
import uyora.core.Main;
import uyora.core.classes.CharacterClass;

import java.io.File;
import java.io.IOException;

public class CharacterFileManager {

    private int character;
    private Player player;
    private Main main;

    private int max_chars = 5;

    private File charFile;
    private FileConfiguration charConfig;

    private PlayerFileManager manager;

    public CharacterFileManager(Main main, int character, Player player){
        this.character = character;
        this.player = player;
        this.main = main;
        manager = new PlayerFileManager(main, player);
        this.charFile = new File(manager.getDirectory(), "Character_" + character + ".yml");
        charConfig = YamlConfiguration.loadConfiguration(charFile);
    }

    public void createCharacterFile(){
        if (manager.getCharacters() < max_chars){
            FileConfiguration charConfiguration = YamlConfiguration.loadConfiguration(charFile);
            if (!charFile.exists()){
                try {
                    charFile.createNewFile();

                    charConfiguration.createSection("Class");
                    charConfiguration.createSection("Level.Combat");
                    charConfiguration.createSection("Balance");
                    charConfiguration.createSection("Location.World");
                    charConfiguration.createSection("Location.X");
                    charConfiguration.createSection("Location.Y");
                    charConfiguration.createSection("Location.Z");
                    charConfiguration.createSection("Inn.World");
                    charConfiguration.createSection("Inn.X");
                    charConfiguration.createSection("Inn.Y");
                    charConfiguration.createSection("Inn.Z");

                    charConfiguration.set("Class", "Undecided");
                    charConfiguration.set("Level.Combat", 0);
                    charConfiguration.set("Balance", 0);


                    manager.getData().set("Active_Character", character);
                    manager.addCharacter(player);
                    saveFile(charConfiguration, charFile);
                    this.charConfig = charConfiguration;
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public File getCharFile(){
        return charFile;
    }

    public FileConfiguration getCharConfiguration(){
        return charConfig;
    }

    public String getClazz(){
        return charConfig.getString("Class");
    }

    public int getLevel(){
        return charConfig.getInt("Level.Combat");
    }

    public int getBalance(){return charConfig.getInt("Balance");}

    public boolean characterExists(){
        if (!charFile.exists()){
            return false;
        } else {
            return true;
        }
    }

    public Location getInn(){
        String world = charConfig.getString("Inn.World");
        double x = charConfig.getDouble("Inn.X");
        double y = charConfig.getDouble("Inn.Y");
        double z = charConfig.getDouble("Inn.Z");
        Location location = new Location(Bukkit.getWorld(world),x,y,z);
        return location;
    }

    public void setInn(){
        charConfig.set("Inn.World", player.getWorld().getName());
        charConfig.set("Inn.X", player.getLocation().getX());
        charConfig.set("Inn.Y", player.getLocation().getY());
        charConfig.set("Inn.Z", player.getLocation().getZ());
        saveFile(charConfig, charFile);
    }

    public void setLocation(){
        charConfig.set("Location.World", player.getWorld().getName());
        charConfig.set("Location.X", player.getLocation().getX());
        charConfig.set("Location.Y", player.getLocation().getY());
        charConfig.set("Location.Z", player.getLocation().getZ());
        saveFile(charConfig, charFile);
    }

    public Location getLocation(){
        String world = charConfig.getString("Location.World");
        double x = charConfig.getDouble("Location.X");
        double y = charConfig.getDouble("Location.Y");
        double z = charConfig.getDouble("Location.Z");
        Location location = new Location(Bukkit.getWorld(world),x,y,z);
        return location;
    }

    public void saveFile(FileConfiguration fileConfiguration, File file){
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteFile(){
        charFile.delete();
        manager.removeCharacter();
    }



}
