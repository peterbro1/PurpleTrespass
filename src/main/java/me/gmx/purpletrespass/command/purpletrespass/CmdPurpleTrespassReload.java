package me.gmx.purpletrespass.command.purpletrespass;

import me.gmx.purpletrespass.PurpleTrespass;
import me.gmx.purpletrespass.config.Lang;
import me.gmx.purpletrespass.core.BSubCommand;

public class CmdPurpleTrespassReload extends BSubCommand {


    public CmdPurpleTrespassReload(){
        this.aliases.add("reload");
        this.permission = "purpletrespass.reload";
        this.correctUsage = "/purpletrespass reload";
        this.senderMustBePlayer = false;
    }
    @Override
    public void execute() {
        PurpleTrespass.getInstance().reloadConfig();
        PurpleTrespass.getInstance().reloadRegionConfig();
        PurpleTrespass.getInstance().reloadTimer();
        msg(Lang.MSG_CONFIGRELOADED.toMsg());
    }

}
