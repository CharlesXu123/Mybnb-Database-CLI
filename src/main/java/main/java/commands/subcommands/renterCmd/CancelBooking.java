package main.java.commands.subcommands.renterCmd;

import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.sql.Statement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "CancelBooking",
        description = "renter can cancel a booking use this command"
)
public class CancelBooking extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @CommandLine.Option(names = {"-rentedId"}, description = "rented(booking) Id",required = true)
    String rentedId;

    @CommandLine.Option(names = {"-rId"}, description = "renter Id", required = true)
    String rId;

    private void parseInput() {
        rentedId = rentedId.replace("%", " ");
        rId =  rId.replace("%", " ");
    }

    public Integer call() {
        parseInput();
        try{
            String query = """
                DROP TRIGGER IF EXISTS cancel_booking_trigger;
                CREATE TRIGGER cancel_booking_trigger
                    BEFORE UPDATE ON rented
                    FOR EACH ROW
                    BEGIN
                        IF NEW.end_date < CURDATE() THEN
                            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'you can not cancel complete booking!';
                        end if;
                        UPDATE available SET available=true WHERE available.lId = NEW.lId AND available.query_date >= NEW.start_date AND available.query_date<= NEW.end_date;
                    end;
                
                UPDATE rented SET canceled=true WHERE rentedId='%s' AND rId='%s';
                
                DROP TRIGGER cancel_booking_trigger;
                """;
            Statement st = this.conn.createStatement();
            query = String.format(query,rentedId,rId);
            st.executeUpdate(query);
            System.out.println("order canceled");
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }

}
