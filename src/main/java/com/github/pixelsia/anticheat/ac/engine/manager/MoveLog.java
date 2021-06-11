package com.github.pixelsia.anticheat.ac.engine.manager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class MoveLog {
    public Player player;
    public long time;
    public Location location;
    public Vector velocity;
    public boolean isSprinting;
    public boolean isSneaking;
    public boolean isAir;
    public boolean isOnFire;
    public boolean isInVehicle;

    final double VELOCITY_MAX = 0.001D;

    public MoveLog(Player p, Location l) {
        this.player = p;
        this.location = l.clone();
        this.time = System.currentTimeMillis();
        this.velocity = p.getVelocity();
        this.isSprinting = p.isSprinting();
        this.isSneaking = p.isSneaking();
        this.isInVehicle = p.isInsideVehicle();
        this.isOnFire = (p.getFireTicks() > 0);

        if (Math.abs(this.velocity.getX()) < VELOCITY_MAX)
            this.velocity.setX(0);
        if (Math.abs(this.velocity.getY()) < VELOCITY_MAX)
            this.velocity.setY(0);
        if (Math.abs(this.velocity.getZ()) < VELOCITY_MAX) {
            this.velocity.setZ(0);
        }
        this.isAir = isBlockAir(this.location);
    }

    public double Speed(MoveLog other) {
        if (other == null) {
            return 0.0D;
        }
        long time = Math.abs(this.time - other.time);
        double distance = this.location.distance(other.location);

        return distance / time;
    }

    private boolean isMaterialAir(Material type) {
        return (type == Material.AIR) || (type == Material.LEGACY_AIR) || (type == Material.CAVE_AIR) || (type == Material.VOID_AIR);
    }

    private boolean isBlockAir(Location location) {
        Location l = location.clone();
        double x = l.getX();
        double y = l.getY();
        double z = l.getZ();

        y = Math.floor(y) - 0.001D;

        Material type = new Location(l.getWorld(), x, y, z).getBlock().getType();

        if (!isMaterialAir(type)) {
            return false;
        }
        boolean xup = false;
        boolean xdwn = false;
        boolean zup = false;
        boolean zdwn = false;

        if ((int) x != (int) (x + 0.32D)) {
            xup = true;
            type = new Location(l.getWorld(), x + 0.32D, y, z).getBlock().getType();

            if (!isMaterialAir(type)) {
                return false;
            }
        }
        if ((int) x != (int) (x - 0.32D)) {
            xdwn = true;
            type = new Location(l.getWorld(), x - 0.32D, y, z).getBlock().getType();

            if (!isMaterialAir(type)) {
                return false;
            }
        }
        if ((int) z != (int) (z + 0.32D)) {
            zup = true;
            type = new Location(l.getWorld(), x, y, z + 0.32D).getBlock().getType();

            if (!isMaterialAir(type)) {
                return false;
            }
        }
        if ((int) z != (int) (z - 0.32D)) {
            zdwn = true;
            type = new Location(l.getWorld(), x, y, z - 0.32D).getBlock().getType();

            if (!isMaterialAir(type)) {
                return false;
            }
        }
        if ((xup) && (zup)) {
            type = new Location(l.getWorld(), x + 0.32D, y, z + 0.32D).getBlock().getType();

            if (!isMaterialAir(type)) {
                return false;
            }
        }
        if ((xup) && (zdwn)) {
            type = new Location(l.getWorld(), x + 0.32D, y, z - 0.32D).getBlock().getType();

            if (!isMaterialAir(type)) {
                return false;
            }
        }
        if ((xdwn) && (zup)) {
            type = new Location(l.getWorld(), x - 0.32D, y, z + 0.32D).getBlock().getType();

            if (!isMaterialAir(type)) {
                return false;
            }
        }
        if ((xdwn) && (zdwn)) {
            type = new Location(l.getWorld(), x - 0.32D, y, z - 0.32D).getBlock().getType();

            if (!isMaterialAir(type)) {
                return false;
            }
        }
        return true;
    }
}