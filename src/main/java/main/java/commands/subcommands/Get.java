package main.java.commands.subcommands;

import main.java.commands.Mybnb;
import picocli.CommandLine;

import java.sql.*;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "Get",
        mixinStandardHelpOptions = true,
        description = "this is the get tool ")
public class Get extends SubCmd implements Callable {

    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @CommandLine.Parameters(index = "0", description = "des")
    String database;

    @Override
    public Object call() throws Exception {
        Statement st = this.conn.createStatement();
        String query = "select * from " + database;
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        return 1;
    }
}
