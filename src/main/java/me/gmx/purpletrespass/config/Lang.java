package me.gmx.purpletrespass.config;

import me.gmx.purpletrespass.core.BConfig;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;


public enum Lang {

    PREFIX("&6&lPurpleTrespass> &r "),
    MSG_ERROR("&4Error occured, please contact server developer."),
    MSG_purpletrespass_USAGE("&4Incorrect usage! See /purpletrespass help."),
    MSG_USAGE_SUBCOMMAND("&4Incorrect usage. Correct usage is %usage%"),
    MSG_NOACCESS("&4You don't have access to this command."),
    MSG_CONFIGRELOADED("&2Config reloaded."),
    MSG_UNKNOWNCOMMAND("&4Unknown command. Check /purpletrespass help"),
    MSG_REGIONADDED("&aRegion added!"),
    MSG_REGIONREMOVED("&cRegion removed!"),
    MSG_RETURNED("&4You are not allowed to go there!"),
    LANG_CONSOLE("The console cannot perform this action."),
    MSG_purpletrespass_HELP("&2Insert generic help here.");


    private String defaultValue;
    private static BConfig config;

    Lang(String str){
        defaultValue = str;
    }


    public String getPath() { return name(); }

    public String getDefaultValue() { return this.defaultValue; }

    public String toString() { return fixColors(config.getConfig().getString(getPath())); }

    public static void setConfig(BConfig paramBConfig) {
        config = paramBConfig;
        load();
    }

    public List<String> getStringList(){
        return Arrays.asList(toString().split("/"));
    }

    public String toMsg() {
        boolean bool = true;
        if (bool) {
            return fixColors(config.getConfig().getString(PREFIX.getPath()) + config.getConfig()
                    .getString(getPath()));
        }
        return fixColors(config.getConfig().getString(getPath()));
    }

    public void set( String o){
        config.getConfig().set(getPath(),o);
    }

    private static void load() {
        for (Lang lang : values()) {
            if (config.getConfig().getString(lang.getPath()) == null) {
                config.getConfig().set(lang.getPath(), lang.getDefaultValue());
            }
        }
        config.save();
    }


    private String fixColors(String paramString) {
        if (paramString == null)
            return "";
        return ChatColor.translateAlternateColorCodes('&', paramString);
    }
}
