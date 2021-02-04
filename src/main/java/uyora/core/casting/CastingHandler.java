package uyora.core.casting;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import uyora.core.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CastingHandler {

    private final Main main;
    private Player player;
    int timer = 0;
    public boolean isCasting;

    public HashMap<Player, List<Action>> casting = new HashMap<Player, List<Action>>();
    public List<Action> combo = new ArrayList<>();

    public CastingHandler(Main main, Player player){
        this.main = main;
        this.player = player;
    }

    //TODO add casting combo enum



}
