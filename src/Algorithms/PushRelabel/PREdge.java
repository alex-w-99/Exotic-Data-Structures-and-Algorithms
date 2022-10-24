package Algorithms.PushRelabel;

public class PREdge {
  public PRVertex A;  // edge (A,B)
  public PRVertex B;
  public double capacity;
  public double flow;
  public boolean isReal;

  public PREdge(PRVertex A, PRVertex B, double flow, double capacity, boolean isReal) {
    this.A = A;
    this.B = B;
    this.flow = flow;
    this.capacity = capacity;
    this.isReal = isReal;
  }

  @Override
  public String toString() {
    return "PREdge(" + this.A + "->" + this.B + ", flow=" + this.flow + ", capacity="
            + this.capacity + ", isReal=" + Boolean.toString(this.isReal).toUpperCase() + ")";
  }
}
