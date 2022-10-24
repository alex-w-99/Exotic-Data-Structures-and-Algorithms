package DataStructures.RedBlackTree;

import java.util.Scanner;

public class RBTreeDriver {

  public static void main(String [] args) {
    RBTree T = new RBTree();
    System.out.println("Please provide a space delimited input array of integers to build a "
            + "Red-Black Tree. For example, \"7 23 8 13 11 6 5 78\".");

    Scanner console = new Scanner(System.in);
    String inputArray = console.nextLine();
    String[] inputs = inputArray.split("\\s+");
    for (String i : inputs) {
      try {
        int newKey = Integer.parseInt(i);
        T.Insert(new RBNode(newKey));  // inserting new nodes/keys
      }
      catch (NumberFormatException e) {
        System.out.println("Please insert only integers separated by spaces for input array!");
        System.out.println("Exiting...");
        System.exit(0);
      }
    }

    System.out.println("Tree height = " + T.getHeight());

    System.out.println("\nPlease enter a command. For example, \"insert 5\", \"delete 4\", "
            + "\"search 2\", \"successor 11\", \"predecessor 5\", \"sort\", \"root\", \"min\", "
            + "\"max\", etc. Enter \"quit\" to exit.");
    while (!console.hasNext("quit")) {  // case insensitive
      String expression = console.nextLine(); // gets the next line from the Scanner
      String[] newCommand = expression.split("\\s+");

      // scanning and executing commands for valid RedBlackTree commands:
      // INSERT:
      if (newCommand[0].equalsIgnoreCase("insert")) {
        try {
          int newKey = Integer.parseInt(newCommand[1]);
          T.Insert(new RBNode(newKey));  // inserting new nodes/keys
        }
        catch (NumberFormatException e) {
          System.out.println("Please \"insert\" only integers! For example, \"insert 8\".");
        }
      }

      // DELETE:
      else if (newCommand[0].equalsIgnoreCase("delete")) {
        try {
          int specifiedKey = Integer.parseInt(newCommand[1]);
          int searchKey = T.Search(T.getRoot(), specifiedKey).getKey();

          if (searchKey == -999) {  // i.e., key/node does not exist in tree
            System.out.println("No such key exists in the Red-Black Tree!");
          }
          else {  // i.e., key/node does exist in tree
            T.Delete(T.Search(T.getRoot(), specifiedKey));  // deleting node/key
          }
        }
        catch (NumberFormatException e) {
          System.out.println("Please \"delete\" only integers! For example, \"delete 8\".");
        }
      }

      // SEARCH:
      else if (newCommand[0].equalsIgnoreCase("search")) {
        try {
          int searchKey = Integer.parseInt(newCommand[1]);
          int nodeKey = T.Search(T.getRoot(), searchKey).getKey();  // searching for specified key

          if (nodeKey == -999) {  // i.e., key/node does not exist in tree
            System.out.println("No such key exists in the Red-Black Tree!");
          }
          else {  // i.e., key/node does exist in tree
            System.out.println("Search successful; printing node key: " + nodeKey);
          }
        }
        catch (NumberFormatException e) {
          System.out.println("Please \"search\" only integers! For example, \"search 8\".");
        }
      }

      // SORT:
      else if (newCommand[0].equalsIgnoreCase("sort")) {
        T.Sort(T.getRoot());
      }

      // SUCCESSOR:
      else if (newCommand[0].equalsIgnoreCase("successor")) {
        try {
          int successorKey = Integer.parseInt(newCommand[1]);
          System.out.println(T.Successor(T.Search(T.getRoot(), successorKey)).getKey());
        }
        catch (NumberFormatException e) {
          System.out.println("Please search for \"successor\" integer keys only! "
                  + "For example, \"successor 8\".");
        }
      }

      // PREDECESSOR:
      else if (newCommand[0].equalsIgnoreCase("predecessor")) {
        try {
          int predecessorKey = Integer.parseInt(newCommand[1]);
          System.out.println(T.Predecessor(T.Search(T.getRoot(), predecessorKey)).getKey());
        }
        catch (NumberFormatException e) {
          System.out.println("Please search for \"predecessor\" integer keys only! "
                  + "For example, \"predecessor 8\".");
        }
      }

      // TREE ROOT:
      else if (newCommand[0].equalsIgnoreCase("root")) {
        System.out.println("Tree root = " + T.getRoot().getKey());
      }

      // MIN:
      else if (newCommand[0].equalsIgnoreCase("min")) {
        System.out.println("Tree min = " + T.Min(T.getRoot()).getKey());
      }

      // MAX:
      else if (newCommand[0].equalsIgnoreCase("max")) {
        System.out.println("Tree max = " + T.Max(T.getRoot()).getKey());
      }

      // INVALID COMMAND GIVEN:
      else {
        System.out.println("Please enter a valid command. For example, \"insert 5\", \"search 2\", "
                + "\"sort\", \"predecessor 9\", \"successor 1\", \"min\", \"max\", \"root\", etc.");
      }

      // printing tree height after command:
      System.out.println("Tree height = " + T.getHeight());

      System.out.println("\nPlease enter a command (\"quit\" to exit).");
    }

    System.out.println("Goodbye!");
    console.close();
  }
}