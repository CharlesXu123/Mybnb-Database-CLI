package main.java.commands.subcommands;

import java.sql.Connection;
import java.sql.DriverManager;

public class SubCmd {
    public Connection conn;
    public SubCmd(){
        try {
            String url = "jdbc:mysql://127.0.0.1/c43TUT";
            String username = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, username, pass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
