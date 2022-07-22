package main.java.commands.subcommands.ExecuteQueries;

import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "LatSearch",
        description = "Search listings by latitude longitude within 20kms"
)

public class LatSearch extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;
    @CommandLine.Option(names = {"-latitude"}, description = "latitude", required = true)
    double lat1;

    @CommandLine.Option(names = {"-longitude"}, description = "longitude", required = true)
    double long1;

    @Override
    public Integer call() {
        try {
            Statement st = this.conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * from listing");
            String[] str = {"ListingID", "RoomType", "address", "latitude", "longitude", "postal_code", "city", "country"};
            Utils utl = new Utils();
            utl.SearchByLatLong(str, resultSet, lat1, long1);
            st.close();
            this.conn.close();
        } catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
