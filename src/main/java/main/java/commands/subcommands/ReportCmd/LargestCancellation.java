package main.java.commands.subcommands.ReportCmd;


import com.kennycason.kumo.WordFrequency;
import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static main.java.commands.subcommands.Utils.getWordCloud;

@CommandLine.Command(
        name = "LargestCancellation",
        description = """
                return the hosts and renters"""
)
public class LargestCancellation extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("Set of most popular noun phrases associated with the listing: ");
            String query1 = "SELECT lId FROM listing";
            Statement st1 = this.conn.createStatement();
            ResultSet rs1 = st1.executeQuery(query1);
            while (rs1.next()) {
                String lId = rs1.getString("lId");
                List<String> texts = new ArrayList<>();
                Statement st = this.conn.createStatement();
                String query =
                        """
                            SELECT l.lId, renter_comments
                            FROM rented r LEFT JOIN listing l on r.lId = l.lId
                            WHERE renter_comments IS NOT NULL AND l.lId = '%s'
                            ORDER BY lId;
                        """;
                query = String.format(query, lId);
                ResultSet resultSet = st.executeQuery(query);
                while (resultSet.next()) {
                    texts.add(resultSet.getString("renter_comments"));
                }
                List<WordFrequency> wordFrequencies = getWordCloud(texts);
                String wordCloud = "";
                int count = 0;
                for (WordFrequency wordFrequency:wordFrequencies) {
                    if (count > 5) {
                        break;
                    }
                    wordCloud = wordCloud + " | " + wordFrequency.getWord();
                    count = count + 1;
                }
                System.out.println(lId + ": " + wordCloud.substring(3));
            }
        }catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
