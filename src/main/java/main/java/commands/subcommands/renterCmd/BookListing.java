package main.java.commands.subcommands.renterCmd;

import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.sql.Statement;
import java.util.concurrent.Callable;

import static main.java.commands.subcommands.Utils.validTime;

@CommandLine.Command(
        name = "BookListing",
        description = "renter can book a listing use this command"
)
public class BookListing extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @CommandLine.Option(names = {"-rId"}, description = "renter Id", required = true)
    String rId;

    @CommandLine.Option(names = {"-lId"}, description = "listing Id", required = true)
    String lId;

    @CommandLine.Option(names = {"-startDate"}, description = "start date of the booking", required = true)
    String start_date;

    @CommandLine.Option(names = {"-endDate"}, description = "end date of the booking", required = true)
    String end_date;

    private void parseInput() {
        rId = rId.replace("&", " ");
        lId = lId.replace("&", " ");
        start_date = start_date.replace("&", " ");
        end_date = end_date.replace("&", " ");
    }
    @Override
    public Integer call() throws Exception {
        parseInput();
        if (!validTime(start_date, end_date)) {
            System.err.println("invalid startDate or endDate");
            return 0;
        }
        try{
            String query = """
                DROP TRIGGER IF EXISTS create_booking_trigger;
                CREATE TRIGGER create_booking_trigger
                    BEFORE INSERT ON rented
                    FOR EACH ROW
                BEGIN
                    IF NEW.start_date > NEW.end_date OR NEW.start_date < CURDATE() THEN
                        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'rent date invalid';
                    END IF;
                    IF EXISTS (SELECT * FROM available WHERE available.lId=NEW.lId AND available.query_date>=NEW.start_date AND available.query_date<=NEW.end_date AND available=false) THEN
                        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'listing unavailable during this period';
                    end if;
                    UPDATE available SET available=false WHERE available.lId=NEW.lId AND available.query_date>=NEW.start_date AND available.query_date<=NEW.end_date;
                END;
                
                INSERT INTO rented(rId, lId, hId, start_date, end_date) values ('%s','%s',(SELECT uId FROM owned WHERE owned.lId='%s'),'%s','%s');
                
                DROP TRIGGER create_booking_trigger;
                """;
            Statement st = this.conn.createStatement();
            query = String.format(query, rId, lId, lId, start_date, end_date);
            st.executeUpdate(query);
            System.out.println("booking created");
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
