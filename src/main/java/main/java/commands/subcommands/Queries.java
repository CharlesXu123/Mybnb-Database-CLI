package main.java.commands.subcommands;


import main.java.commands.subcommands.ExecuteQueries.ShowListings;
import main.java.commands.subcommands.InsertSub.Renter;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "Query",
        mixinStandardHelpOptions = true,
        description = "this is the Query tool ",
        subcommands = {
                ShowListings.class
        })
public class Queries implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return null;
    }

    public static void main(String[] args) {

    }
}
