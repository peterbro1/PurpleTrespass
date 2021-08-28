package me.gmx.purpletrespass.command.purpletrespass;

import me.gmx.purpletrespass.PurpleTrespass;
import me.gmx.purpletrespass.config.Lang;
import me.gmx.purpletrespass.core.BSubCommand;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.List;

public class CmdPurpleTrespassAdd extends BSubCommand {

    public CmdPurpleTrespassAdd(){
        this.aliases.add("add");
        this.permission = "purpletrespass.add";
        this.correctUsage = "/purpletrespass add [region]";
        this.senderMustBePlayer = false;
    }

    @Override
    public void execute(){
        if (this.args.length != 1) {
            sendCorrectUsage();
            return;
        }

        if (!PurpleTrespass.getInstance().wgp.getRegionManager(player.getWorld()).hasRegion(args[0])){
            msg(String.format("&4Region %s not found!",new Object[]{args[0]}));
            return;
        }
        List<String> _del = PurpleTrespass.getInstance().getRegionConfig().getStringList("blacklist");
        _del.add(args[0]);
        PurpleTrespass.getInstance().getRegionConfig().set("blacklist",_del);
        PurpleTrespass.getInstance().saveRegionConfig();
        PurpleTrespass.getInstance().reloadRegionConfig();
        PurpleTrespass.getInstance().reloadTimer();
        msg(Lang.MSG_REGIONADDED.toString());
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);



    }
}
