package DataStructures.FibonacciHeap;

public class FibonacciHeapNode {
  public FibonacciHeapNode parent;
  public int key;
  public int degree;  // number of children
  public FibonacciHeapNode leftSibling;
  public FibonacciHeapNode rightSibling;
  public FibonacciHeapNode child;  // a pointer a child of this node
  public boolean lostChild;  // true if lost one of its children

  public FibonacciHeapNode(int key) {
    this.key = key;
    this.degree = 0;
    this.parent = null;
    this.leftSibling = null;
    this.rightSibling = null;
    this.child = null;
    this.lostChild = false;
  }

  public FibonacciHeapNode lookup(int lookupKey) {
    FibonacciHeapNode temp = this;
    FibonacciHeapNode returnNode = null;

    while (temp != null) {
      if (temp.key == lookupKey) {  // i.e., found the node with desired key
        returnNode = temp;
        break;
      }

      // cut the left sibling's pointer to this node to avoid infinite loop;
      // this will be repaired later
      FibonacciHeapNode temporarilyObviatedPointer = this.leftSibling.rightSibling;
      this.leftSibling.rightSibling = null;

      if (temp.child == null)  // i.e., no more children; time to go through siblings
        temp = temp.rightSibling;
      else {  // i.e., more children to search
        returnNode = temp.child.lookup(lookupKey);
        if (returnNode == null) {
          temp = temp.rightSibling;
        }
        else {
          this.leftSibling.rightSibling = temporarilyObviatedPointer;
          break;
        }
      }
      this.leftSibling.rightSibling = temporarilyObviatedPointer;
    }
    return returnNode;
  }
}
