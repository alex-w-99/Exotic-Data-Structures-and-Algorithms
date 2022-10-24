package DataStructures.SkipList;

import java.util.Scanner;

public class SkipListDriver {

  public static void main(String[] args) {
    SkipList sl = new SkipList();

    System.out.println("Please enter a command. For example, \"insert 4\", \"delete 8\", "
            + "\"search 11\", \"print\", etc. Enter \"quit\" to exit.");

    Scanner console = new Scanner(System.in);

    while (!console.hasNext("quit")) {  // case insensitive
      String expression = console.nextLine(); // gets the next line from the Scanner
      String[] newCommand = expression.split("\\s+");

      // scanning and executing valid commands...

      // INSERT:
      if (newCommand[0].equalsIgnoreCase("insert")) {
        try {
          int newKey = Integer.parseInt(newCommand[1]);
          sl.Insert(newKey);  // inserting new nodes/keys
        }
        catch (NumberFormatException e) {
          System.out.println("Please \"insert\" only integers! For example, \"insert 8\".");
        }
      }

      // DELETE:
      else if (newCommand[0].equalsIgnoreCase("delete")) {
        try {
          int newKey = Integer.parseInt(newCommand[1]);
          sl.Delete(newKey);  // deleting specified node/key (if exists)
        }
        catch (NumberFormatException e) {
          System.out.println("Please \"delete\" only integers! For example, \"delete 8\".");
        }
      }

      // SEARCH:
      else if (newCommand[0].equalsIgnoreCase("search")) {
        try {
          int newKey = Integer.parseInt(newCommand[1]);
          System.out.println(sl.Search(newKey));  // printing key/node (if exists)
        }
        catch (NumberFormatException e) {
          System.out.println("Please \"search\" for only integers! For example, \"search 8\".");
        }
      }

      // PRINT:
      else if (newCommand[0].equalsIgnoreCase("print")) {
        System.out.println(sl);
      }

      // INVALID COMMAND GIVEN:
      else {
        System.out.println("Please enter a valid command. For example, \"insert 4\", \"delete 8\", "
                + "\"search 11\", \"print\", etc. Enter \"quit\" to exit.");
      }

      System.out.println("\nPlease enter a command (\"quit\" to exit).");
    }

    System.out.println("Goodbye!");
    console.close();
  }
}