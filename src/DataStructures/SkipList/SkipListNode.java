package DataStructures.SkipList;

public class SkipListNode {
  public int key;
  public SkipListNode previous;
  public SkipListNode next;
  public SkipListNode up;
  public SkipListNode down;

  // upon instantiation, all SkipList nodes contain a given key and null pointers in all directions.
  public SkipListNode(int key) {
    this.key = key;
    this.previous = null;
    this.next = null;
    this.up = null;
    this.down = null;
  }
}
