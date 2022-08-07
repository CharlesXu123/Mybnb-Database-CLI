package main.java.commands.subcommands.hostCmd;

import main.java.commands.subcommands.*;
import picocli.CommandLine;

import java.sql.*;
import java.util.concurrent.Callable;
//import main.java.commands.subcommands.Utils;

@CommandLine.Command(
        name = "MyListings",
        description = "host can get all their listings here"
)
public class MyListings extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;
    @CommandLine.Option(names = {"-hId"}, description = "host's Id", required = true)
    String hId;

    private void parseInput() {
        hId = hId.replace("%", " ");
    }

    @Override
    public Integer call() throws Exception {
        parseInput();
        try {
            String query;
            query = "SELECT listing.* from listing, owned WHERE owned.uId=%s AND listing.lId=owned.lId;";
            Statement st = this.conn.createStatement();
            query = String.format(query, hId);
            ResultSet resultSet = st.executeQuery(query);
            String[] args = {"lId","type","latitude","longitude","postal_code","city","country"};
            Utils.printResult(args,resultSet);
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
