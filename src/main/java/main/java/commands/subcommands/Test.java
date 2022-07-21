package main.java.commands.subcommands;

import picocli.CommandLine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "Test",
        mixinStandardHelpOptions = true,
        description = "this is the get tool ")
public class Test extends SubCmd implements Callable {

    @CommandLine.Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @Override
    public Object call() throws Exception {
        Statement st = this.conn.createStatement();
        String query = """
                           INSERT INTO renter (uid, name,address,date_of_birth,occupation,payment_info)
                           VALUES
                               ('10','Preston donnell','6728 Aliquam Avenue','1999-06-07','Vulputate Lacus Institute','670654 6557434452');
                             """;

         if (1==2) {
             ResultSet rs = st.executeQuery(query);
             List<String> result_ls = new ArrayList<>();
             while(rs.next()){
                 result_ls.add(rs.getString(1));
             }
             for (String result: result_ls) {
                 System.out.println(result);
             }
         } else {
             st.executeUpdate(query);
         }

        return 1;
    }
}
