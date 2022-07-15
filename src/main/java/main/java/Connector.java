package main.java;

import java.sql.*;

public class Connector {
    public static void main (String[] args) throws Exception {
        String url = "jdbc:mysql://127.0.0.1/c43Project";
        String username = "root";
        String pass = "apart1571709";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, pass);

        Statement st = conn.createStatement();
        String query = "select * from Course";
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getString("cNum"));
            System.out.println(rs.getString("dept"));
        }
        query = """
           create table a (                
                  name int primary key
                  );
            """;
        boolean b_rs = st.execute(query);
        System.out.println(b_rs);
        st.close();
        conn.close();
    }

}
