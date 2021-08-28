package me.gmx.purpletrespass.tasks;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.gmx.purpletrespass.PurpleTrespass;
import me.gmx.purpletrespass.config.Lang;
import me.gmx.purpletrespass.config.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class TrespassTimer extends BukkitRunnable{

    private PurpleTrespass ins;
    private int period;
    private ArrayList<String> disabledWorlds;
    private String[] blacklist;
    private HashMap<String,ArrayList<PotionEffectType>> potionMap;
    public TrespassTimer(PurpleTrespass ins, int period, Collection<String> blacklist, List<String> disabledWorlds, HashMap<String,ArrayList<PotionEffectType>> potions){
        this.ins = ins;
        this.period = period;
        this.disabledWorlds = (ArrayList<String>) disabledWorlds;
        this.blacklist = blacklist.toArray(new String[0]);
        this.potionMap = potions;
    }

    @Override
    public void run(){
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            //Disable in certain worlds
            if (p.isOp())
                continue;
            if (disabledWorlds.contains(p.getWorld().getName()))
                continue;



            ApplicableRegionSet list = WGBukkit.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation());

            //Check for __global__
            if (list.size() == 0) {
                ret(p);
                continue;
            }
            //Check for blacklist regions
            for (final ProtectedRegion r : list) {
                if (Arrays.stream(blacklist).anyMatch(region -> region.equals(r.getId()))) {
                    ret(p);
                    continue;
                }
               for(String s : potionMap.keySet())
                if (potionMap.keySet().contains(r.getId())){
                    for (PotionEffect effect : p.getActivePotionEffects())
                        if (potionMap.get(r.getId()).contains(effect.getType()))
                            p.removePotionEffect(effect.getType());


                }

            }



        }

    }


    public void start(){
        this.runTaskTimer(this.ins, 5,period*20);

        /*this.daemonId = new BukkitRunnable(){
            public void run(){

            }
        }.runTaskTimer(this.ins,40,period*20).getTaskId();*/
    }

    private void ret(Player p){
        p.sendMessage(Lang.MSG_RETURNED.toString());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Settings.PUNISHMENT_COMMAND.getString().replace("%player%",p.getName()));
    }


}
