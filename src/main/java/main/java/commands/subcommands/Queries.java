package main.java.commands.subcommands;


import main.java.commands.subcommands.ExecuteQueries.AddressSearch;
import main.java.commands.subcommands.ExecuteQueries.LatSearch;
import main.java.commands.subcommands.ExecuteQueries.PostalSearch;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "Query",
        mixinStandardHelpOptions = true,
        description = "this is the Query tool ",
        subcommands = {
                LatSearch.class,
                PostalSearch.class,
                AddressSearch.class
        })
public class Queries implements Callable<Integer> {
    public static void main(String[] args) {

    }

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
