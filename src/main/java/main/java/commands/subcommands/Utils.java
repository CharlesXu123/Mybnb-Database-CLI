package main.java.commands.subcommands;

import dnl.utils.text.table.TextTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.text.*;
import java.util.Date;

public class Utils {
    public static void printResult(String[] args, ResultSet resultSet) {
        List<Object[]> data = new ArrayList<>();
        try {
            int columnsNumber = args.length;
            while (resultSet.next()) {
                Object[] row = new String[columnsNumber];
                for (int i = 1; i <= columnsNumber; i++) {
                    row[i-1] = resultSet.getString(i);
                }
                data.add(row);
            }
            TextTable tt = new TextTable(args,data.toArray(new Object[0][]));
            tt.setAddRowNumbering(true);
            tt.setSort(0);
            tt.printTable();
        }catch(Exception e){
            System.err.println("Got an error!");
            System.err.println(e);
        }
    }

    public static boolean validTime (String start_date, String end_date) {
        try {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sdformat.parse(start_date);
            Date d2 = sdformat.parse(end_date);
            if(d1.compareTo(d2) <= 0) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}