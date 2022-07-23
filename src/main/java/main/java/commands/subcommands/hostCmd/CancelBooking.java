package main.java.commands.subcommands.hostCmd;

import picocli.CommandLine;

@CommandLine.Command(
        name = "CancelBooking",
        description = "host can cancel a booking use this command"
)
public class CancelBooking {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @CommandLine.Option(names = {"-hId"}, description = "host's Id")
    String hId;

}
