package main.java.commands.subcommands.ExecuteQueries;


import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "PostalSearch",
        description = "Search listings by nearby Postal Code"
)

public class PostalSearch extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;
    @CommandLine.Option(names = {"-postal_code"}, description = "postalcode", required = true)
    String postal = "";

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
            postal = postal.substring(0, 3);
            String[] arrOfStr = amenitiies.split(",");

            String query = new String();
            String amenities_query = new String();
            PreparedStatement pst = null;
            if (Utils.validPrice(lowest_price, highest_price) && Utils.validTime(start_date, end_date)) {

                query = """
                        SELECT * from listing lst
                        where SUBSTR(lst.postal_code, 1,3) = (?) 
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
                pst.setString(1, postal);
                pst.setDate(2, java.sql.Date.valueOf(start_date));
                pst.setDate(3, java.sql.Date.valueOf(end_date));
                pst.setDouble(4, lowest_price);
                pst.setDouble(5, highest_price);

            } else if (start_date.equals("not given")) {
                postal = postal.replace("&", " ");
                System.out.println("Postal is" + postal);
                Statement st = this.conn.createStatement();
                query = """
                        SELECT * from listing
                        where SUBSTR(listing.postal_code, 1,3) = (?)
                        """;
                pst = this.conn.prepareStatement(query);
                pst.setString(1, postal);
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
