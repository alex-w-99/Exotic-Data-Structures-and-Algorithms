package DataStructures.SkipList;

import java.util.Random;

public class SkipList {
  public static final int MAX_LEVELS = 50;  // max number of skip list levels/layers
  public static final int NEG_INFTY = Integer.MIN_VALUE;
  public static final int POS_INFTY = Integer.MAX_VALUE;
  public Random rand = new Random();
  public int levels;
  public SkipListNode head;
  public SkipListNode nil;

  public SkipList() {
    this.head = new SkipListNode(NEG_INFTY);
    this.nil = new SkipListNode(POS_INFTY);

    this.head.next = this.nil;  // head -> nil
    this.nil.previous = this.head;  // head <- nil

    this.levels = 0;  // initially have empty list, hence effectively 0 levels
  }

  public boolean Search(int key) {
    return key == this.Lookup(key).key;
  }

  public SkipListNode Lookup(int key) {
    SkipListNode n = this.head;

    while (n.down != null) {  // for each "level"/"row"
      n = n.down;

      while (key >= n.next.key) {  // for each value in a given "level"/"row"
        n = n.next;
      }
    }

    return n;
  }

  public SkipListNode Delete(int key) {
    SkipListNode n = this.Lookup(key);

    if (n.key != key) {  // i.e., if key does not exist in the first place
      return null;
    }

    this.obviateNodeReferences(n);

    while (n != null) {
      this.obviateNodeReferences(n);

      if (n.up != null) {
        n = n.up;
      }
      else {
        break;
      }
    }
    return n;
  }

  private void obviateNodeReferences(SkipListNode n) {
    SkipListNode next = n.next;
    SkipListNode prev = n.previous;

    prev.next = next;
    next.previous = prev;
  }

  public SkipListNode Insert(int key) {
    SkipListNode position = this.Lookup(key);
    SkipListNode x = null;  // compiler appeasement

    if (position.key == key) {  // i.e., if this key is already in the SkipList
      return position;  // then do not insert it again, just return the existing node
    }

    int depth = -1;

    boolean randBool = true;  // "coin flip"; initialized to true to ensure it runs at least once
    while (randBool && this.levels <= MAX_LEVELS) {
      depth++;
      this.deepen(depth);

      x = position;

      while (position.up == null) {  // traversing to node with "access" to above level
        position = position.previous;
      }

      position = position.up;

      x = this.insertAfterAbove(position, x, key);

      randBool = rand.nextBoolean();  // akin to flipping a coin
    }

    return x;
  }

  private void deepen(int depth) {
    if (depth >= this.levels) {
      this.levels++;
      newEmptyLevel();
    }
  }

  private void newEmptyLevel() {
    SkipListNode newHead = new SkipListNode(NEG_INFTY);
    SkipListNode newNil = new SkipListNode(POS_INFTY);

    newHead.next = newNil;
    newHead.down = this.head;
    newNil.previous = newHead;
    newNil.down = this.nil;

    this.head.up = newHead;
    this.nil.up = newNil;

    this.head = newHead;
    this.nil = newNil;
  }

  private SkipListNode insertAfterAbove(SkipListNode position, SkipListNode x, int key) {
    SkipListNode n = new SkipListNode(key);  // create node to insert
    SkipListNode prior = position.down.down;

    // setting previous and next references:
    n.next = x.next;  // setting n itself
    n.previous = x;
    x.next.previous = n;  // setting nodes left and right of n
    x.next = n;

    // setting up and down references:
    if (prior != null) {
      while (true) {
        if (prior.next.key != key) {
          prior = prior.next;
        }
        else {  // break once found
          break;
        }
      }
      n.down = prior.next;
      prior.next.up = n;
    }
    if (position != null) {
      if (position.next.key == key) {
        n.up = position.next;
      }
    }

    return n;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    SkipListNode start = this.head;
    SkipListNode x = start;
    int lvl = this.levels;

    while (x != null) {
      sb.append("\nLevel: " + lvl + "\n\t");

      while (start != null) {
        if (start.key != NEG_INFTY && start.key != POS_INFTY) {
          sb.append(start.key);

          if (start.next != null) {
            sb.append(" --- ");
          }
        }
        else if (start.key == NEG_INFTY) {
          sb.append("HEAD --- ");
        }
        else {
          sb.append("NIL");
        }


        start = start.next;
      }

      x = x.down;
      start = x;
      lvl--;
    }

    return sb.toString();
  }
}