package com.github.pixelsia.anticheat.ac.engine;

import com.github.pixelsia.anticheat.ac.engine.elements.MoveCheckElement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ACCheckMainListener implements Listener {
    public ACCheckMainListener(ACEngine acEngine) {
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        MoveCheckElement.run(e);
    }
}
