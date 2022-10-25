package DataStructures.FibonacciHeap;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FibonacciHeap {
  public FibonacciHeapNode globalMin;
  public int size;

  public FibonacciHeap() {
    this.globalMin = null;
    this.size = 0;
  }

  public boolean isEmpty() {
    return this.globalMin == null;
  }

  public void emptyHeap() {
    this.globalMin = null;
    this.size = 0;
  }

  public int minimum() {  // just peeks the minimum (whereas extractMin removes the minimum)
    if (this.isEmpty()) {  // i.e., if (this.globalMin == null)
      return Integer.MAX_VALUE;
    }
    else {  // i.e., Fibonacci heap is non-empty
      return this.globalMin.key;
    }
  }

  public void insert(int key) {
    // fib heap lazily defers consolidation/cleanup until next extractMin
    FibonacciHeapNode newNode = new FibonacciHeapNode(key);

    // simply insert new node as a tree of size 1 at the end of linked list of tree roots
    if (this.isEmpty()) {  // i.e., if (this.globalMin == null)
      this.globalMin = newNode;
      newNode.leftSibling = newNode;
      newNode.rightSibling = newNode;
    }
    else {  // i.e., Fibonacci heap is non-empty
      newNode.rightSibling = this.globalMin;
      newNode.leftSibling = this.globalMin.leftSibling;
      this.globalMin.leftSibling.rightSibling = newNode;  // node preceding newNode in tree roots
      this.globalMin.leftSibling = newNode;  // newNode is to left of globalMin

      // must determine if node we are inserting contains the new smallest key
      if (newNode.key < this.globalMin.key) {
        this.globalMin = newNode;
      }
    }
    this.size++;
  }

  public int extractMin() {  // requires heap consolidation if necessary; O(n log n) (amortized)
    if (this.isEmpty()) {  // nothing to do if Fibonacci heap empty
      return Integer.MAX_VALUE;
    }

    FibonacciHeapNode oldGlobalMin = this.globalMin;

    // before returning oldGlobalMin.key, must promote its children to root list
    int totalChildren = oldGlobalMin.degree;
    FibonacciHeapNode currentChild = oldGlobalMin.child;
    FibonacciHeapNode nextChild;
    while (totalChildren > 0) {  // for each child of oldGlobalMin...
      nextChild = currentChild.rightSibling;

      // obviating currentChild from its linkage to its siblings
      currentChild.leftSibling.rightSibling = currentChild.rightSibling;
      currentChild.rightSibling.leftSibling = currentChild.leftSibling;

      // placing currentChild into root list, updating pointers of adjacent root nodes, etc.
      currentChild.rightSibling = this.globalMin;
      currentChild.leftSibling = this.globalMin.leftSibling;
      this.globalMin.leftSibling.rightSibling = currentChild;  // node preceding newNode in tree roots
      this.globalMin.leftSibling = currentChild;  // newNode is to left of globalMin
      currentChild.parent = null;  // nodes in root list do not have a parent

      // preparing for next loop
      totalChildren--;  // one less child to worry about
      currentChild = nextChild;  // moving onto next child
    }

    // moving oldGlobalMin from root list by updating pointers of adjacent root nodes
    oldGlobalMin.leftSibling.rightSibling = oldGlobalMin.rightSibling;
    oldGlobalMin.rightSibling.leftSibling = oldGlobalMin.leftSibling;

    if (oldGlobalMin == oldGlobalMin.rightSibling) {  // == oldGlobalMin.leftSibling
      this.globalMin = null;  // i.e., heap now empty
    }
    else {  // i.e., heap not empty after removing oldGlobalMin
      this.globalMin = oldGlobalMin.rightSibling;  // a placeholder before union() called below
      this.union();  // key feature of Fib heaps is deferring consolidation until after extractMin()
    }

    this.size--;
    return oldGlobalMin.key;  // returning key of old global minimum node, now obviated from heap
  }

  /**
   * Goes through the root list of the Fibonacci heap, repeatedly combining trees of the same degree
   *      (making the root with the higher key a child of the root with the lower key) until no
   *      two trees have the same degree. This compresses the Fibonacci heap to its minimum possible
   *      size (as far as root list), and keeps it as efficient as possible.
   */
  public void union() {

    // 1. finding total number of root nodes
    int totalRoots = 0;
    FibonacciHeapNode nodeX = this.globalMin;
    if (nodeX != null) {  // if there are any roots in the first place
      totalRoots++;
      nodeX = nodeX.rightSibling;

      while (nodeX != this.globalMin) {
        totalRoots++;
        nodeX = nodeX.rightSibling;
      }
    }

    // 2. recall that, by the Shape Theorem, every node will have a degree less than or equal to
    // log_φ(n), with Golden Ratio φ = (1 + sqrt(5))/2. therefore, we can create a list called
    // rootsByDegreesList that will contain entry for a node of each degree (up to the max degree).
    // rootsByDegreesList will initially contain only null values, and be filled out as we go
    // through the current roots list and merge nodes of the same degree where necessary (step 3).
    // finally, after step 3, we can just model the Fibonacci heap after rootsByDegreesList
    double goldenRatio = (1 + Math.sqrt(5)) / 2;
    double inverseLogOfGoldenRatio = 1 / Math.log(goldenRatio);
    int maxRootDegree = ((int) Math.floor(Math.log(this.size) * inverseLogOfGoldenRatio)) + 1;

    List<FibonacciHeapNode> rootsByDegreesList = new ArrayList<>(maxRootDegree);
    for (int i = 0; i < maxRootDegree; i++) {
      rootsByDegreesList.add(null);  // initializing ArrayList
    }

    // 3. for each root node, we must ensure that it is the only node with its given degree
    while (totalRoots > 0) {

      // 3.1 get the degree of the root
      int nodeDegree = nodeX.degree;
      FibonacciHeapNode nextNode = nodeX.rightSibling;

      // 3.2 see if there exists another root of the same degree, merging where needed
      for (; ; ) {  // looking for root with same degree as nodeX
        FibonacciHeapNode nodeY = rootsByDegreesList.get(nodeDegree);
        if (nodeY == null) {  // i.e., no other root with same degree as nodeX
          break;
        }

        // if two nodes of same degree, we should call merge() on them; however, for simplicity's
        // sake, we should ensure nodeX will become the parent of nodeY (please see merge function)
        if (nodeX.key > nodeY.key) {  // ensuring nodeX will become nodeY's parent
          FibonacciHeapNode tmpNode = nodeX;
          nodeX = nodeY;
          nodeY = tmpNode;
        }
        this.merge(nodeX, nodeY);  // nodeY becomes a child of nodeX

        // moving to next degree
        rootsByDegreesList.set(nodeDegree, null);
        nodeDegree++;
      }

      // 3.3 now that nodeX is the only node of its degree, we fill it in inside the degrees list
      rootsByDegreesList.set(nodeDegree, nodeX);

      // 3.4 moving to next node
      nodeX = nextNode;
      totalRoots--;
    }

    // 4. we now model the Fibonacci heap after rootsByDegreesList
    this.globalMin = null;  // want to start fresh before mirroring rootsByDegreesList
    for (int j = 0; j < maxRootDegree; j++) {  // for each possible degree...
      FibonacciHeapNode nodeJ = rootsByDegreesList.get(j);
      if (nodeJ == null) {  // i.e., no root of degree j in the roots list
        continue;  // move on to next possible degree j
      }

      // otherwise, we have a root of degree j to insert into root list
      if (this.globalMin == null) {  // i.e., Fibonacci heap currently has an empty root list
        this.globalMin = nodeJ;
      } else {  // i.e., Fibonacci heap has a non-empty root list

        // even though root list was "emptied", we still need to fix node pointers;
        // first, obviate nodeJ from the old root list pointer structure
        nodeJ.leftSibling.rightSibling = nodeJ.rightSibling;
        nodeJ.rightSibling.leftSibling = nodeJ.leftSibling;

        // second, add nodeJ to the current root list
        nodeJ.leftSibling = this.globalMin;
        nodeJ.rightSibling = this.globalMin.rightSibling;
        this.globalMin.rightSibling = nodeJ;
        nodeJ.rightSibling.leftSibling = nodeJ;
        nodeJ.parent = null;  // nodes in root list do not have a parent

        // updating this.globalMin if necessary
        if (nodeJ.key < this.globalMin.key) {
          this.globalMin = nodeJ;
        }
      }
    }
  }

  /**
   /**
   * A helper function for union(); obviates nodeY from root list by making it a child of nodeX;
   *     NOTE: this method assumes that nodeY.key > nodeX.key!
   *
   * @param nodeX node in root list with a relatively smaller key, to become nodeY's parent.
   * @param nodeY node in root list with a relatively larger key, to become nodeX's child.
   * @throws IllegalArgumentException thrown if nodeX.key > nodeY.key.
   */
  private void merge(FibonacciHeapNode nodeX, FibonacciHeapNode nodeY)
          throws IllegalArgumentException {
    if (nodeX.key > nodeY.key) {
      throw new IllegalArgumentException("\"merge()\" requires nodeX.key <= nodeY.key!");
    }

    // 1. removing nodeY from root list (modifying pointers of adjacent nodes)
    nodeY.leftSibling.rightSibling = nodeY.rightSibling;
    nodeY.rightSibling.leftSibling = nodeY.leftSibling;

    // 2. making nodeY a child of nodeX
    nodeY.parent = nodeX;
    if (nodeX.child == null) {  // easier case
      nodeX.child = nodeY;
      nodeY.leftSibling = nodeY;
      nodeY.rightSibling = nodeY;
    }
    else {  // i.e., if (nodeX.child != null)
      nodeY.leftSibling = nodeX.child;
      nodeY.rightSibling = nodeX.child.rightSibling;
      nodeX.child.rightSibling = nodeY;
      nodeY.rightSibling.leftSibling = nodeY;
    }

    // 3. maintaining other properties...
    nodeY.lostChild = false;  // this resets nodeY's lostChild attribute to true
    nodeX.degree++;  // nodeX just gained another child
  }

  public void delete(int key) {
    if (this.globalMin != null && this.globalMin.lookup(key) != null) {  // if key exists
      this.decreaseKey(key, Integer.MIN_VALUE);  // converting this node to have the smallest key

      // removing node with minimum value on Fibonacci heap;
      // NOTE: extractMin will take care of updating this.globalMin (specifically union does this)
      this.extractMin();
    }
  }

  public boolean decreaseKey(int oldKey, int newKey) {
    // 1. ensure the newKey is actually less than the old key (i.e., it will be decreased)
    if (newKey > oldKey) {
      return false;  // cannot decrease key if newKey is higher than oldKey
    }

    // 2. find the node with the oldKey value; return false if no such node exists in the heap
    FibonacciHeapNode nodeX = this.globalMin.lookup(oldKey);
    if (nodeX == null) {  // i.e., no such node exists with a key equal to oldKey
      return false;
    }

    // 3. update nodeX's key to the newKey value, and correct any min-heap violations
    nodeX.key = newKey;  // updating key value
    FibonacciHeapNode parent = nodeX.parent;
    if (parent != null && nodeX.key < parent.key) {  // min-heap violation

      // 3.1 removing nodeX from parent's children "list"
      nodeX.leftSibling.rightSibling = nodeX.rightSibling;
      nodeX.rightSibling.leftSibling = nodeX.leftSibling;
      parent.degree--;
      if (parent.degree == 0) {
        parent.child = null;
      }
      else if (parent.child == nodeX) {
        parent.child = nodeX.rightSibling;
      }

      // 3.2 adding nodeX to root list
      nodeX.rightSibling = this.globalMin;
      nodeX.leftSibling = this.globalMin.leftSibling;
      this.globalMin.leftSibling.rightSibling = nodeX;  // node preceding nodeX in tree roots
      this.globalMin.leftSibling = nodeX;  // nodeX is to left of globalMin
      nodeX.parent = null;  // nodes in root list do not have a parent
      nodeX.lostChild = false;  // being promoted to root list means resetting this field to false

      // 3.3 now that parent has lost a child, must account for bereaved child rules
      this.bereavedParent(parent);
    }

    // 4. update this.globalMin node if necessary
    if (nodeX.key < this.globalMin.key) {  // i.e., if newKey is the lowest key in the heap
      this.globalMin = nodeX;
    }
    return true;
  }

  /**
   * A helper function to decreaseKey; this function is called when a parent node loses a child;
   *     to maintain the integrity of the Fibonacci heap, we must ensure that parents who lose their
   *     first child are "marked", and parents who lose their second child have their "mark" reset
   *     to false and are promoted to the root list. This helps us keep the size of each Fibonacci
   *     tree exponential, so that the total amount of roots on the heap is ultimately logarithmic.
   *
   * @param parent FibonacciHeapNode representing the node which just lost its child.
   */
  private void bereavedParent(FibonacciHeapNode parent) {
    FibonacciHeapNode grandparent = parent.parent;
    if (grandparent != null) {  // i.e., if parent not in root list
      if (!parent.lostChild) {
        parent.lostChild = true;
      }
      else {  // i.e., if (parent.lostChild)
        // since the parent has previously lost a child, it must now be promoted to root list

        // 1. removing parent from grandparent's children "list"
        parent.leftSibling.rightSibling = parent.rightSibling;
        parent.rightSibling.leftSibling = parent.leftSibling;
        grandparent.degree--;
        if (grandparent.degree == 0) {
          grandparent.child = null;
        }
        else if (grandparent.child == parent) {
          grandparent.child = parent.rightSibling;
        }

        // 2. adding parent to root list
        parent.rightSibling = this.globalMin;
        parent.leftSibling = this.globalMin.leftSibling;
        this.globalMin.leftSibling.rightSibling = parent;  // node preceding parent in tree roots
        this.globalMin.leftSibling = parent;  // parent is to left of globalMin
        parent.parent = null;  // nodes in root list do not have a parent
        parent.lostChild = false;  // being promoted to root list means resetting to false

        // 3. now that grandparent has lost parent, must account for bereaved child rules
        this.bereavedParent(grandparent);
      }
    }
  }

  @Override
  public String toString() {
    if (this.globalMin == null) {
      return "Fibonacci heap empty! No nodes to print.";
    }

    StringBuffer sb = new StringBuffer();
    sb.append("{");

    Stack<FibonacciHeapNode> nodeStack = new Stack<FibonacciHeapNode>();
    nodeStack.push(this.globalMin);
    while (!nodeStack.empty()) {  // i.e., while nodeStack non-empty
      FibonacciHeapNode nodeX = nodeStack.pop();
      sb.append(nodeX.key);
      sb.append(", ");

      if (nodeX.child != null) {
        nodeStack.push(nodeX.child);
      }

      FibonacciHeapNode nextNode = nodeX.rightSibling;
      while (nextNode != nodeX) {  // avoiding infinite loop
        sb.append(nextNode.key);
        sb.append(", ");

        if (nextNode.child != null) {
          nodeStack.push(nextNode.child);
        }

        nextNode = nextNode.rightSibling;
      }
    }

    // removing last ", " which is unnecessary
    sb.setLength(Math.max(sb.length() - 2, 0));  // avoiding setting a negative length
    sb.append("}");

    return sb.toString();
  }
}
