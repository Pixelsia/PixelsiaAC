package com.github.pixelsia.anticheat.ac.engine;

import com.github.pixelsia.anticheat.ac.PixelsiaAC;
import com.github.pixelsia.anticheat.ac.engine.move.SpeedHack;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class MoveCheckElement {

    private static List<MoveCheckElement> methods = new ArrayList<>();
    public static List<Player> without = new ArrayList<>();

    public static void init(){
        register(new SpeedHack());
    }

    public abstract void call(PlayerMoveEvent ev);
    public static void register(MoveCheckElement element) {
        methods.add(element);
    }

    public static void run(PlayerMoveEvent ev){
        for(MoveCheckElement el : methods){
            el.call(ev);
        }
    }

    public static boolean can(Player p) {
        return !without.contains(p);
    }

    public static void without(Player p, int tick){
        without.add(p);
        new BukkitRunnable(){
            /**
             * When an object implementing interface <code>Runnable</code> is used
             * to create a thread, starting the thread causes the object's
             * <code>run</code> method to be called in that separately executing
             * thread.
             * <p>
             * The general contract of the method <code>run</code> is that it may
             * take any action whatsoever.
             *
             * @see Thread#run()
             */
            @Override
            public void run() {
                without.remove(p);
            }
        }.runTaskLater(PixelsiaAC.instance, tick);
    }
}
