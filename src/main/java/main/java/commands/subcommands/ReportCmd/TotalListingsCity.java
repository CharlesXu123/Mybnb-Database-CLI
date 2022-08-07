package main.java.commands.subcommands.ReportCmd;

import main.java.commands.subcommands.SubCmd;

import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "TotalListingsCity",
        description = """
                find the total listings per country and city"""
)
public class TotalListingsCity extends SubCmd implements Callable<Integer> {

    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("TotalListingPostalCode: ");
            Statement st = this.conn.createStatement();
            String query =
                    """
                        SELECT distinct country, city, COUNT(city)
                        FROM listing
                        group by country, city
                        order by country, city
                    """;
            ResultSet resultSet = st.executeQuery(query);
            String[] args = {"country","city", "total listing in the city"};
            Utils.printResult(args, resultSet);
        }catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
