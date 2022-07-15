package main.java.commands.subcommands;

import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "Insert",
        mixinStandardHelpOptions = true,
        description = "this is the get tool ")
public class Insert implements Callable {
    @Override
    public Object call() throws Exception {
        return null;
    }
}
