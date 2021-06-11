package com.github.pixelsia.anticheat.ac.engine;

import com.github.pixelsia.anticheat.ac.Kei;
import com.github.pixelsia.anticheat.ac.PixelsiaAC;
import com.github.pixelsia.anticheat.ac.engine.elements.MoveCheckElement;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ACEngine {

    PixelsiaAC acmain;
    Map<UUID, Integer> levels;

    public ACEngine(PixelsiaAC pixelsiaAC) {
        this.acmain = pixelsiaAC;
        levels = new HashMap<>();

        // register main listener
        Kei.a(new ACCheckMainListener(this), pixelsiaAC.instance);

        // register check elements
        MoveCheckElement.init();

        // register some manager
    }

    public static void KickPlayer(Player p, ACKickReason reason){
        String r = "KickReason: " + reason.getReason();
        p.kickPlayer(r);
        Kei.out("AC: KickPlayer(" + p.getName() + ") [" + reason.getConsoleReason() + "]");
    }
}
