package main.java.commands.subcommands.ReportCmd;

import main.java.commands.subcommands.SubCmd;

import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "TotalListings",
        description = """
                find the hosts that have a number of listings that is more than 10% of the number of listings in that
                city and country"""
)
public class TotalListings extends SubCmd implements Callable<Integer> {

    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("Commercial hosts: ");
            Statement st = this.conn.createStatement();
            String query =
                    """
                        SELECT distinct country, COUNT(lId)
                        FROM listing
                        group by country
                    """;
            ResultSet resultSet = st.executeQuery(query);
            String[] args = {"country", "total listing in the region"};
            Utils.printResult(args, resultSet);
        }catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
