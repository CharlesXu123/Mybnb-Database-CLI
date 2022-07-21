package main.java.commands.subcommands;

import main.java.commands.subcommands.DeleteSub.Host;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "Delete",
        mixinStandardHelpOptions = true,
        subcommands = {
                Host.class
        })
public class Delete implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1;
    }
}
