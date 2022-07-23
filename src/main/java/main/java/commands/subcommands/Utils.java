package main.java.commands.subcommands;

import dnl.utils.text.table.TextTable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

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
}