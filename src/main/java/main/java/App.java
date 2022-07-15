package main.java;

import main.java.commands.Mybnb;
import picocli.CommandLine;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new CommandLine(new Mybnb()).execute(args);
    }
}
