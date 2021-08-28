package me.gmx.purpletrespass.core;


import me.gmx.purpletrespass.PurpleTrespass;

import java.util.ArrayList;

public class BCommand {

    public PurpleTrespass main;
    public ArrayList<BSubCommand> subcommands;

    public BCommand(PurpleTrespass ins) {
        this.main = ins;
        subcommands = new ArrayList<BSubCommand>();
    }

}
