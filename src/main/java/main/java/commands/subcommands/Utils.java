package main.java.commands.subcommands;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public void SearchByLatLong(String[] args, ResultSet resultSet, double lat1, double long1) {
        try {
            for (String s : args) {
                System.out.print(s);
                System.out.print("  ");
            }
            System.out.println();
            ArrayList<String[]> listings = new ArrayList<>();
            int columnsNumber = args.length;
            while (resultSet.next()) {
                double lat2 = Double.parseDouble(resultSet.getString(3));
                double long2 = Double.parseDouble(resultSet.getString(4));
                double distance = calculateDistance(lat1, long1, lat2, long2);
                if (distance <= 20) {

                    String[] lst = new String[9];

                    for (int i = 1; i <= columnsNumber; i++) {
                        lst[i - 1] = resultSet.getString(i);
                    }
                    lst[7] = String.valueOf(distance);
                    listings.add(lst);
                }

            }
            Collections.sort(listings, new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    return Double.valueOf(Double.parseDouble(o1[7])).compareTo(Double.parseDouble(o2[7]));
                }
            });
            for (int i = 0; i < listings.size(); i++) {
                for (int j = 0; j < 8; j++) {
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