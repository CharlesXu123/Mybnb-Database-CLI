package main.java.commands.subcommands.InsertSub;

import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import java.sql.*;

@CommandLine.Command(
        name = "host",
        description = "host can create account using this command"
)
public class Host extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;
    @CommandLine.Option(names = {"-name"}, description = "renter name", required = true)
    String name;

    @CommandLine.Option(names = {"-address"}, description = "renter address", required = true)
    String address;

    @CommandLine.Option(names = {"-DOB"}, description = "renter's date of birth", required = true)
    String data_of_birth;

    @CommandLine.Option(names = {"-occupation"}, description = "renter's occupation", required = true)
    String occupation;

    private void parseInput() {
        name = name.replace("&", " ");
        address = address.replace("&", " ");
        data_of_birth = data_of_birth.replace("&", " ");
        occupation = occupation.replace("&", " ");
    }

    @Override
    public Integer call() {
        parseInput();
        try{
            String query = """
                INSERT INTO host (name,address,date_of_birth,occupation)
                VALUES
                    ((?),(?),(?),(?));
                """;
            PreparedStatement pst = this.conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, address);
            pst.setString(3, data_of_birth);
            pst.setString(4, occupation);
            pst.executeUpdate();
            System.out.println("host created");
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
