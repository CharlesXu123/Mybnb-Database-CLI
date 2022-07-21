package main.java.commands.subcommands;

import main.java.commands.subcommands.InsertSub.Renter;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "Insert",
        mixinStandardHelpOptions = true,
        description = "this is the get tool ",
        subcommands = {
                Renter.class
        })
public class Insert implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return null;
    }
}
