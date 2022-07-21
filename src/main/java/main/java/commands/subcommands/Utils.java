package main.java.commands.subcommands;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Utils {
    public static void printResult(String[] args, ResultSet resultSet) {
    try {
        for (String s : args) {
            //Do your stuff here
            System.out.print(s);
            System.out.print(" ");
        }
        System.out.println("");

        int columnsNumber = args.length;
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " ");
//                + rsmd.getColumnName(i)
            }
            System.out.println("");
        }
    }catch(Exception e){
        System.err.println("Got an error!");
        System.err.println(e);
    }
    }
}
