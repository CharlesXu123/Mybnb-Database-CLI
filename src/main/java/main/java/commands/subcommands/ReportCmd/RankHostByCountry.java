package main.java.commands.subcommands.ReportCmd;

import main.java.commands.subcommands.SubCmd;

import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "RankHostByCountry",
        description = """
                rank the hosts by total number of listings they have per country
                """
)
public class RankHostByCountry extends SubCmd implements Callable<Integer> {

    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("Rank hosts: ");
            Statement st = this.conn.createStatement();
            String query =
                    """
                        SELECT country, h.name, count(h.name) v
                        FROM listing l join owned o on l.lId = o.lId join host h on h.uid = o.uId
                        group by country, h.name
                        order by count(h.name) desc
                    """;
            ResultSet resultSet = st.executeQuery(query);
//            String[] args = {"listingId", "room type", "a","b","c","d","e","f","g", "h", "i", "j", "k", "l", "m"};
            String[] args= {"Country","Host Name", " Number Listings Owned"};
            Utils.printResult(args, resultSet);
        }catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
