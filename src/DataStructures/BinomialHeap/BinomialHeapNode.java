package DataStructures.BinomialHeap;

public class BinomialHeapNode {
  public BinomialHeapNode parent;
  public int key;
  public int degree;
  public BinomialHeapNode leftmostChild;
  public BinomialHeapNode rightSibling;

  public BinomialHeapNode(int key) {
    this.key = key;
    this.degree = 0;
    this.parent = null;
    this.leftmostChild = null;
    this.rightSibling = null;
  }

  public BinomialHeapNode lookup(int lookupKey) {
    BinomialHeapNode temp = this;
    BinomialHeapNode returnNode = null;

    while (temp != null) {
      if (temp.key == lookupKey) {  // i.e., found the node with desired key
        returnNode = temp;
        break;
      }

      if (temp.leftmostChild == null)  // i.e., no more children; time to go through siblings
        temp = temp.rightSibling;
      else {  // i.e., more children to search
        returnNode = temp.leftmostChild.lookup(lookupKey);
        if (returnNode == null)
          temp = temp.rightSibling;
        else
          break;
      }
    }
    return returnNode;
  }

  public int getSize() {
    // calculating child size:
    int childSize;
    if (this.leftmostChild == null) {
      childSize = 0;
    }
    else {
      childSize = this.leftmostChild.getSize();
    }

    // calculating sibling size:
    int siblingSize;
    if (this.rightSibling == null) {
      siblingSize = 0;
    }
    else {
      siblingSize = this.rightSibling.getSize();
    }

    return 1 + childSize + siblingSize;  // add +1 to account for this node
  }

  public BinomialHeapNode getMinNode() {
    BinomialHeapNode x = this;
    BinomialHeapNode smallest = this;
    int min = x.key;

    while (x != null) {
      if (x.key < min) {
        smallest = x;
        min = x.key;
      }

      x = x.rightSibling;
    }

    return smallest;
  }

  public BinomialHeapNode reverse(BinomialHeapNode sibling) {
    BinomialHeapNode r;  // compiler appeasement

    if (this.rightSibling != null) {
      r = this.rightSibling.reverse(this);
    }
    else {  // found rightmost sibling
      r = this;
    }

    this.rightSibling = sibling;

    return r;
  }

  @Override
  public String toString() {
    return this.toStringHelper(this);
  }

  private String toStringHelper(BinomialHeapNode node) {
    if (node != null) {
      return this.toStringHelper(node.leftmostChild) + node.key + " "
              + this.toStringHelper(node.rightSibling);
    }
    else {
      return "";
    }
  }
}