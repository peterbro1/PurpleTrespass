package me.gmx.purpletrespass;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.gmx.purpletrespass.command.CmdPurpleTrespass;
import me.gmx.purpletrespass.config.Lang;
import me.gmx.purpletrespass.config.Settings;
import me.gmx.purpletrespass.core.BConfig;
import me.gmx.purpletrespass.tasks.TrespassTimer;
import me.gmx.purpletrespass.util.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurpleTrespass extends JavaPlugin {

    Logger logger;
    private static PurpleTrespass ins;
    public BConfig langConfig, mainConfig;

    public File regionFile;
    public FileConfiguration regionConfig;

    public WorldGuardPlugin wgp;

    public TrespassTimer timer;

    @Override
    public void onEnable(){
        ins = this;
        logger = getLogger();

        if (getServer().getPluginManager().getPlugin("Worldguard") == null)
            getServer().getPluginManager().disablePlugin(this);
        else
            this.wgp = WorldGuardPlugin.inst();


        registerCommands();
        registerListeners();
        initConfigs();
        reloadConfig();
        reloadTimer();
        logger.log(Level.INFO, String.format("[%s] Successfully enabled version %s!", new Object[] { getDescription().getName(), getDescription().getVersion() }));

    }

    public void reloadTimer(){

        if (this.timer != null)
            this.timer.cancel();


        this.timer = new TrespassTimer(getInstance(),Settings.CHECK_INTERVAL.getNumber()
                , getRegionConfig().getStringList("blacklist")
                , getRegionConfig().getStringList("disabled_worlds")
                , FileUtils.getPotionMap());
        this.timer.start();
    }

    @Override
    public void onDisable(){
        Bukkit.getScheduler().cancelTasks(this);
        langConfig.save();
        mainConfig.save();
    }

    public void saveRegionConfig() {
        try {
            getRegionConfig().save(regionFile);
        } catch(IOException e){
            e.printStackTrace();
            log("Failed to save region config.");
        }
    }

    public void reloadRegionConfig(){
        try {
            regionConfig.load(new File(getDataFolder(), "regions.yml"));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void initConfigs(){
        try {
            regionFile = new File(getDataFolder(), "regions.yml");
            if (!regionFile.exists()) {
                regionFile.getParentFile().mkdirs();
                saveResource("regions.yml", false);
            }
            regionConfig= new YamlConfiguration();

            regionConfig.load(regionFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getRegionConfig(){
        return this.regionConfig;
    }

    private void registerListeners(){
        //Bukkit.getPluginManager().registerEvents(new Listener(),getInstance());

    }
    private void registerCommands(){
        getCommand("purpletrespass").setExecutor(new CmdPurpleTrespass(getInstance()));
    }


    public void reloadConfig() {
        this.langConfig = new BConfig(this,"Lang.yml");
        Lang.setConfig(this.langConfig);
        this.mainConfig = new BConfig(this,"Settings.yml");
        Settings.setConfig(this.mainConfig);
    }
    public void saveLang(){
        this.langConfig.save();
    }
    public void saveMain(){
        this.mainConfig.save();
    }
    public void log(String message){
        logger.log(Level.INFO,"["+getDescription().getName()+"] " + message);
    }

    public static PurpleTrespass getInstance(){return ins;}


}
