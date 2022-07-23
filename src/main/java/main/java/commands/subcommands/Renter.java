package main.java.commands.subcommands;

import main.java.commands.subcommands.renterCmd.AddRenter;
import main.java.commands.subcommands.renterCmd.CancelBooking;
import main.java.commands.subcommands.renterCmd.DeleteRenter;
import main.java.commands.subcommands.renterCmd.MyBookings;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "Renter",
        mixinStandardHelpOptions = true,
        description = "renter's tool bar",
        subcommands = {
                AddRenter.class,
                DeleteRenter.class,
                MyBookings.class,
                CancelBooking.class
        })
public class Renter implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1;
    }
}
