package main.java.commands.subcommands;

import main.java.commands.Mybnb;
import picocli.CommandLine;

import java.sql.*;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "Get",
        mixinStandardHelpOptions = true,
        description = "this is the get tool ")
public class Get implements Callable {
    public Connection conn;
    public Get() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://127.0.0.1/c43TUT";
        String username = "root";
        String pass = "apart1571709";
        Class.forName("com.mysql.cj.jdbc.Driver");

        this.conn = DriverManager.getConnection(url, username, pass);
    }

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
            System.out.println(rs.getString("cNum"));
            System.out.println(rs.getString("dept"));
        }
        return 1;
    }
}
