package main.java.commands.subcommands;

import java.sql.ResultSet;

public class Utils {
    public static void printResult(String[] args, ResultSet resultSet) {
        try {
            for (String s : args) {
                //Do your stuff here
                System.out.print(s);
                System.out.print(" ");
            }
            System.out.println();

            int columnsNumber = args.length;
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

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        var R = 6371; // Radius of the earth in km
        var dLat = deg2rad(lat2 - lat1);  // deg2rad below
        var dLon = deg2rad(lon2 - lon1);
        var a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var d = R * c; // Distance in km
        return d;
    }

    public static double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }


    public static void SearchByLatLong(String[] args, ResultSet resultSet, double lat1, double long1) {
        try {
            for (String s : args) {
                System.out.print(s);
                System.out.print("  ");
            }
            System.out.println();

            int columnsNumber = args.length;
            while (resultSet.next()) {
                double lat2 = Double.parseDouble(resultSet.getString(3));
                double long2 = Double.parseDouble(resultSet.getString(4));
                if (calculateDistance(lat1, long1, lat2, long2) <= 20) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = resultSet.getString(i);
                        System.out.print(columnValue + " ");
//                + rsmd.getColumnName(i)
                    }
                    System.out.println();
                }

            }
        } catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
        }
    }

}