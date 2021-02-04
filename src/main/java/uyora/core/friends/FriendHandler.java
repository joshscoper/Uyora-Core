package uyora.core.friends;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import uyora.core.Main;
import uyora.core.filemanagement.PlayerFileManager;

import java.util.ArrayList;


public class FriendHandler {

    private final Main main;
    private Player player;
    private ArrayList<Player> friends = new ArrayList<>();
    private PlayerFileManager manager;

    public FriendHandler(Main main, Player player){
        this.main = main;
        this.player = player;
        this.manager = new PlayerFileManager(main, player);
    }

    public void loadFriends(){
        if (manager.getFriends().size() > 0) {
            for (int i = 0; i < manager.getFriends().size(); i++) {
                Player friend = (Player) Bukkit.getOfflinePlayer(manager.getFriends().get(i-1));
                if (friend != null) {
                    friends.add(friend);
                }
            }
        }
    }

    public Boolean isFriend(Player player){
        loadFriends();
        if (friends.contains(player)){
            return true;
        } else {
            return false;
        }
    }

    public void addFriend(Player player){
        String uuid = player.getName();
        manager.addFriend(uuid);
        loadFriends();
    }

    public void removeFriend(Player player){
        String uuid = player.getName();
        manager.removeFriend(uuid);
        loadFriends();
    }





}
