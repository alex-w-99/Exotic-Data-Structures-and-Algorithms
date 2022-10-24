package DataStructures.BinomialHeap;

public class BinomialHeap {
  public BinomialHeapNode head;
  public BinomialHeapNode globalMin;
  public int size;

  public BinomialHeap() {
    this.head = null;
    this.globalMin = null;
    this.size = 0;
  }

  public boolean isEmpty() {
    return this.head == null;
  }

  public void emptyHeap() {
    this.head = null;
    this.size = 0;
    this.globalMin = null;
  }

  public int minimum() {
    if (this.head == null) {
      return Integer.MAX_VALUE;
    }
    else {
      return this.head.getMinNode().key;
    }
  }

  public void insert(int key) {
    BinomialHeapNode newNode = new BinomialHeapNode(key);

    if (this.head == null) {  // i.e., binomial heap is empty
      this.head = newNode;
      this.size = 1;
    }
    else {  // i.e., binomial heap is non-empty
      this.union(newNode);
      this.size++;
    }

    this.globalMin = this.head.getMinNode();
  }

  // "merge" is a helper function for union
  private void merge(BinomialHeapNode node) {
    BinomialHeapNode x = this.head;
    BinomialHeapNode y = node;

    while (x != null && y != null) {
      if (x.degree == y.degree) {  // Case 1: cannot have multiple binomial trees of same degree
        BinomialHeapNode z = y;
        y = y.rightSibling;
        z.rightSibling = x.rightSibling;
        x.rightSibling = z;
        x = z.rightSibling;
      }

      else if (x.degree < y.degree) {  // Case 2: x has a lower degree than y
        if (x.rightSibling == null || x.rightSibling.degree > y.degree) {
          BinomialHeapNode z = y;
          y = y.rightSibling;
          z.rightSibling = x.rightSibling;
          x.rightSibling = z;
          x = z.rightSibling;
        }
        else {  // i.e., if (x.rightSibling != null && x.rightSibling.degree <= y.degree)
          x = x.rightSibling;
        }
      }

      else {  // Case 3: if x.degree > y.degree
        BinomialHeapNode z = x;
        x = y;
        y = y.rightSibling;
        x.rightSibling = z;

        if (z == this.head) {
          this.head = x;
        }
      }

    }

    // outside of while loop; updating x in case of null
    if (x == null) {
      x = this.head;

      while (x.rightSibling != null) {
        x = x.rightSibling;
      }

      x.rightSibling = y;
    }
  }

  public void union(BinomialHeapNode node) {
    this.merge(node);  // calling helper

    BinomialHeapNode previousX = null;
    BinomialHeapNode x = this.head;
    BinomialHeapNode nextX = this.head.rightSibling;

    while (nextX != null) {
      if (x.degree != nextX.degree
              || (nextX.rightSibling != null && nextX.rightSibling.degree == x.degree)) {
        previousX = x;
        x = nextX;
      }

      else {
        if (x.key <= nextX.key) {  // x becomes nextX's parent
          x.rightSibling = nextX.rightSibling;
          nextX.parent = x;
          nextX.rightSibling = x.leftmostChild;
          x.leftmostChild = nextX;
          x.degree++;
        }
        else {  // nextX becomes x's parent
          if (previousX == null) {
            this.head = nextX;
          } else {
            previousX.rightSibling = nextX;
          }

          x.parent = nextX;
          x.rightSibling = nextX.leftmostChild;
          nextX.leftmostChild = x;
          nextX.degree++;
          x = nextX;
        }
      }
      nextX = x.rightSibling;
    }
  }

  public void delete(int key) {
    if (this.head != null && this.head.lookup(key) != null) {  // if key exists...
      this.decreaseKey(key, Integer.MIN_VALUE);  // converting this node to have the smallest key
      this.extractMin();  // removing node with minimum value on binomial heap
    }

    this.globalMin = this.head.getMinNode();
  }

  public void decreaseKey(int oldKey, int newKey) {
    BinomialHeapNode x = this.head.lookup(oldKey);

    if (x != null) {
      x.key = newKey;  // setting new key
      BinomialHeapNode parentX = x.parent;

      while (parentX != null && x.key < parentX.key) {  // must find correct position for new x
        int xKey = x.key;
        x.key = parentX.key;
        parentX.key = xKey;

        // updating x and parentX for next while loop:
        x = parentX;
        parentX = parentX.parent;
      }
    }

    this.globalMin = this.head.getMinNode();
  }

  public int extractMin() {
    if (this.isEmpty()) {  // nothing to do if binomial heap empty
      return Integer.MAX_VALUE;
    }

    BinomialHeapNode x = this.head;
    BinomialHeapNode previousX = null;
    BinomialHeapNode minNode = this.head.getMinNode();

    // before returning minNode.key, must maintain integrity of binomial heap properties
    while (x.key != minNode.key) {
      previousX = x;
      x = x.rightSibling;
    }

    if (previousX == null) {
      this.head = x.rightSibling;
    }
    else {
      previousX.rightSibling = x.rightSibling;
    }

    x = x.leftmostChild;
    BinomialHeapNode y = x;

    while (x != null) {
      x.parent = null;
      x = x.rightSibling;
    }

    if (this.head == null && y == null) {
      this.size = 0;
    }
    else {
      if (this.head == null && y != null) {
        this.head = y.reverse(null);
        size = this.head.getSize();
      }
      else {
        if (this.head != null && y == null) {
          this.size = this.head.getSize();
        }
        else {  // i.e., if (this.head != null && y != null)
          this.union(y.reverse(null));
          this.size = this.head.getSize();
        }
      }
    }

    this.globalMin = this.head.getMinNode();

    return minNode.key;
  }

  public int getGlobalMinimum() {
    if (this.globalMin == null) {
      return Integer.MAX_VALUE;
    }
    else {
      return this.globalMin.key;
    }
  }

  @Override
  public String toString() {
    if (this.head == null) {
      return "Binomial Heap empty! No nodes to print.";
    }
    else {
      return this.head.toString();
    }
  }

  public void uglyPrint(BinomialHeapNode n) {
    while (n != null) {

      // copying:
      System.out.println("Root: " + n.key);
      BinomialHeapNode rootCopy = new BinomialHeapNode(n.key);
      rootCopy.leftmostChild = n.leftmostChild;
      rootCopy.degree = n.degree;

      // printing:
      BinomialHeapNode child = n.leftmostChild;
      while (child != null) {
        System.out.println("\t\t\t" + child.key);

        BinomialHeapNode grandchild = child.leftmostChild;
        while(grandchild != null) {
          System.out.println("\t\t\t\t" + grandchild.key);

          BinomialHeapNode greatGrandChild = grandchild.leftmostChild;
          while (greatGrandChild != null) {
            System.out.println("\t\t\t\t\t" + greatGrandChild.key);

            BinomialHeapNode greatGreatGrandChild = greatGrandChild.leftmostChild;
            while (greatGreatGrandChild != null) {
              System.out.println("\t\t\t\t\t\t" + greatGreatGrandChild.key);
              greatGreatGrandChild = greatGreatGrandChild.rightSibling;
            }

            greatGrandChild = greatGrandChild.rightSibling;
          }

          grandchild = grandchild.rightSibling;
        }

        child = child.rightSibling;
      }

      System.out.println("\n");
      n = n.rightSibling;
    }
  }
}