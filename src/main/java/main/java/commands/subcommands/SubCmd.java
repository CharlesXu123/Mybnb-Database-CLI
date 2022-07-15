package main.java.commands.subcommands;

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

public class SubCmd {
    public Connection conn;
    public SubCmd(){
        try {
            Dotenv dotenv = Dotenv.configure().load();
            String url = "jdbc:mysql://127.0.0.1/c43Project";
            String username = "root";
            String pass = dotenv.get("PASSWORD");
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, username, pass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
