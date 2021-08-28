package me.gmx.purpletrespass.util;

import me.gmx.purpletrespass.PurpleTrespass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {

    public static void copy(InputStream input, File file) {

        try {
            FileOutputStream output = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int i;
            while ((i = input.read(b)) > 0) {
                output.write(b, 0, i);
            }
            output.close();
            input.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mkDir(File file) {
        try {
            file.mkdir();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static HashMap<String, ArrayList<PotionEffectType>> getPotionMap() {
        HashMap<String, ArrayList<PotionEffectType>> map = new HashMap<>();
        for (String sec : PurpleTrespass.getInstance().getRegionConfig().getKeys(false)) {
            if (sec.equals("blacklist") || sec.equals("disabled_worlds"))
                continue;

            ArrayList<PotionEffectType> list = new ArrayList<>();
            for (String s : PurpleTrespass.getInstance().getRegionConfig().getStringList(sec)) {
                try {
                    list.add(PotionEffectType.getByName(s));
                } catch (Exception e) {
                    PurpleTrespass.getInstance().log(String.format("Cannot load %s's potion effect: %s", new Object[]{sec, s}));
                }
            }
            map.put(sec,list);
        }
        return map;
    }






}
