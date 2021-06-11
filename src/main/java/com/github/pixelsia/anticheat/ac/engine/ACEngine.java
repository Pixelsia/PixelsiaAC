package com.github.pixelsia.anticheat.ac.engine;

import com.github.pixelsia.anticheat.ac.Kei;
import com.github.pixelsia.anticheat.ac.PixelsiaAC;
import com.github.pixelsia.anticheat.ac.engine.elements.MoveCheckElement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import tokyo.peya.lib.PixelTone;

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
        PixelTone.kickPlayer(PixelsiaAC.instance, p, PixelTone.KickType.CHEAT.setReason(reason.getReason()));

        Kei.out("AC: KickPlayer(" + p.getName() + ") [" + reason.getConsoleReason() + "]");

    }
}
