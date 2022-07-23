package main.java.commands.subcommands.hostCmd;

import main.java.commands.subcommands.SubCmd;
import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "UpdateAmenities",
        description = "host can update amenities use this command")
public class UpdateAmenities extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @CommandLine.Option(names = {"-lId"}, description = "listing Id", required = true)
    String lId;
    @CommandLine.Parameters(paramLabel = "Amenity", description = "one or more amenities to update")
    String[] amenities;

    @CommandLine.Option(names = {"-add"}, description = "flag to add amenities instead update", negatable = true)
    boolean add;

    @CommandLine.Option(names = {"-showAmenities"}, description = "flag to show all amenities",negatable = true)
    boolean showAmenities;

    private void parseInput() {
        lId = lId.replace("&", " ");
        for (int i = 0; i<amenities.length; i++) {
            amenities[i] = amenities[i].replace("&", " ");
        }
    }

    @Override
    public Integer call() throws Exception {
        parseInput();
        try {
            Statement st = this.conn.createStatement();
            if (showAmenities) {
                String query = "SELECT amenity from amenity;";
                ResultSet resultSet = st.executeQuery(query);
                String[] args = {"amenity"};
                Utils.printResult(args,resultSet);
            }
            if (amenities.length > 0) {
                if (!add) {
                    String query1 = "DELETE FROM has WHERE lId='%s';";
                    query1 = String.format(query1, lId);
                    st.executeUpdate(query1);
                }
                for (String amenity: amenities) {
                    String query1 = "INSERT INTO has VALUES ('%s',(SELECT aId FROM amenities WHERE amenity='%s'));";
                    query1 = String.format(query1, lId, amenity);
                    st.executeUpdate(query1);
                }
                System.out.println("amenities updated");
            }
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
