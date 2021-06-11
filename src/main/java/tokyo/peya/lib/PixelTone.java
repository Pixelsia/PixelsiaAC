package tokyo.peya.lib;

import com.github.pixelsia.anticheat.ac.Kei;
import com.github.pixelsia.anticheat.ac.PixelsiaAC;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class PixelTone {
    /**
     * Kick Player
     * @param plugin JavaPlugin instance
     * @param target Target player
     * @param type Kick type
     */
    public static void kickPlayer(JavaPlugin plugin, Player target, KickType type) {
        String reason = type.getReason();
        reason = reason.replace("%%id%%", genID())
                .replace("%%ggid%%", genGGID());

        if (type == KickType.CHEAT) {
            Bukkit.getServer().broadcast(Component.text("[PIXELSIA CHEAT DETECTION]" +
                    ChatColor.RED + ChatColor.BOLD + "ハッキング、または不適切な発言によってゲームからプレイヤーがキックされました。"));
        }

        String finalReason = reason;
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getServer().broadcast(Component.text(
                        ChatColor.RED.toString() + ChatColor.BOLD + "違反行為をしたプレイヤーをゲームから対処しました。" +
                                ChatColor.AQUA + "報告ありがとうございました！"));
                target.kickPlayer(finalReason);
            }
        }.runTaskLater(PixelsiaAC.instance, 6);
    }

    private static String genID() {

        StringBuilder id = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            if (random.nextBoolean())
                id.append(random.nextInt(9));
            else
                id.append((char) (random.nextInt(5) + 'A'));
        }
        return id.toString();
    }


    private static String genGGID() {
        Random random = new Random();
        StringBuilder ggId = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            ggId.append(random.nextInt(9));
        }

        return ggId.toString();
    }

    public enum KickType {
        CHEAT("\n" +
                ChatColor.RED + "ゲームから退出させられました！\n" +
                "\n" +
                ChatColor.GRAY + "理由：" + ChatColor.WHITE + "%%reason%% " +
                    ChatColor.GRAY + ChatColor.ITALIC + "[GG-%%ggid%%]"),
        STAFF("\n" +
                ChatColor.RED + "ゲームから退出させられました！\n" +
                "\n" +
                ChatColor.GRAY + "理由：" + ChatColor.WHITE + "%%reason%%" +
                "\n" +
                "\n" +
                ChatColor.GRAY + "Kick ID：" + ChatColor.WHITE + "%%id%%"),
        CUSTOM("%%reason%%" +
                "\n" +
                "\n" +
                ChatColor.GRAY + "Kick ID：" + ChatColor.WHITE + "%%id%%");

        private final String baseContext;
        private String message;

        KickType(String baseContext) {
            this.baseContext = baseContext;
            this.message = baseContext;
        }

        public String getBaseContext() {
            return baseContext;
        }

        public KickType setReason(String reason) {
            this.message = this.message.replace("%%reason%%", reason);
            return this;
        }

        public String getReason() {
            return message;
        }
    }
}
