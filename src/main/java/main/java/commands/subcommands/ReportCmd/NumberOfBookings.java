package main.java.commands.subcommands.ReportCmd;

import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

import static main.java.commands.subcommands.Utils.validTime;

@CommandLine.Command(
        name = "NumberOfBookings",
        description = "run a report and provide the total number of bookings in a specific date range by city. Or Run the same report by zip code within a city"
)
public class NumberOfBookings extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @CommandLine.Option(names = {"-startDate"}, description = "start date", required = true)
    String start_date;

    @CommandLine.Option(names = {"-endDate"}, description = "end date", required = true)
    String end_date;

    @CommandLine.Option(names = {"-zip"}, description = "zip code", negatable = true)
    boolean zip;

    private void parseInput() {
        start_date = start_date.replace("%", " ");
        end_date = end_date.replace("%", " ");
    }

    @Override
    public Integer call() throws Exception {
        parseInput();
        if (!validTime(start_date, end_date)) {
            System.err.println("invalid date");
            return 0;
        }
        try {
            String query;
            if (zip) {
                query =
                """
                    SELECT COUNT(*), city, postal_code
                    FROM rented r JOIN listing l on r.lId = l.lId
                    WHERE r.start_date >= '%s' AND r.end_date <= '%s'
                    GROUP BY city, postal_code
                """;
            }
            else  {
                query =
                        """
                            SELECT COUNT(*), city
                            FROM rented r JOIN listing l on r.lId = l.lId
                            WHERE r.start_date >= '%s' AND r.end_date <= '%s'
                            GROUP BY city
                        """;
            }
            query = String.format(query, start_date, end_date);
            Statement st = this.conn.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            if (zip) {
                Utils.printResult(new String[]{"number of bookings", "city","postal_code"},resultSet);
            } else {
                Utils.printResult(new String[]{"number of bookings", "city"},resultSet);
            }
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
