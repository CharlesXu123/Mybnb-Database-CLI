package main.java.commands.subcommands.ExecuteQueries;

import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import java.sql.*;

@CommandLine.Command(
        name = "Listing",
        description = "renter can create account using this command"
)

public class ShowListings extends SubCmd implements Callable<Integer> {


    @Override
    public Integer call() {
        try{
            Statement st = this.conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * from listing");
            String[] str = {"ListingID",  "RoomType", "latitude", "longitude", "postalcode", "city", "country"};
            Utils.printResult(str, resultSet);
            st.close();
            this.conn.close();
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
