package uyora.core.filemanagement;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import uyora.core.Main;
import uyora.core.classes.CharacterClass;

import java.io.File;
import java.io.IOException;

public class CharacterFileManager {

    private int character;
    private Player player;
    private Main main;

    private int max_chars = 5;

    private File file;
    private FileConfiguration fileConfiguration;

    private File charFile;

    public CharacterFileManager(Main main, int character, Player player){
        this.character = character;
        this.player = player;
        this.main = main;
        charFile = new File(main.getPlayerFileManager().getPlayerFile(player), "Character_" + character + ".yml");
    }

    public void createCharacterFile(){
        file = main.getPlayerFileManager().getPlayerFile(player);
        fileConfiguration = main.getPlayerFileManager().getPlayerConfig(player);
        if (fileConfiguration.getInt("Characters") < max_chars){
            FileConfiguration charConfig = YamlConfiguration.loadConfiguration(charFile);
            if (!charFile.exists()){
                try {
                    charFile.createNewFile();

                    fileConfiguration.set("Class", "none");
                    fileConfiguration.set("Level.Combat", 0);
                    fileConfiguration.set("Balance", 0);


                    main.getPlayerFileManager().addCharacter(player);
                    main.getPlayerFileManager().setActiveCharacter(player, character);

                    saveFile(fileConfiguration, file);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    public boolean characterExists(){
        if (!charFile.exists()){
            return false;
        } else {
            return true;
        }
    }

    public void saveFile(FileConfiguration fileConfiguration, File file){
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClazz(){
        return fileConfiguration.getString("Class");
    }

    public void setClazz(CharacterClass clazz){
        fileConfiguration.set("Class", clazz.getPath());
        saveFile(fileConfiguration, charFile);
    }

    public int getCombatLevel(){
        return fileConfiguration.getInt("Level.Combat");
    }

    public void setCombatLevel(int level){
        fileConfiguration.set("Level.Combat", level + getCombatLevel());
        saveFile(fileConfiguration, charFile);
    }

    public int getBalance(){
        return fileConfiguration.getInt("Balance");
    }

    public void setBalance(int balance){
        fileConfiguration.set("Balance", balance + getBalance());
        saveFile(fileConfiguration, charFile);
    }


}
