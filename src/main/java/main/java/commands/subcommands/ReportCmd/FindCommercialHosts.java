package main.java.commands.subcommands.ReportCmd;

import com.kennycason.kumo.WordFrequency;
import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static main.java.commands.subcommands.Utils.getWordCloud;
import static main.java.commands.subcommands.Utils.printResult;

@CommandLine.Command(
        name = "FindCommercialHosts",
        description = "find the hosts that have a number of listings that is more than 10% of the number of listings in that city and country"
)
public class FindCommercialHosts extends SubCmd implements Callable<Integer> {

    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("Commercial hosts: ");
            Statement st = this.conn.createStatement();
            String query =
                    """
                        SELECT country, city, h.uId, name, count(*), (SELECT COUNT(*)
                                                                      FROM listing
                                                                      WHERE listing.country = l.country AND listing.city = l.city) totol
                        FROM listing l JOIN owned o on l.lId = o.lId JOIN host h on h.uId = o.uId
                        GROUP BY country, city, uId, name
                        HAVING COUNT(*) > (SELECT COUNT(*)
                                           FROM listing
                                           WHERE listing.country = l.country AND listing.city = l.city)/10;
                    """;
            ResultSet resultSet = st.executeQuery(query);
            String[] args = {"country", "city", "uId", "name", "listing owned", "total listing in the region"};
            printResult(args, resultSet);
        }catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
