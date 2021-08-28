package me.gmx.purpletrespass.command;

import me.gmx.purpletrespass.PurpleTrespass;
import me.gmx.purpletrespass.command.purpletrespass.*;
import me.gmx.purpletrespass.config.Lang;
import me.gmx.purpletrespass.core.BCommand;
import me.gmx.purpletrespass.core.BSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdPurpleTrespass extends BCommand implements CommandExecutor {

    public CmdPurpleTrespass(PurpleTrespass instance) {
        super(instance);
        this.subcommands.add(new CmdPurpleTrespassHelp());
        this.subcommands.add(new CmdPurpleTrespassReload());
        this.subcommands.add(new CmdPurpleTrespassAdd());
        this.subcommands.add(new CmdPurpleTrespassList());
        this.subcommands.add(new CmdPurpleTrespassRemove());
    }

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        if (arg3.length == 0) {
            arg0.sendMessage(Lang.MSG_UNKNOWNCOMMAND.toString());
            return true;
        }
        for (BSubCommand cmd : this.subcommands) {
            if (cmd.aliases.contains(arg3[0])) {
                cmd.execute(arg0, arg3);
                return true;
            }
        }
        arg0.sendMessage(Lang.MSG_purpletrespass_USAGE.toMsg());

        return true;
    }

}
