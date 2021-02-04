package uyora.core.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import uyora.core.Main;
import uyora.core.filemanagement.CharacterFileManager;
import uyora.core.filemanagement.PlayerFileManager;

public class ScoreboardHandler {

    private final Main main;
    private PlayerFileManager manager;

    public ScoreboardHandler(Main main, Player player){
        this.main = main;
        manager = new PlayerFileManager(main, player);
    }

    public void setScoreboard(Player player){
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("Uyora", "dummy", main.TCC("&9&l&n▁▂▃Uyora▃▂▁"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        PlayerFileManager manager = new PlayerFileManager(main, player);
        CharacterFileManager characterFileManager = new CharacterFileManager(main, manager.getActive(), player);

        Score break1 = obj.getScore(main.TCC("&c"));
        break1.setScore(16);
        Score clazz = obj.getScore(main.TCC("&a&lClass &7» "));
        clazz.setScore(15);
        Score break2 = obj.getScore(" ");
        break2.setScore(13);
        Score balance = obj.getScore(main.TCC("&a&lBalance &7» "));
        balance.setScore(12);

        Team claz = board.registerNewTeam("class");
        claz.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);

        Team bal = board.registerNewTeam("balance");
        bal.addEntry(ChatColor.RED + "" + ChatColor.RED);

        if (characterFileManager.getClazz() != null) {
            claz.setPrefix(main.TCC("&e" + characterFileManager.getClazz()));
        } else {
            claz.setPrefix(main.TCC("&eTalk to &9&lZara&e."));
        }

        bal.setPrefix(main.TCC("&e" + characterFileManager.getBalance() + "\ueff1"));

        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(14);
        obj.getScore(ChatColor.RED + "" + ChatColor.RED).setScore(10);

        player.setScoreboard(board);
    }

    public void updateBoard(Player player){
         Scoreboard scoreboard = player.getScoreboard();
         CharacterFileManager characterFileManager = new CharacterFileManager(main, manager.getActive(), player);
         String claz = characterFileManager.getCharConfiguration().getString("Class");
         int bal = characterFileManager.getCharConfiguration().getInt("Balance");
         scoreboard.getTeam("class").setPrefix(main.TCC("&e" + claz));
         scoreboard.getTeam("balance").setPrefix(main.TCC("&e" + bal + "\ueff1"));
    }

    public void updater(Player player){
        Bukkit.getScheduler().runTaskTimer(main, new Runnable() {
            @Override
            public void run() {
                setScoreboard(player);
            }
        },20,20);
    }

}
