package main.java.commands.subcommands.ExecuteQueries;

import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "AddressSearch",
        description = "show all the listings available atm"
)

public class AddressSearch extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;
    @CommandLine.Option(names = {"-address"}, description = "address", required = true)
    String addr;

    private void parseInput() {
        addr = addr.replace("&", " ");
    }

    @Override
    public Integer call() {
        try {
            parseInput();
            Statement st = this.conn.createStatement();
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM listing WHERE address = ?");
            stmt.setString(1, addr);
            ResultSet resultSet = stmt.executeQuery();
//            ResultSet resultSet = st.executeQuery("SELECT * from listing WHERE address=addr");
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
