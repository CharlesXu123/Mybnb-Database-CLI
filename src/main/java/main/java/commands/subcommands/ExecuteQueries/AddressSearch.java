package main.java.commands.subcommands.ExecuteQueries;

import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

//import static main.java.commands.subcommands.Utils.validTime;

@CommandLine.Command(
        name = "AddressSearch",
        description = "show all the listings available atm"
)

public class AddressSearch extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;
    @CommandLine.Option(names = {"-address"}, description = "address", required = true)
    String addr;

    @CommandLine.Option(names = {"-start_date"}, description = "start date", required = false)
    String start_date = "not given";
    //  format YYYY-MM-DD
    @CommandLine.Option(names = {"-end_date"}, description = "end date", required = false)
    String end_date = "not given";

    private void parseInput() {
        addr = addr.replace("&", " ");
    }

    @Override
    public Integer call() {

        try {
            parseInput();
            Statement st = this.conn.createStatement();
            String query = new String();            
            PreparedStatement pst = null;
            if (!start_date.equals("not given") && !end_date.equals("not given")
                    && Utils.validTime(start_date, end_date)) {
                query = """
                        SELECT lId, type, address, latitude,longitude, postal_code, city, country 
                        FROM listing
                        WHERE address = (?) and lId not in (Select lId
                                                            from available
                                                            where available.query_date >= (?) 
                                                            && available.query_date <= (?) 
                                                            && available.available = 0)
                        """;
                pst = this.conn.prepareStatement(query);
                pst.setString(1, addr);
                pst.setDate(2, java.sql.Date.valueOf(start_date));
                pst.setDate(3, java.sql.Date.valueOf(end_date));

            } else if (start_date.equals("not given")) {
                query = """
                        SELECT * FROM listing WHERE address = (?)
                        """;
                pst = this.conn.prepareStatement(query);
                pst.setString(1,addr);
            }
            ResultSet resultSet = pst.executeQuery();
            String[] str = {"lId", "type", "latitude", "longitude", "postal_code", "city", "country"};
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
