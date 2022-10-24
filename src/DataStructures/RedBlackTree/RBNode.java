package DataStructures.RedBlackTree;

public class RBNode {
  public int key;
  public RBColor color;
  public RBNode parent;
  public RBNode leftChild;
  public RBNode rightChild;

  public RBNode() {
    this.key = 0;
    this.color = RBColor.RED;  // default RBColor for new RBTree node
    this.parent = null;
    this.leftChild = null;
    this.rightChild = null;
  }

  public RBNode(int key) {
    this.key = key;
    this.color = RBColor.RED;  // default RBColor for new RBTree node
    this.parent = null;
    this.leftChild = null;
    this.rightChild = null;
  }

  public int getKey() {
    return this.key;
  }

  public RBColor getColor() {
    return this.color;
  }

  public RBNode getParent() {
    return this.parent;
  }

  public RBNode getLeftChild() {
    return this.leftChild;
  }

  public RBNode getRightChild() {
    return this.rightChild;
  }

  public void setKey(int newKey) {
    this.key = newKey;
  }

  public void setColor(RBColor newColor) {
    this.color = newColor;
  }

  public void setParent(RBNode parent) {
    this.parent = parent;
  }

  public void setLeftChild(RBNode leftChild) {
    this.leftChild = leftChild;
  }

  public void setRightChild(RBNode rightChild) {
    this.rightChild = rightChild;
  }
}