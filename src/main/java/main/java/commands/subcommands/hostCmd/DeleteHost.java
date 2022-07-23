package main.java.commands.subcommands.hostCmd;

import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.sql.PreparedStatement;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "Delete",
        description = "delete a host"
)
public class DeleteHost extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-uId"}, description = "host's uId", required = true)
    String uId;

    private void parseInput() {
        uId = uId.replace("&", " ");
    }

    @Override
    public Integer call() throws Exception {
        parseInput();
        try{
            String query = """
                DELETE FROM host
                WHERE host.uId = (?);
                """;
            PreparedStatement pst = this.conn.prepareStatement(query);
            pst.setString(1, uId);
            pst.executeUpdate();
            System.out.println("host deleted");
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
