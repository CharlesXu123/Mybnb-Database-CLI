package main.java.commands.subcommands;

import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import dnl.utils.text.table.TextTable;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
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
                    row[i - 1] = resultSet.getString(i);
                }
                data.add(row);
            }
            TextTable tt = new TextTable(args, data.toArray(new Object[0][]));
            tt.setAddRowNumbering(true);
            tt.setSort(0);
            tt.printTable();
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

        public static boolean validTime (String start_date, String end_date){
            try {
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                Date d1 = sdformat.parse(start_date);
                Date d2 = sdformat.parse(end_date);
                if (d1.compareTo(d2) <= 0) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
            return false;
        }


        public static List<WordFrequency> getWordCloud (List < String > texts) {
            final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
            Collection<String> stopWords = new ArrayList<>();
            stopWords.add("the");
            stopWords.add("a");
            stopWords.add("an");
            stopWords.add("and");
            stopWords.add("that");
            stopWords.add("for");
            stopWords.add("it");
            stopWords.add("he");
            stopWords.add("she");
            frequencyAnalyzer.setStopWords(stopWords);
            final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(texts, true);
            return wordFrequencies;
        }
    }
