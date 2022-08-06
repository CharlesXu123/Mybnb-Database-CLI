package main.java.commands.subcommands.hostCmd;

import main.java.commands.subcommands.SubCmd;
//import main.java.commands.subcommands.Utils;
import picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "DeleteListing",
        description = "host can delete listing use this command"
)
public class DeleteListing extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @CommandLine.Option(names = {"-lId"}, description = "listing Id", required = true)
    String lId;

    private void parseInput() {
        lId = lId.replace("&", " ");
    }

    @Override
    public Integer call() throws Exception {
        parseInput();
        try {
            String query = """
                DROP TRIGGER IF EXISTS delete_listing_trigger;
                CREATE TRIGGER delete_listing_trigger
                BEFORE DELETE ON listing
                FOR EACH ROW
                BEGIN
                    UPDATE rented SET canceled = true WHERE rented.lId = OLD.lId AND rented.end_date > CURDATE();
                end;
            
            DELETE FROM listing WHERE lId='%s';
            
            DROP TRIGGER delete_listing_trigger;
            """;
            Statement st = this.conn.createStatement();
            query = String.format(query, lId);
            st.executeUpdate(query);
            System.out.println("listing deleted");
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
