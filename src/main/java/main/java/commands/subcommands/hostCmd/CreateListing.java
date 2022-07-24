package main.java.commands.subcommands.hostCmd;

import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.sql.Statement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "CreateListing",
        description = "host can add a listing and associate with the host himself"
)
public class CreateListing extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;
    @CommandLine.Option(names = {"-hId"}, description = "host's uId", required = true)
    String hId;

    @CommandLine.Option(names = {"-type"}, description = "listing type (can only be room, full house, apartment)", required = true)
    String type;

    @CommandLine.Option(names = {"-latitude"}, description = "listing's latitude", required = true)
    String latitude;

    @CommandLine.Option(names = {"-longitude"}, description = "listing's longitude", required = true)
    String longitude;

    @CommandLine.Option(names = {"-postalCode"}, description = "postal code of listing", required = true)
    String postal_code;

    @CommandLine.Option(names = {"-city"}, description = "listing's city", required = true)
    String city;

    @CommandLine.Option(names = {"-country"}, description = "listing's country", required = true)
    String country;

    private void parseInput() {
        hId = hId.replace("&", " ");
        type = type.replace("&", " ");
        latitude = longitude.replace("&", " ");
        postal_code = postal_code.replace("&", " ");
        city = city.replace("&", " ");
        country = country.replace("&", " ");
    }

    @Override
    public Integer call() throws Exception {
        parseInput();
        try{
            String query = """
                DROP TRIGGER IF EXISTS insert_listing_trigger;
                CREATE TRIGGER insert_listing_trigger
                    AFTER INSERT ON listing
                    FOR EACH ROW
                BEGIN
                    INSERT INTO owned VALUES ('%s',NEW.lId);
                end;
                
                INSERT INTO listing(type,latitude,longitude,postal_code,city,country)
                VALUES ('%s','%s','%s','%s','%s','%s');
                
                DROP TRIGGER insert_listing_trigger
                """;
            Statement st = this.conn.createStatement();
            query = String.format(query, hId, type, latitude, longitude, postal_code, city, country);
            st.executeUpdate(query);
            System.out.println("listing added");
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
