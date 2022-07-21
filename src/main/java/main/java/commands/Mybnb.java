package main.java.commands;

import main.java.commands.subcommands.Delete;
import main.java.commands.subcommands.Test;
import main.java.commands.subcommands.Insert;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "Mybnb",
        mixinStandardHelpOptions = true,
        description = "this is the mybnb tool that helps to manage mybnb database",
        requiredOptionMarker = '*',
        header = "mybnb CLI",
        parameterListHeading = "Params are:%n",
        optionListHeading = "Options are:%n",
        subcommands = {
                Insert.class,
                Delete.class,
                Test.class
        })
public class Mybnb implements Callable<Integer> {
    final Integer SUCCESS = 0;
    final Integer FAIL = 1;

    @Option(names = {"-h", "-help"}, usageHelp = true, description = "show help")
    boolean help;

    @Override
    public Integer call() throws Exception {
        System.out.println("mybnb");
        return SUCCESS;
    }

    public static void main(String[] args) {
        new CommandLine(new Mybnb()).execute(args);
    }
}
