package main.java.commands.subcommands;

import main.java.commands.subcommands.hostCmd.AddHost;
import main.java.commands.subcommands.hostCmd.CancelBooking;
import main.java.commands.subcommands.hostCmd.DeleteHost;
import main.java.commands.subcommands.hostCmd.MyBookings;
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
        })
public class Host implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1;
    }
}
