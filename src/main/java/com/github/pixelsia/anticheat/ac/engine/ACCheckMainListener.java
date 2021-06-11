package com.github.pixelsia.anticheat.ac.engine;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class ACCheckMainListener implements Listener {
    public ACCheckMainListener(ACEngine acEngine) {
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        MoveCheckElement.run(e);
    }
}
