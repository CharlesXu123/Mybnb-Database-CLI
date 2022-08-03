package main.java.commands.subcommands;


import java.sql.ResultSet;
import java.util.ArrayList;

public class Utils {
    public static void printResult(String[] args, ResultSet resultSet) {
        //helper to print the result
        //args is the list of column names
        try {
            for (String s : args) {
                //Do your stuff here
                System.out.print(s);
                System.out.print(" ");
            }
            System.out.println();
            int columnsNumber = args.length;
//            ArrayList<String>
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue + " ");
//                + rsmd.getColumnName(i)
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
        }
    }


    public void SearchByPostal(String[] str, ResultSet resultSet, String postal) {
        //this utils return the listing if the first 3 characters match with desired postal code
        try {
            for (String s : str) {
                System.out.print(s);
                System.out.print("  ");
            }
            System.out.println();
            ArrayList<String[]> listings = new ArrayList<>();
            int columnsNumber = str.length;
            while (resultSet.next()) {
                String cur_postal = resultSet.getString(6);
                cur_postal = cur_postal.substring(0, 3);
                postal = postal.substring(0, 3);
                if (cur_postal.equals(postal)) {
                    String[] lst = new String[columnsNumber + 2];
                    for (int i = 1; i <= columnsNumber; i++) {
                        lst[i - 1] = resultSet.getString(i);
                    }
                    listings.add(lst);
                }
            }

            for (int i = 0; i < listings.size(); i++) {
                for (int j = 0; j < columnsNumber; j++) {
                    if (j > 0) System.out.print(",  ");
                    System.out.print(listings.get(i)[j] + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
        }
    }
}