package DataStructures.BinomialHeap;

public class BinomialHeapDriver {

  public static void main(String[] args) {
    BinomialHeap bh = new BinomialHeap();
    System.out.println("Creating empty binomial heap.");
    System.out.println("bh.isEmpty() = " + bh.isEmpty());System.out.println("bh.size = " + bh.size);
    System.out.println("================================================================");

    // inserting nodes; printing, getting size, getting global minimum attribute
    bh.insert(12);
    bh.insert(8);
    System.out.println("Printing binomial heap...");
    bh.uglyPrint(bh.head);
    System.out.println("bh.size = " + bh.size);
    System.out.println("bh.getGlobalMinimum() = " + bh.getGlobalMinimum());
    System.out.println("================================================================");

    // inserting node; printing, getting size, getting global minimum attribute
    bh.insert(5);
    System.out.println("Printing binomial heap...");
    bh.uglyPrint(bh.head);
    System.out.println("bh.size = " + bh.size);
    System.out.println("bh.getGlobalMinimum() = " + bh.getGlobalMinimum());
    System.out.println("================================================================");

    // inserting nodes; printing, getting size, getting global minimum attribute, checking if empty
    bh.insert(15);
    bh.insert(7);
    bh.insert(2);
    bh.insert(9);
    System.out.println("Printing binomial heap...");
    bh.uglyPrint(bh.head);
    System.out.println("bh.size = " + bh.size);
    System.out.println("bh.minimum() = " + bh.minimum());
    System.out.println("bh.getGlobalMinimum() = " + bh.getGlobalMinimum());
    System.out.println("bh.isEmpty() = " + bh.isEmpty());
    System.out.println("================================================================");

    // deleting nodes; printing, getting size, getting global minimum attribute, checking if empty
    bh.delete(15);
    bh.delete(2);
    System.out.println("Printing binomial heap...");
    bh.uglyPrint(bh.head);
    System.out.println("bh.size = " + bh.size);
    System.out.println("bh.minimum() = " + bh.minimum());
    System.out.println("bh.getGlobalMinimum() = " + bh.getGlobalMinimum());
    System.out.println("================================================================");

    bh.decreaseKey(12,11);
    System.out.println("Decreased 12 to 11; Printing binomial heap...");
    bh.uglyPrint(bh.head);
    System.out.println("bh.getGlobalMinimum() = " + bh.getGlobalMinimum());
    System.out.println("================================================================");

    System.out.println("bh.extractMin() = " + bh.extractMin());
    System.out.println("Printing binomial heap...");
    bh.uglyPrint(bh.head);
    System.out.println("bh.getGlobalMinimum() = " + bh.getGlobalMinimum());
    System.out.println("================================================================");

    bh.emptyHeap();
    System.out.println("bh.emptyHeap()");
    System.out.println("Printing binomial heap...");
    bh.uglyPrint(bh.head);
    System.out.println("bh.isEmpty() = " + bh.isEmpty());
    System.out.println("bh.size = " + bh.size);
  }
}
