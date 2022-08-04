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

    @CommandLine.Option(names = {"-city"}, description = "city for report", required = true)
    String city;

    @CommandLine.Option(names = {"-startDate"}, description = "start date", required = true)
    String start_date;

    @CommandLine.Option(names = {"-endDate"}, description = "end date", required = true)
    String end_date;

    @CommandLine.Option(names = {"-zip"}, description = "zip code")
    String zip="not given";

    private void parseInput() {
        city = city.replace("&", " ");
        start_date = start_date.replace("&", " ");
        end_date = end_date.replace("&", " ");
        zip = zip.replace("&", " ");
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
            if (!zip.equals("not given")) {
                query =
                """
                    SELECT COUNT(*)
                    FROM rented r JOIN listing l on r.lId = l.lId
                    WHERE r.start_date >= '%s' AND r.end_date <= '%s' AND l.city = '%s' AND l.postal_code = '%s';
                """;
                query = String.format(query, start_date, end_date, city, zip);
            }
            else  {
                query =
                        """
                            SELECT COUNT(*)
                            FROM rented r JOIN listing l on r.lId = l.lId
                            WHERE r.start_date >= '%s' AND r.end_date <= '%s' AND l.city = '%s';
                        """;
                query = String.format(query, start_date, end_date, city);
            }
            Statement st = this.conn.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            String[] args = {"number of bookings"};
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
