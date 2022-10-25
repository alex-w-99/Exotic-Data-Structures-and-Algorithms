package DataStructures.FibonacciHeap;

public class FibonacciHeapDriver {

  public static void main(String[] args) {
    FibonacciHeap fibHeap = new FibonacciHeap();

    System.out.println("fibHeap.size = " + fibHeap.size);
    System.out.println(fibHeap);
    System.out.println("\n");

    fibHeap.insert(7);
    fibHeap.insert(1);
    fibHeap.insert(3);
    fibHeap.insert(35);

    System.out.println("fibHeap.extractMin() = " + fibHeap.extractMin());
    System.out.println("fibHeap.size = " + fibHeap.size);
    System.out.println(fibHeap);
    System.out.println("\n");

    fibHeap.delete(35);
    fibHeap.insert(44);
    fibHeap.insert(-1);

    System.out.println("fibHeap.extractMin() = " + fibHeap.extractMin());
    System.out.println("fibHeap.size = " + fibHeap.size);
    System.out.println(fibHeap);
    System.out.println("\n");

    fibHeap.delete(88);  // bad delete; no such node exists

    System.out.println("fibHeap.extractMin() = " + fibHeap.extractMin());
    System.out.println("fibHeap.size = " + fibHeap.size);
    System.out.println(fibHeap);
    System.out.println("\n");

    System.out.println("fibHeap.decreaseKey(44, -44);");
    System.out.println("fibHeap.size = " + fibHeap.size);
    fibHeap.decreaseKey(44, -44);
    System.out.println(fibHeap);
    System.out.println("\n");

    System.out.println("fibHeap.extractMin() = " + fibHeap.extractMin());
    System.out.println("fibHeap.size = " + fibHeap.size);
    System.out.println(fibHeap);
    System.out.println("\n");

    System.out.println("fibHeap.emptyHeap();");
    fibHeap.emptyHeap();
    System.out.println("fibHeap.size = " + fibHeap.size);
    System.out.println(fibHeap);
  }
}
