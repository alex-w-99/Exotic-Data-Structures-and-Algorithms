package Algorithms.PushRelabel;

import java.util.ArrayList;
import java.util.Objects;

public class PRVertex {
  public int data;
  public double excessFlow;
  public int height;
  public ArrayList<PREdge> E;

  public PRVertex(int data) {
    this.data = data;
    this.excessFlow = 0;
    this.height = 0;
    this.E = new ArrayList<>();
  }

  /**
   * Adds a new PREdge to PRVertex's this.E attribute, a list of PREdges leaving this PRVertex.
   *
   * @param B PRVertex destination of this Edge.
   * @param flow int flow of this Edge.
   * @param capacity int capacity of this Edge.
   * @param isReal boolean if Edge is "real".
   */
  public void addEdge(PRVertex B, double flow, double capacity, boolean isReal) {
    this.E.add(new PREdge(this, B, flow, capacity, isReal));
  }

  @Override
  public String toString() {
    return "V" + this.data;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {  // if comparing a Vertex object to itself
      return true;  // ...then return true
    }
    else if (other == null || getClass() != other.getClass()) {  // if other is null/not Vertex
      return false;  // ...then return false
    }
    else {   // i.e., if comparing two distinct Vertex objects
      return Objects.equals(this.data, ((PRVertex) other).data);
    }
  }
}
