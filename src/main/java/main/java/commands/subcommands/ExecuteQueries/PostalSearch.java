package main.java.commands.subcommands.ExecuteQueries;


import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "PostalSearch",
        description = "Search listings by latitude longitude within 20kms"
)

public class PostalSearch extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;
    @CommandLine.Option(names = {"-postal_code"}, description = "postal code", required = true)
    String postal;


    @Override
    public Integer call() {
        try {
            postal = postal.replace("&", " ");
            System.out.println("Postal is" + postal);
            Statement st = this.conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * from listing");
            String[] str = {"ListingID", "RoomType", "address", "latitude", "longitude", "postal_code", "city", "country"};
            Utils utl = new Utils();
            utl.SearchByPostal(str, resultSet, postal);
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
