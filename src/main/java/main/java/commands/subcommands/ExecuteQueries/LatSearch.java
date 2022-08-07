package main.java.commands.subcommands.ExecuteQueries;

import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    @CommandLine.Option(names = {"-lowest_price"}, description = "lowest price", required = false)
    Double lowest_price = -1.0;

    @CommandLine.Option(names = {"-highest_price"}, description = "highest price", required = false)
    Double highest_price = -1.0;
    @CommandLine.Option(names = {"-amenities"}, description = "highest price", required = false)
    String amenitiies = "not given";

    @Override
    public Integer call() {
        try {
            String[] arrOfStr = amenitiies.split(",");
            String query = new String();
            String amenities_query = new String();
            PreparedStatement pst = null;
            if (Utils.validTime(start_date, end_date) && Utils.validPrice(lowest_price, highest_price)) {

                query = """
                        SELECT lId, type, address, latitude,longitude, postal_code, city, country 
                        FROM listing lst
                        WHERE (((acos((sin(latitude * (PI() / 180))) * sin((?) * (PI() / 180)) + cos(latitude * (PI() / 180)) * cos((?) * (PI() / 180)) * cos((longitude * (PI() / 180) - (?) * (PI() / 180))))) * 6371) <= 20)
                                            and lst.lId not in (Select lId
                                                            from available
                                                            where available.query_date >= (?) 
                                                            && available.query_date <= (?) 
                                                            && available.available = 0
                                                            )
                                            and lst.lId not in (SELECT lId
                                                            from available
                                                            where available.price <= (?)
                                                                || available.price >= (?)
                                                                && available.lId = lst.lId)
                                            and lst.lId in ((Select lId
                                                             from has
                                                             where has.lId = lst.lId && """;

                int amen_len = arrOfStr.length;
                amenities_query = " ((has.aId =" + arrOfStr[0] + ")";
                for (int i = 1; i < amen_len; i++) {
                    amenities_query = amenities_query + " || " + "(has.aId = " + arrOfStr[i] + ")";
                }
                amenities_query = amenities_query + ") ";
                amenities_query = amenities_query + "group by lId " + "having count(lId) = " + amen_len + "))";

                query = query + amenities_query;

                pst = this.conn.prepareStatement(query);
                pst.setDouble(1, lat1);
                pst.setDouble(2, lat1);
                pst.setDouble(3, long1);
                pst.setDate(4, java.sql.Date.valueOf(start_date));
                pst.setDate(5, java.sql.Date.valueOf(end_date));
                pst.setDouble(6, lowest_price);
                pst.setDouble(7, highest_price);
            } else if (start_date.equals("not given")) {
                query = """
                        SELECT lId, type, address, latitude,longitude, postal_code, city, country 
                        FROM listing
                        WHERE ((acos((sin(latitude * (PI() / 180))) * sin((?) * (PI() / 180)) + cos(latitude * (PI() / 180)) * cos((?) * (PI() / 180)) * cos((longitude * (PI() / 180) - (?) * (PI() / 180))))) * 6371) <= 20;
                        """;

                pst = this.conn.prepareStatement(query);
                pst.setDouble(1, lat1);
                pst.setDouble(2, lat1);
                pst.setDouble(3, long1);
            } else {
                return 0;
            }
            ResultSet resultSet = pst.executeQuery();
            String[] str = {"lId", "type", "latitude", "longitude", "postal_code", "city", "country"};
            Utils.printResult(str, resultSet);
            this.conn.close();

        } catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
