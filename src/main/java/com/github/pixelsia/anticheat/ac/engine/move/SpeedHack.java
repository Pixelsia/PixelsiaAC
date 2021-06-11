package com.github.pixelsia.anticheat.ac.engine.move;

import com.github.pixelsia.anticheat.ac.engine.MoveCheckElement;
import com.github.pixelsia.anticheat.ac.engine.manager.MoveLog;
import com.github.pixelsia.anticheat.ac.engine.manager.SpeedHackManager;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class SpeedHack extends MoveCheckElement {

    public static final double MAXDISTANCE = 1.0d;

    @Override
    public void call(PlayerMoveEvent ev) {
        if (MoveCheckElement.can(ev.getPlayer())) {
            // TODO: check
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<SpeedHackManager> getManage(ArrayList<MoveLog> moves) {
        int inc = 1;
        ArrayList jumps = new ArrayList();
        while (inc < moves.size()) {
            if (((MoveLog) moves.get(inc)).isInVehicle) {
                return new ArrayList();
            }
            int startInc = inc;
            while ((inc < moves.size()) && (!((MoveLog) moves.get(inc)).isAir))
                inc++;
            if (inc > startInc + 5) {
                SpeedHackManager jump = new SpeedHackManager((MoveLog) moves.get(startInc), (MoveLog) moves.get((inc + startInc - 1) / 2), (MoveLog) moves.get(inc - 1));
                jump.isOnGround = true;
                jumps.add(jump);
                MoveLog end;
            }
            if (inc >= moves.size()) {
                break;
            }
            MoveLog start = (MoveLog) moves.get(inc - 1);
            while ((inc < moves.size()) && (((MoveLog) moves.get(inc)).isAir) && (((MoveLog) moves.get(inc)).location.getY() > ((MoveLog) moves.get(inc - 1)).location.getY()))
                inc++;
            if (inc >= moves.size()) {
                jumps.add(new SpeedHackManager(start, (MoveLog) moves.get(inc - 1), (MoveLog) moves.get(inc - 1)));
                break;
            }
            MoveLog apex = (MoveLog) moves.get(inc - 1);
            boolean isFloating = false;
            boolean isOnFire = false;
            int floatCount = 0;
            while ((inc < moves.size()) && (((MoveLog) moves.get(inc)).isAir)) {
                if (((MoveLog) moves.get(inc - 1)).location.getY() <= ((MoveLog) moves.get(inc)).location.getY()) {
                    floatCount++;
                    if (floatCount > 3)
                        isFloating = true;
                } else {
                    floatCount = 0;
                }
                if (((MoveLog) moves.get(inc)).isOnFire) {
                    isOnFire = true;
                }
                inc++;
            }
            MoveLog end;
            if (inc >= moves.size())
                end = (MoveLog) moves.get(moves.size() - 1);
            else end = (MoveLog) moves.get(inc);
            SpeedHackManager jump = new SpeedHackManager(start, apex, end);
            jump.isFloating = isFloating;
            jump.isOnFire = isOnFire;
            jumps.add(jump);
        }
        return jumps;
    }
}
