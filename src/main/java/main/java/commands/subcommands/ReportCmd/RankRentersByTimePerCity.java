package main.java.commands.subcommands.ReportCmd;

import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "RankRentersByTimePerCity",
        description = """
                rank the renters by number of booking in a time period per city
                """
)
public class RankRentersByTimePerCity extends SubCmd implements Callable<Integer> {

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
            if (!Utils.validTime(start_date, end_date)) {
                System.out.println("Invalid Time given");
                return 0;
            }
            System.out.println("Rank renters: ");
            String query =
                    """
                             select city, name, count(name) c
                             from renter r join rented r2 on r.uId = r2.rId join listing l on r2.lId = l.lId
                             where r2.start_date >= (?) and r2.end_date <= (?)
                             group by city, name
                             having c >= 2
                             order by city, c desc
                            """;
            PreparedStatement pst = this.conn.prepareStatement(query);
            pst.setDate(1, Date.valueOf(start_date));
            pst.setDate(2, Date.valueOf(end_date));
            ResultSet resultSet = pst.executeQuery();
            String[] args = {"City", "Name", "Number of Bookings"};
            Utils.printResult(args, resultSet);
        } catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
