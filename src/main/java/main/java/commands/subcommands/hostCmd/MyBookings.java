package main.java.commands.subcommands.hostCmd;

import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.sql.*;
import java.util.concurrent.Callable;
import main.java.commands.subcommands.Utils;

@CommandLine.Command(
        name = "MyBookings",
        description = "host can get all their bookings here"
)
public class MyBookings extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;
    @CommandLine.Option(names = {"-hId"}, description = "host's Id", required = true)
    String hId;

    @CommandLine.Option(names = {"-unfinished"}, description = "only show unfinished bookings", negatable = true)
    boolean showUnfinished;

    private void parseInput() {
        hId = hId.replace("%", " ");
    }

    @Override
    public Integer call() throws Exception {
        parseInput();
        try {
            String query;
            if (showUnfinished) {
                query = "SELECT * from rented WHERE hId=%s AND end_date > CURDATE();";
            } else {
                query = "SELECT * from rented WHERE hId=%s";
            }
            Statement st = this.conn.createStatement();
            query = String.format(query, hId);
            ResultSet resultSet = st.executeQuery(query);
            String[] args = {"rentedId","rId","lId","hId","start_date","end_date","canceled","host_rating","renter_rating","host_comments","renter_comments"};
            Utils.printResult(args,resultSet);
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
