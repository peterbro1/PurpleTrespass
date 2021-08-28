package me.gmx.purpletrespass.command.purpletrespass;

import me.gmx.purpletrespass.PurpleTrespass;
import me.gmx.purpletrespass.core.BSubCommand;
import org.bukkit.Sound;

import java.util.List;

public class CmdPurpleTrespassList extends BSubCommand {

    public CmdPurpleTrespassList(){
        this.aliases.add("list");
        this.permission = "purpletrespass.list";
        this.correctUsage = "/purpletrespass list";
        this.senderMustBePlayer = false;
    }

    @Override
    public void execute(){
        if (this.args.length != 0) {
            sendCorrectUsage();
            return;
        }
        msg("&6Blacklisted regions:");
        int i = 1;
        for (String s : PurpleTrespass.getInstance().getRegionConfig().getStringList("blacklist"))
            msg(String.format("&a%d) &2%s",new Object[]{i++,s}));
        msg("&6Disabled worlds:");
        i=1;
        for (String s : PurpleTrespass.getInstance().getRegionConfig().getStringList("disabled_worlds"))
            msg(String.format("&a%d) &2%s",new Object[]{i++,s}));

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
    }

}
