package main.java.commands.subcommands.ReportCmd;

import main.java.commands.subcommands.SubCmd;

import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "RankRentersByTime",
        description = """
                rank the renters by number of booking in a time period
                """
)
public class RankRentersByTime extends SubCmd implements Callable<Integer> {

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
            System.out.println("Rank renters: ");
//            Statement st = this.conn.createStatement();
            String query =
                    """
                     select name, count(*) count
                     from renter r join rented r2 on r.uId = r2.rId
                     where r2.start_date >= (?) and r2.end_date <= (?)
                     group by rId
                     order by count(*) desc
                    """;
            PreparedStatement pst = this.conn.prepareStatement(query);
            pst.setDate(1, Date.valueOf(start_date));
            pst.setDate(2, Date.valueOf(end_date));
            ResultSet resultSet = pst.executeQuery();
//            String[] args = {"listingId", "room type", "a","b","c","d","e","f","g", "h", "i", "j", "k", "l", "m"};
            String[] args= {"Name", "Number of Bookings"};
            Utils.printResult(args, resultSet);
        }catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
