package DataStructures.RedBlackTree;

public class RBTree {
  public RBNode root;
  public RBNode nil;  // sentinel node

  public RBTree() {
    RBNode sentinel = new RBNode(-999);
    sentinel.setColor(RBColor.BLACK);
    this.nil = sentinel;

    this.root = sentinel;
    this.root.setLeftChild(this.nil);
    this.root.setRightChild(this.nil);
    this.root.setParent(this.nil);

    sentinel.setLeftChild(this.root);
    sentinel.setRightChild(this.root);
  }

  public RBNode getRoot() {
    return this.root;
  }

  public int getHeight() {
    return this.calculateHeight(this.root);
  }

  // calculates height of entire tree
  public int calculateHeight(RBNode x) {
    if (x == this.nil) {
      return 0;
    }
    else {
      int leftHeight = this.calculateHeight(x.getLeftChild());
      int rightHeight = this.calculateHeight(x.getRightChild());

      if (leftHeight > rightHeight) {
        return 1 + leftHeight;
      }
      else {  // i.e., leftHeight <= rightHeight
        return 1 + rightHeight;
      }
    }
  }

  public void Sort(RBNode x) {
    if (x != this.nil) {
      Sort(x.getLeftChild());
      System.out.println("Key: " + x.getKey() + "; Color: " + x.getColor());
      Sort(x.getRightChild());
    }
  }

  public RBNode Search(RBNode x, int key) {
    while (x != this.nil && key != x.getKey()) {
      if (key < x.getKey()) {
        x = x.getLeftChild();
      }
      else {
        x = x.getRightChild();
      }
    }
    return x;
  }

  public RBNode Min(RBNode x) {
    while (x.getLeftChild() != this.nil) {
      x = x.getLeftChild();
    }
    return x;
  }

  public RBNode Max(RBNode x) {
    while (x.getRightChild() != this.nil) {
      x = x.getRightChild();
    }
    return x;
  }

  public RBNode Successor(RBNode x) {
    if (x.getRightChild() != this.nil) {  // i.e., if x has a right child
      return this.Min(x.getRightChild());
    }
    else {
      RBNode y = x.getParent();
      while (y != this.nil && x == y.getRightChild()) {
        x = y;
        y = y.getParent();
      }
      return y;
    }
  }

  public RBNode Predecessor(RBNode x) {
    if (x.getLeftChild() != this.nil) {  // i.e., if x has a left child
      return this.Max(x.getLeftChild());
    }
    else {
      RBNode y = x.getParent();
      while (y != this.nil && x == y.getLeftChild()) {
        x = y;
        y = y.getParent();
      }
      return y;
    }
  }

  public void LeftRotation(RBNode x) {
    RBNode y = x.getRightChild();

    x.setRightChild(y.getLeftChild());  // x's right child set to y's left child
    if (y.getLeftChild() != this.nil) {
      y.getLeftChild().setParent(x);  // y's left child's parent set to x
    }

    y.setParent(x.getParent());  // y's parent set to x's parent
    if (x.getParent() == this.nil) {
      this.root = y;
    }
    else if (x == x.getParent().getLeftChild()) {
      x.getParent().setLeftChild(y);  // x's parent's left child set to y
    }
    else {
      x.getParent().setRightChild(y);  // x's parent's right child set to y
    }

    y.setLeftChild(x);  // y's left child set to x
    x.setParent(y);  // x's parent set to y
  }

  public void RightRotation(RBNode y) {
    RBNode x = y.getLeftChild();

    y.setLeftChild(x.getRightChild());  // y's left child set to x's right child
    if (x.getRightChild() != this.nil) {
      x.getRightChild().setParent(y);  // x's right child's parent set to y
    }


    x.setParent(y.getParent());  // x's parent set to y's parent
    if (y.getParent() == this.nil) {
      this.root = x;
    }
    else if (y == y.getParent().getLeftChild()) {
      y.getParent().setLeftChild(x);  // y's parent's left child set to x
    }
    else {
      y.getParent().setRightChild(x);  // y's parent's right child set to x
    }

    x.setRightChild(y);  // x's right child set to y
    y.setParent(x);  // y's parent set to x
  }

  public void Insert(RBNode z) {
    // first adding node z as a leaf in RBTree as if it were a regular BST
    RBNode y = this.nil;
    RBNode x = this.root;

    while (x != this.nil) {  // navigating all the way down the tree
      y = x;

      if (z.getKey() < x.getKey()) {
        x = x.getLeftChild();
      }
      else {  // i.e., z.getKey() >= x.getKey()
        x = x.getRightChild();
      }
    }  // end while loop

    z.setParent(y);  // z's parent set to y
    if (y == this.nil) {
      this.root = z;
    }
    else if (z.getKey() < y.getKey()) {
      y.setLeftChild(z);
    }
    else {  // (z.getKey() >= y.getKey()
      y.setRightChild(z);
    }

    // modifying node z
    z.setColor(RBColor.RED);
    z.setLeftChild(this.nil);
    z.setRightChild(this.nil);

    // maintaining Red-Black Properties
    this.RBInsertFixup(z);
  }

  public void RBInsertFixup(RBNode z) {
    while (z.getParent().getColor() == RBColor.RED) {
      if (z.getParent() == z.getParent().getParent().getLeftChild()) {
        RBNode y = z.getParent().getParent().getRightChild();

        if (y.getColor() == RBColor.RED) {  // CASE 1
          z.getParent().setColor(RBColor.BLACK);
          y.setColor(RBColor.BLACK);
          z.getParent().getParent().setColor(RBColor.RED);
          z = z.getParent().getParent();
        }
        else {
          if (z == z.getParent().getRightChild()) {  // CASE 2
            z = z.getParent();
            this.LeftRotation(z);
          }
          z.getParent().setColor(RBColor.BLACK);  // CASE 3
          z.getParent().getParent().setColor(RBColor.RED);  // CASE 3
          this.RightRotation(z.getParent().getParent());  // CASE 3
        }
      }
      else {  // i.e., z.getParent() == z.getParent().getParent().getRightChild())
        RBNode y = z.getParent().getParent().getLeftChild();

        if (y.getColor() == RBColor.RED) {  // CASE 1
          z.getParent().setColor(RBColor.BLACK);
          y.setColor(RBColor.BLACK);
          z.getParent().getParent().setColor(RBColor.RED);
          z = z.getParent().getParent();
        }
        else {
          if (z == z.getParent().getLeftChild()) {  // CASE 2
            z = z.getParent();
            this.RightRotation(z);
          }
          z.getParent().setColor(RBColor.BLACK);  // CASE 3
          z.getParent().getParent().setColor(RBColor.RED);  // CASE 3
          this.LeftRotation(z.getParent().getParent());  // CASE 3
        }
      }
    }

    this.root.setColor(RBColor.BLACK);  // setting root to BLACK
  }

  public void Delete(RBNode z) {
    RBNode x;
    RBNode y = z;
    RBColor yOriginalColor = y.getColor();

    if (z.getLeftChild() == this.nil) {
      x = z.getRightChild();
      this.RBTransplant(z, z.getRightChild());
    }
    else if (z.getRightChild() == this.nil) {
      x = z.getLeftChild();
      this.RBTransplant(z, z.getLeftChild());
    }
    else {
      y = this.Min(z.getRightChild());
      yOriginalColor = y.getColor();
      x = y.getRightChild();

      if (y.getParent() == z) {
        x.setParent(y);
      }
      else {
        this.RBTransplant(y, y.getRightChild());
        y.setRightChild(z.getRightChild());
        y.getRightChild().setParent(y);
      }

      this.RBTransplant(z,y);
      y.setLeftChild(z.getLeftChild());
      y.getLeftChild().setParent(y);
      y.setColor(z.getColor());
    }

    // maintaining Red-Black Properties
    if (yOriginalColor == RBColor.BLACK) {
      this.RBDeleteFixup(x);
    }
  }

  public void RBTransplant(RBNode u, RBNode v) {
    if (u.getParent() == this.nil) {  // if u is root of tree...
      this.root = v;  // set v to be root of tree
    }
    else if (u == u.getParent().getLeftChild()) {  // if u is its parent's left child...
      u.getParent().setLeftChild(v);  // set v to be that parent's left child
    }
    else {  // if u is its parent's right child...
      u.getParent().setRightChild(v);  // set v to be that parent's right child
    }

    v.setParent(u.getParent());  // setting v's parent to be u's parent
  }

  public void RBDeleteFixup(RBNode x) {
    while (x != this.root && x.getColor() == RBColor.BLACK) {
      if (x == x.getParent().getLeftChild()) {  // i.e., x is its parent's left child
        RBNode w = x.getParent().getRightChild();
        if (w.getColor() == RBColor.RED) {
          w.setColor(RBColor.BLACK);  // CASE 1
          x.getParent().setColor(RBColor.RED);  // CASE 1
          this.LeftRotation(x.getParent());  // CASE 1
          w = x.getParent().getRightChild();  // CASE 1
        }
        if (w.getLeftChild().getColor() == RBColor.BLACK
                && w.getRightChild().getColor() == RBColor.BLACK) {
          w.setColor(RBColor.RED);  // CASE 2
          x = x.getParent();  // CASE 2
        } else {  // CASE 3 or CASE 4
          if (w.getRightChild().getColor() == RBColor.BLACK) {
            w.getLeftChild().setColor(RBColor.BLACK);  // CASE 3
            w.setColor(RBColor.RED);  // CASE 3
            this.RightRotation(w);  // CASE 3
            w = x.getParent().getRightChild();  // CASE 3
          }
          w.setColor(x.getParent().getColor());  // CASE 4
          x.getParent().setColor(RBColor.BLACK);  // CASE 4
          w.getRightChild().setColor(RBColor.BLACK);  // CASE 4
          this.LeftRotation(x.getParent());  // CASE 4
          x = this.root;
        }
      }

      else {  // i.e., x is its parent's right child
        RBNode w = x.getParent().getLeftChild();
        if (w.getColor() == RBColor.RED) {
          w.setColor(RBColor.BLACK);  // CASE 1
          x.getParent().setColor(RBColor.RED);  // CASE 1
          this.RightRotation(x.getParent());  // CASE 1
          w = x.getParent().getLeftChild();  // CASE 1
        }
        if (w.getRightChild().getColor() == RBColor.BLACK
                && w.getLeftChild().getColor() == RBColor.BLACK) {
          w.setColor(RBColor.RED);  // CASE 2
          x = x.getParent();  // CASE 2
        } else {  // CASE 3 or CASE 4
          if (w.getLeftChild().getColor() == RBColor.BLACK) {
            w.getRightChild().setColor(RBColor.BLACK);  // CASE 3
            w.setColor(RBColor.RED);  // CASE 3
            this.LeftRotation(w);  // CASE 3
            w = x.getParent().getLeftChild();  // CASE 3
          }
          w.setColor(x.getParent().getColor());  // CASE 4
          x.getParent().setColor(RBColor.BLACK);  // CASE 4
          w.getLeftChild().setColor(RBColor.BLACK);  // CASE 4
          this.RightRotation(x.getParent());  // CASE 4
          x = this.root;
        }
      }
    }

    x.setColor(RBColor.BLACK);
  }
}