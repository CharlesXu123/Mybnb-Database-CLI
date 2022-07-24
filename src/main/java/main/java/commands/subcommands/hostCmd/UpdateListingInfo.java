package main.java.commands.subcommands.hostCmd;

//    A host should be able to update the price of a listing, but only if the listing is
//    available for rent in the specific date range the change is to be made.
//    Otherwise, if the listing is booked the change cannot take place and the host
//    should be informed.

//    A host cannot change the availability of a listing on a date it is booked; that
//    can only happen through a cancellation (the host has to cancel the booking
//    that day). The host can change the availability of a listing on a date it is
//    available (and make it unavailable for rent that date).

import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.sql.Statement;
import java.util.concurrent.Callable;

import static main.java.commands.subcommands.Utils.validTime;

@CommandLine.Command(
        name = "UpdateListingInfo",
        description = "host can update listing's availability and price use this command")
public class UpdateListingInfo extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @CommandLine.Option(names = {"-lId"}, description = "listing Id", required = true)
    String lId;

    @CommandLine.Option(names = {"-startDate"}, description = "start date for update", required = true)
    String start_date;

    @CommandLine.Option(names = {"-endDate"}, description = "end date for update", required = true)
    String end_date;

    @CommandLine.Option(names = {"-price"}, description = "update the listing price between startDate and endDate if given")
    String price = "not given";

    @CommandLine.Option(names = {"-setUnavailable"}, description = "set listing as unavailable between startDate and endDate if given", negatable = true)
    boolean setUnavailable;

    private void parseInput() {
        lId = lId.replace("&", " ");
        start_date = start_date.replace("&", " ");
        end_date = end_date.replace("&", " ");
        price = price.replace("&", " ");
    }
    @Override
    public Integer call() throws Exception {
        parseInput();
        if (!validTime(start_date, end_date)) {
            System.err.println("invalid startDate or endDate");
            return 0;
        }
        try {
            Statement st = this.conn.createStatement();
            String query = """
                    DROP TRIGGER IF EXISTS update_available_trigger;
                    CREATE TRIGGER update_available_trigger
                        BEFORE UPDATE ON available
                        FOR EACH ROW
                        BEGIN
                            IF EXISTS(SELECT * FROM rented WHERE rented.lId=NEW.lId AND ((start_date between '%s' AND '%s') OR (end_date between '%s' AND '%s')) AND canceled=false) THEN
                                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'listing unavailable during this period';
                            end if;
                        end;
                    """;
            query = String.format(query, start_date, end_date, start_date, end_date);
            st.executeUpdate(query);
            if (!price.equals("not given")) {
                String query1 = "UPDATE available SET price='%s' WHERE lId = '%s' AND query_date >= '%s' AND query_date <= '%s';";
                query1 = String.format(query1, price, lId, start_date, end_date);
                st.executeUpdate(query1);
            }
            String query2;
            if (setUnavailable) {
                query2 = "UPDATE available SET available=false WHERE lId = '%s' AND query_date >= '%s' AND query_date <= '%s';";
            }
            else {
                query2 = "UPDATE available SET available=true WHERE lId = '%s' AND query_date >= '%s' AND query_date <= '%s';";
            }
            query2 = String.format(query2, lId, start_date, end_date);
            st.executeUpdate(query2);
            System.out.println("update listing info");
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
