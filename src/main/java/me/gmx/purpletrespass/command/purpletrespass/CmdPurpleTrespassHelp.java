package me.gmx.purpletrespass.command.purpletrespass;

import me.gmx.purpletrespass.PurpleTrespass;
import me.gmx.purpletrespass.config.Lang;
import me.gmx.purpletrespass.core.BSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class CmdPurpleTrespassHelp extends BSubCommand {

    public CmdPurpleTrespassHelp(){
        this.aliases.add("help");
        this.correctUsage = "/koth help";
        this.permission = "koth.help";
    }

    @Override
    public void execute() {
        msg(Lang.PREFIX.toString() + "&6&l----PurpleTrespass Commands----");
        msg(Lang.PREFIX.toString() + "&6/purpletrespass help " + "&9- Display this help page");
        msg(Lang.PREFIX.toString() + "&6/purpletrespass list " + "&9- List all blacklisted regions");
        msg(Lang.PREFIX.toString() + "&6/purpletrespass add [region] " + "&9- Add region to blacklist");
        msg(Lang.PREFIX.toString() + "&6/purpletrespass remove [region] " + "&9- Remove region from blacklist");
        msg(Lang.PREFIX.toString() + "&6/purpletrespass reload " + "&9- Reload config");


        msg(Lang.PREFIX.toString() + "&4&lVersion: " + ChatColor.AQUA + PurpleTrespass.getInstance().getDescription().getVersion());


    }
}
