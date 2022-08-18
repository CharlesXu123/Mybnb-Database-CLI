package main.java.commands.subcommands;

import java.sql.*;

public class SubCmd {
    public Connection conn;

    public SubCmd() {
        try {
//            Dotenv dotenv = Dotenv.configure().load();
//            String pass = dotenv.get("PASSWORD");
            String url = "jdbc:mysql://127.0.0.1/c43Project?allowMultiQueries=true";
            String username = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, username, "apart1571709");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUp(ResultSet rs, Statement st) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
        if (this.conn != null) {
            this.conn.close();
        }
    }
}
