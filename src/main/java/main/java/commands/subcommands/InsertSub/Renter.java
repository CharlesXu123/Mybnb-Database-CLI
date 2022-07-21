package main.java.commands.subcommands.InsertSub;

import main.java.commands.subcommands.SubCmd;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import java.sql.*;

@CommandLine.Command(
        name = "renter",
        description = "renter can create account using this command"
)
public class Renter extends SubCmd implements Callable<Integer> {
    @CommandLine.Option(names = {"-name"}, description = "renter name", required = true)
    String name;

    @CommandLine.Option(names = {"-address"}, description = "renter address", required = true)
    String address;

    @CommandLine.Option(names = {"-DOB"}, description = "renter's date of birth", required = true)
    String data_of_birth;

    @CommandLine.Option(names = {"-occupation"}, description = "renter's occupation", required = true)
    String occupation;

    @CommandLine.Option(names = {"-paymentInfo"}, description = "renter's payment info", required = true)
    String payment_info;

    private void parseInput() {
        name = name.replace("&", " ");
        address = address.replace("&", " ");
        data_of_birth = data_of_birth.replace("&", " ");
        occupation = occupation.replace("&", " ");
        payment_info = payment_info.replace("&", " ");
    }

    @Override
    public Integer call() {
        parseInput();
        try{
            String query = """
                INSERT INTO renter (name,address,date_of_birth,occupation,payment_info)
                VALUES
                    ((?),(?),(?),(?),(?));
                """;
            PreparedStatement pst = this.conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, address);
            pst.setString(3, data_of_birth);
            pst.setString(4, occupation);
            pst.setString(5, payment_info);
            pst.executeUpdate();
            System.out.println("renter created");
        }
        catch (Exception e) {
            System.err.println("Got an error!");
            System.err.println(e);
            return 0;
        }
        return 1;
    }
}
