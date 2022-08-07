package main.java.commands.subcommands.ToolkitCmd;

import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "SuggestAmenities",
        description = "good amenities to have"
)
public class SuggestAmenities extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @CommandLine.Option(names = {"-lId"}, description = "listing Id", required = true)
    String lId;

    private void parseInput() {
        lId = lId.replace("%", " ");
    }

    @Override
    public Integer call() throws Exception {
        parseInput();
        try {
            String query = """
                SELECT @city := listing.city, @country := listing.country, @zip := listing.postal_code
                FROM listing
                WHERE lId='%s';
            """;
            Statement st = this.conn.createStatement();
            query = String.format(query, lId);
            st.executeQuery(query);
            String query1 = """
                    SELECT amenity, COUNT(*) / (SELECT COUNT(*)
                                                     FROM listing
                                                     WHERE city=@city AND country=@country AND SUBSTR(postal_code, 1, 3)=SUBSTR(@zip, 1, 3)) * 100
                    FROM amenity a JOIN has h on a.aId = h.aId JOIN listing l on l.lId = h.lId
                    WHERE city=@city AND country=@country AND SUBSTR(postal_code, 1, 3)=SUBSTR(@zip, 1, 3) AND NOT EXISTS(SELECT has.aId
                                                                                                                          FROM has
                                                                                                                          WHERE has.aId = a.aId AND has.lId = '%s')
                    GROUP BY h.aId
                    HAVING COUNT(*) > (SELECT COUNT(*)
                                       FROM listing
                                       WHERE city=@city AND country=@country AND SUBSTR(postal_code, 1, 3)=SUBSTR(@zip, 1, 3))/2;
                    """;
            Statement st1 = this.conn.createStatement();
            query1 = String.format(query1, lId);
            ResultSet resultSet = st1.executeQuery(query1);
            String[] args = {"amenity", "percent of nearby listing with this amenity"};
            Utils.printResult(args, resultSet);
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
