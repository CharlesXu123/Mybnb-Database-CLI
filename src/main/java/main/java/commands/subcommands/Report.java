package main.java.commands.subcommands;

import main.java.commands.subcommands.ReportCmd.ListingWordCloud;
import main.java.commands.subcommands.ReportCmd.NumberOfBookings;
import main.java.commands.subcommands.renterCmd.*;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "Report",
        mixinStandardHelpOptions = true,
        description = "reports",
        subcommands = {
                NumberOfBookings.class,
                ListingWordCloud.class
        })
public class Report implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
                return 1;
        }
}
