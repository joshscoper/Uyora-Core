package uyora.core.chat;

import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;
import uyora.core.Main;
import uyora.core.charactercreation.CharacterCreationMenu;
import uyora.core.filemanagement.CharacterFileManager;
import uyora.core.filemanagement.PlayerFileManager;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONHandler {


    private final Main main;

    private Player player;
    private String message;

    private PlayerFileManager manager;

    public JSONHandler(Player player, Main main, String message){
        this.main = main;
        this.player = player;
        this.message = message;
        this.manager = new PlayerFileManager(main, player);
    }

    public BaseComponent rank(){
        TextComponent rank = new TextComponent(main.TCC("&7"));
        if (player.hasPermission("uyora.adventurer")){
            rank = new TextComponent(main.TCC("&7[&a&lAdventurer&7]"));
        }

        if (player.hasPermission("uyora.hero")){
            rank = new TextComponent(main.TCC("&7[&b&lHero&7]"));
        }

        if (player.hasPermission("uyora.legend")){
            rank = new TextComponent(main.TCC("&7[&6&lLegend&7]"));
        }

        if (player.hasPermission("uyora.staff")){
            rank = new TextComponent(main.TCC("&f[&5&lGM&f]" + " "));
        }
        return rank;
    }

    public BaseComponent name(){
        TextComponent name = new TextComponent(main.TCC(player.getDisplayName() + " "));
        if (player.hasPermission("uyora.staff")){
            name = new TextComponent(main.TCC("&d" + player.getDisplayName() + " "));
        }
        name.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + player.getName()));
        //TODO add character hooks
        CharacterFileManager characterFileManager = new CharacterFileManager(main, manager.getActive(), player);
        String clazz = characterFileManager.getClazz();
        String level = String.valueOf(characterFileManager.getLevel());
        name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(main.TCC("&eClass &f» &a" + clazz
                + "\n" + "&eLevel &f» &a" + level + "\n" +
                "&eClick to send a message.")).create()));
        return name;
    }

    public BaseComponent message(){
        TextComponent messagecom = new TextComponent(main.TCC("&f» &r" + this.message));
        return messagecom;
    }

    public void sendMessage(Set<Player> recipients){
        for (Player p : recipients) {
            p.spigot().sendMessage(rank(),name(),message());
        }
    }

}
