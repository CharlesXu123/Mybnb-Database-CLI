# Mybnb Database CLI

This project is to build a simple command line interface tool that interacts with Mybnb database in mysql. It allows direct access and changes to the database with the proper input. The target users are admins or some backend api, they have root access to the database and can run reports to understand the data.

The project doesn't have a login system to validate the user identity or a complex parser to validate and sanitize user inputs. It does have basic input validation such as checking if the input is a date, it cannot validate if the input address is correct, so users are responsible for what they put in the database.

[Complete Documentation and User manual](https://docs.google.com/document/d/1_E9HEIFYJf2TkhxW_CuDHhsUBc7a6nC949AJCODEu5w/edit?usp=sharing)

# Prerequisite 
 - Have a `.env` file with env variable `PASSWORD` <br />
   or directly change variable `pass` in `src/main/java/main/java/commands/subcommands/SubCmd.java`
 - Have a MySQL serve set up and initialize databases by running `mysqlFiles/initialize.sql`

# How to run
  - Click the green run button in IntelliJ mavan package, it will generate `c43Project-1.0-jar-with-dependencies.jar` under `/target`
  
    <img width="358" alt="Screen Shot 2022-08-18 at 7 05 29 PM" src="https://user-images.githubusercontent.com/78982364/185510057-5a198fee-404c-44ef-a173-d5301f2a2fac.png">
  - Run command using `java -jar /target/c43Project-1.0-jar-with-dependencies.jar [commands from user manual]`

  - Or directly run main class in `src/main/java/main/java/commands/Mybnb.java` given commands from user manual as arguments

