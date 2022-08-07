package main.java.commands.subcommands.renterCmd;

import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.sql.PreparedStatement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "Delete",
        description = "operation to delete renter")
public class DeleteRenter extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;
    @CommandLine.Option(names = {"-uId"}, description = "renter's uId", required = true)
    String uId;

    private void parseInput() {
        uId = uId.replace("%", " ");
    }
    @Override
    public Integer call() throws Exception {
        parseInput();
        try{
            String query = """
                DELETE FROM renter
                WHERE renter.uId = (?);
                """;
            PreparedStatement pst = this.conn.prepareStatement(query);
            pst.setString(1, uId);
            pst.executeUpdate();
            System.out.println("renter deleted");
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
