package main.java.commands.subcommands.hostCmd;

import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.sql.Statement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "CommentAndRate",
        description = "host can comment or rate his renting experience"
)
public class CommentAndRate extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @CommandLine.Option(names = {"-rentedId"}, description = "id for booking", required = true)
    String rentedId;

    @CommandLine.Option(names = {"-comment"}, description = "comment for the renting experience", required = true)
    String comment;

    @CommandLine.Option(names = {"-rating"}, description = "rate for the renting experience", required = true)
    String rating;

    private void parseInput() {
        rentedId = rentedId.replace("%", " ");
        comment =  comment.replace("%", " ");
        rating =  rating.replace("%", " ");
    }

    @Override
    public Integer call() throws Exception {
        parseInput();
        try{
            String query = """
                DROP TRIGGER IF EXISTS host_comment_trigger;
                CREATE TRIGGER host_comment_trigger
                    BEFORE UPDATE ON rented
                    FOR EACH ROW
                BEGIN
                    IF OLD.canceled = true THEN
                        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'can not comment or rate on canceled bookings';
                    end if;
                end;
                
                UPDATE rented SET host_comments='%s', host_rating='%s' WHERE rentedId='%s';
                
                DROP TRIGGER host_comment_trigger;
                """;
            Statement st = this.conn.createStatement();
            query = String.format(query,comment, rating, rentedId);
            st.executeUpdate(query);
            System.out.println("done");
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}