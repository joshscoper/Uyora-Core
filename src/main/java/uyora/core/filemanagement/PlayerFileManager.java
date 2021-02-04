package uyora.core.filemanagement;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import uyora.core.Main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PlayerFileManager {

    private final Main main;
    private File directory;
    private File playerData;
    private FileConfiguration data;
    private Player player;

    public PlayerFileManager(Main main, Player player){
        this.main = main;
        this.player = player;
        this.directory = new File(main.getDataFolder() + File.separator + "Player_Data" + File.separator + player.getUniqueId().toString());
        this.playerData = new File(directory, "data.yml");
        this.data = YamlConfiguration.loadConfiguration(playerData);
    }

    public void loadPlayerFile(){
        //File Creation
        if (!playerData.exists()){
            directory.mkdirs();
            try{
                playerData.createNewFile();
                data.createSection("Name");
                data.createSection("Date_Joined");
                data.createSection("Characters");
                data.createSection("Active_Character");

                data.createSection("Settings.PvP");
                data.createSection("Settings.Hide_Players");
                data.createSection("Settings.Streamer_Mode");

                data.createSection("Friends");

                //Default Values
                data.set("Name", player.getName());

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                String joinDate = dateFormat.format(new Date());
                data.set("Date_Joined", joinDate);

                data.set("Characters", 0);
                data.set("Active_Character", 1);

                data.set("Settings.PvP", true);
                data.set("Settings.Hide_Players", false);
                data.set("Settings.Streamer_Mode", false);

                List<String> friends = new ArrayList<>();
                data.set("Friends", friends);

                saveFile(data,playerData);
            } catch (IOException exception){
                exception.printStackTrace();
            }
        } else {
            if (!player.getName().equalsIgnoreCase(data.getString("Name"))){
                data.set("Name",player.getName());
                saveFile(data, playerData);
            }
        }
    }

    public List<String> getFriends(){
        return (List<String>) data.getList("Friends");
    }

    public void addFriend(String uuid){
        List<String> friends = getFriends();
        if (!friends.contains(uuid)) {
            friends.add(uuid);
            data.set("Friends", friends);
        }
        saveFile(data, playerData);
    }

    public void removeFriend(String uuid){
        List<String> friends = getFriends();
        if (friends.contains(uuid)){
            friends.remove(uuid);
        }
        saveFile(data, playerData);
    }

    public void setHidePlayers(Boolean bool){
        data.set("Settings.Hide_Players", bool);
        saveFile(data, playerData);
    }

    public void setPvP(Boolean bool){
        data.set("Settings.PvP", bool);
        saveFile(data, playerData);
    }

    public void setStreamerMode(Boolean bool){
        data.set("Settings.Streamer_Mode", bool);
        saveFile(data, playerData);
    }

    public FileConfiguration getData(){
        return data;
    }

    public File getDirectory(){return directory;}

    public File getPlayerFile(){
        return playerData;
    }

    public String joinDate(){
        return data.getString("Date_Joined");
    }

    public int getCharacters(){
        return data.getInt("Characters");
    }

    public int getActive(){
       return data.getInt("Active_Character");
    }

    public void setActive(Player player, int id){
        if (getActive() != 0){
            CharacterFileManager characterFileManager = new CharacterFileManager(main, getActive(), player);
            characterFileManager.setLocation();
            //TODO Save Inventory
        }
        data.set("Active_Character", id);
        saveFile(data, playerData);
    }

    public void addCharacter(Player player){
        System.out.println(getCharacters());
        if (getCharacters() < 0) {
            data.set("Characters", 0);
            System.out.println("Uyora >>> " + player.getDisplayName() + "'s data file has been repaired (negative characters)");
        }
        if (getCharacters() <= 5){
            CharacterFileManager characterFileManager = new CharacterFileManager(main, getCharacters() + 1, player);
            characterFileManager.createCharacterFile();
            data.set("Characters", getCharacters() + 1);
            saveFile(data, playerData);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        }
    }

    public void removeCharacter(){
        if (data.getInt("Characters") >= 0) {
            data.set("Characters", getCharacters() - 1);
            saveFile(data, playerData);
        }
    }

    public void saveFile(FileConfiguration configuration, File file){
        try {
            configuration.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }



}
