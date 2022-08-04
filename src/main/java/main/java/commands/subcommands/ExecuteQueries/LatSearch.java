package main.java.commands.subcommands.ExecuteQueries;

import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.PreparedStatement;
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

    @CommandLine.Option(names = {"-start_date"}, description = "start date", required = false)
    String start_date = "not given";
    //  format YYYY-MM-DD
    @CommandLine.Option(names = {"-end_date"}, description = "end date", required = false)
    String end_date = "not given";

    @Override
    public Integer call() {
        try {
            if (!start_date.equals("not given") && !end_date.equals("not given")
                    && Utils.validTime(start_date, end_date)){

            }

            Statement st = this.conn.createStatement();
            String query = """
                    SELECT lId, type, address, latitude,longitude, postal_code, city, country 
                    FROM listing
                    WHERE ((acos((sin(latitude * (PI() / 180))) * sin((?) * (PI() / 180)) + cos(latitude * (PI() / 180)) * cos((?) * (PI() / 180)) * cos((longitude * (PI() / 180) - (?) * (PI() / 180))))) * 6371) <= 20;
                    """;

            PreparedStatement pst = this.conn.prepareStatement(query);
            pst.setDouble(1, lat1);
            pst.setDouble(2, lat1);
            pst.setDouble(3, long1);
            ResultSet resultSet = pst.executeQuery();
            String[] str = {"ListingID", "RoomType", "address", "latitude", "longitude", "postal_code", "city", "country"};

            Utils.printResult(str, resultSet);
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
