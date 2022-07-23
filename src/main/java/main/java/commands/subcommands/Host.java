package main.java.commands.subcommands;

import main.java.commands.subcommands.hostCmd.*;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "Host",
        mixinStandardHelpOptions = true,
        description = "host tool bar",
        subcommands = {
                AddHost.class,
                DeleteHost.class,
                MyBookings.class,
                CancelBooking.class,
                CreateListing.class,
                MyListings.class,
                UpdateAmenities.class,
                UpdateListingInfo.class
        })
public class Host implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1;
    }
}
