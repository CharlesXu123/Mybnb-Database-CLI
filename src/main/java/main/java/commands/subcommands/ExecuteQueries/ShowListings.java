package main.java.commands.subcommands.ExecuteQueries;

import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "Listing",
        description = "show all the listings available atm"
)

public class ShowListings extends SubCmd implements Callable<Integer> {


    @Override
    public Integer call() {
        try {
            Statement st = this.conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * from listing");
            String[] str = {"lId", "type", "address", "latitude", "longitude", "postal_code", "city", "country"};
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
