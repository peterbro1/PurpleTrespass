package me.gmx.purpletrespass.command.purpletrespass;

import me.gmx.purpletrespass.PurpleTrespass;
import me.gmx.purpletrespass.config.Lang;
import me.gmx.purpletrespass.core.BSubCommand;
import org.bukkit.Sound;

import java.util.List;

public class CmdPurpleTrespassRemove extends BSubCommand {

    public CmdPurpleTrespassRemove(){
        this.aliases.add("remove");
        this.aliases.add("delete");
        this.permission = "purpletrespass.remove";
        this.correctUsage = "/purpletrespass remove [region]";
        this.senderMustBePlayer = false;
    }

    @Override
    public void execute(){
        if (this.args.length != 1) {
            sendCorrectUsage();
            return;
        }

        List<String> _del = PurpleTrespass.getInstance().getRegionConfig().getStringList("blacklist");
        if (!_del.contains(args[0])){
            msg(String.format("&4Region %s not found!",new Object[]{args[0]}));
            return;
        }
        _del.remove(args[0]);
        PurpleTrespass.getInstance().getRegionConfig().set("blacklist",_del);
        PurpleTrespass.getInstance().saveRegionConfig();
        PurpleTrespass.getInstance().reloadRegionConfig();
        msg(Lang.MSG_REGIONREMOVED.toString());
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);

    }
}
