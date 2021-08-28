package me.gmx.purpletrespass.config;


import me.gmx.purpletrespass.core.BConfig;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public enum Settings {

    CHECK_INTERVAL("5"),
    PUNISHMENT_COMMAND("spawn %player%");


    private String defaultValue;
    private Class type;
    private static BConfig config;
    private String prefix = ChatColor.DARK_RED + "" + ChatColor.BLACK;
    Settings(String str){
        defaultValue = str;
    }


    public String getPath() { return name(); }

    public String getDefaultValue() { return this.defaultValue; }


    public static void setConfig(BConfig paramBConfig) {
        config = paramBConfig;
        load();
    }
    public String getEncodeString(){
        return prefix + ChatColor.translateAlternateColorCodes('&',config.getConfig().getString(getPath()));

    }
    public int getNumber() {
        return Integer.parseInt(config.getConfig().getString(getPath()));
    }

    public List<String> getStringList(){
        return Arrays.asList(getString().split("/"));
    }


    public boolean getBoolean() throws Exception{

        try {
            return Boolean.valueOf(config.getConfig().getString(getPath()));
        }catch(NullPointerException e) {
            throw new Exception("Value could not be converted to a boolean");
        }

    }


    public String getString(){
        return ChatColor.translateAlternateColorCodes('&',config.getConfig().getString(getPath()));
    }
    public void set( String o){
        config.getConfig().set(getPath(),o);
    }

    private static void load() {
        for (Settings lang : values()) {
            if (config.getConfig().getString(lang.getPath()) == null) {
                config.getConfig().set(lang.getPath(), lang.getDefaultValue());
            }
        }
        config.save();
    }
}
