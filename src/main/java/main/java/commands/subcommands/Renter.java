package main.java.commands.subcommands;

import main.java.commands.subcommands.renterCmd.AddRenter;
import main.java.commands.subcommands.renterCmd.DeleteRenter;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "Renter",
        mixinStandardHelpOptions = true,
        description = "renter's tool bar",
        subcommands = {
                AddRenter.class,
                DeleteRenter.class
        })
public class Renter implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1;
    }
}
