package main.java.commands.subcommands;

import main.java.commands.subcommands.ToolkitCmd.SuggestAmenities;
import main.java.commands.subcommands.ToolkitCmd.SuggestListingPrice;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "Toolkit",
        mixinStandardHelpOptions = true,
        description = "toolkit",
        subcommands = {
                SuggestListingPrice.class,
                SuggestAmenities.class
        })
public class Toolkit implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1;
    }
}