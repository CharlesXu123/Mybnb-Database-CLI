package main.java.commands.subcommands.ReportCmd;


import com.kennycason.kumo.WordFrequency;
import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static main.java.commands.subcommands.Utils.getWordCloud;

@CommandLine.Command(
        name = "LargestCancellation",
        description = """
                return the hosts and renters with the largest cancellation in a year"""
)

public class LargestCancellation extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @CommandLine.Option(names = {"-start_date"}, description = "start date", required = false)
    String start_date = "2022-01-01";
    //  format YYYY-MM-DD
    @CommandLine.Option(names = {"-end_date"}, description = "end date", required = false)
    String end_date = "2022-12-31";

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("Host with largest cancellation ");
            String query =
                    """         
                                select * from (
                                SELECT h.name, count(h.name) count
                                FROM host h join rented r on r.hId = h.uId
                                where r.start_date >= (?) and r.end_date <= (?)
                                group by h.name
                                ) sub order by sub.count desc limit 1
                            """;
            PreparedStatement pst = this.conn.prepareStatement(query);
            pst.setDate(1, Date.valueOf(start_date));
            pst.setDate(2, Date.valueOf(end_date));
            ResultSet resultSet = pst.executeQuery();
            String[] args= {"Name", "Number of Bookings"};
            Utils.printResult(args, resultSet);
            System.out.println();
            System.out.println("Renter with largest cancellation ");
             query =
                    """         
                        select * from (
                        SELECT r.name, count(r.name) count
                        FROM renter r join rented rt on rt.hId = r.uId
                        where rt.start_date >= (?) and rt.end_date <= (?)
                        group by r.name
                        ) sub order by sub.count desc limit 1
                    """;
             pst = this.conn.prepareStatement(query);
            pst.setDate(1, Date.valueOf(start_date));
            pst.setDate(2, Date.valueOf(end_date));
            resultSet = pst.executeQuery();
            Utils.printResult(args, resultSet);

        } catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
