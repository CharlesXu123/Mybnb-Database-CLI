package main.java;

import java.sql.*;

public class Connector {
    public static void main (String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/c43Project";
        String username = "root";
        String pass = "Tony8968@@";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, pass);

        Statement st = conn.createStatement();
        String query = "select * from listing";
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getString("uid"));
        }
//        query = """
//           create table a (
//                  name int primary key
//                  );
//            """;
//        boolean b_rs = st.execute(query);
//        System.out.println(b_rs);
        st.close();
        conn.close();
    }

}
