package Algorithms.PushRelabel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PRNetwork {
  public LinkedList<PRVertex> V;  // list of all vertices contained in PushRelabelNetwork
  public PRVertex s;  // source vertex
  public PRVertex t;  // sink vertex

  /**
   * Creates a new PRNetwork object; merely instantiates the this.V attribute to be a new,
   *     empty LinkedList object. The method this.processInput must be called to feed it edge info.
   */
  public PRNetwork() {
    this.V = new LinkedList<>();
  }

  /**
   * Takes in processed input instructions, creates vertices and edges.
   *
   * @param treatedInput list of doubles of form: [[1.0, 2.0, 16.0], [1.0, 4.0, 13.0], ...]
   */
  public void processInput(List<ArrayList<Double>> treatedInput) {
    // 1. get |V|:
    int absV = this.getTotalVerticesCount(treatedInput);

    // 2. getting Vertex info; creates vertices and adds them to this.V:
    this.createVertices(absV);

    // 3. getting Edge info; creates edges and adds them to respective vertices in this.V
    this.createEdges(treatedInput);
  }

  /**
   * Takes in input instructions (list of edges with weights) and returns int representing how many
   *       unique vertices the flow network has; used in this.processInput method.
   *
   * @param treatedInput list of doubles of form: [[1.0, 2.0, 16.0], [1.0, 4.0, 13.0], ...]
   * @return int representing how many (unique) vertices the flow network has.
   */
  private int getTotalVerticesCount(List<ArrayList<Double>> treatedInput) {
    List<Integer> vertices = new ArrayList<>();

    for (List<Double> edge : treatedInput) {
      int i = 0;
      for (Double num : edge) {
        if (i < 2) {
          vertices.add(num.intValue());
        }
        i++;
      }
    }
    // System.out.println("vertices = " + vertices);
    // System.out.println("vertices.max = " + Collections.max(vertices));
    return Collections.max(vertices);
  }

  /**
   * Creates the desired amount of Vertices and places each PRVertex into this.V; used in
   *         this.processInput method.
   *
   * @param totalVertices int representing amount of vertices the PushRelabelNetwork should have.
   */
  private void createVertices(int totalVertices) {
    for (int i = 1; i <= totalVertices; i++) {
      if (i == 1) {  // source s
        PRVertex source = new PRVertex(1);
        source.height = totalVertices;  // this.s.height = |V|
        this.s = source;
        this.V.add(this.s);
      }
      else if (i == totalVertices) {  // sink t
        this.t = new PRVertex(totalVertices);
        this.V.add(this.t);
      }
      else {
        this.V.add(new PRVertex(i));
      }
    }
  }

  /***
   * Takes in treated input of edge information, then creates an edge for each item in the list;
   *       used in this.processInput method.
   *
   * @param treatedInput list of doubles of form: [[1.0, 2.0, 16.0], [1.0, 4.0, 13.0], ...]
   */
  private void createEdges(List<ArrayList<Double>> treatedInput) {
    for (ArrayList<Double> edgeInfo : treatedInput) {
      int source = edgeInfo.get(0).intValue() - 1;
      int destination = edgeInfo.get(1).intValue() - 1;
      double capacity = edgeInfo.get(2);

      this.V.get(source).addEdge(this.V.get(destination), 0, capacity, true);
    }
  }

  /**
   * Initializes preflow for PushRelabelNetwork by saturating edges that are adjacent to this.s, the
   *     source PRVertex; adds residual edges leading to source in the process. Used in
   *     this.pushRelabel() method.
   */
  public void initializePreflow() {
    for (PREdge pipe : this.s.E) {  // for each "pipe"/PREdge leading out of source vertex
      pipe.flow = pipe.capacity;
      pipe.B.excessFlow += pipe.flow;  // updating destination PRVertex's excess flow
      pipe.B.addEdge(this.s, -pipe.flow, 0, false);  // adding residual backwards edge
    }
  }

  /**
   * Checks if there is at least one Edge of PRVertex u that could dump off PRVertex u's excess flow
   *     into the Edge's destination PRVertex.
   *
   * @param u PRVertex representing the PRVertex we are potentially interested in calling a push on.
   * @return boolean representing whether such an Edge leading to such a PRVertex exists.
   */
  public boolean checkCanPush(PRVertex u) {
    for (PREdge pipe: u.E) {
      if ((u.height > pipe.B.height) && (pipe.flow != pipe.capacity)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Pushes excess flow from PRVertex u to PRVertex v of the amount equal to the minimum between
   *     PRVertex u's total overflow and how much residual capacity remains in PREdge (u,v); updates
   *     pre-flow and excess flow attributes of PRVertex u and of PRVertex v.
   *
   * @param u PRVertex that we are pushing excess flow from.
   */
  public void push(PRVertex u) {
    for (PREdge pipe : u.E) {
      if ((u.height > pipe.B.height) && (pipe.flow != pipe.capacity)) {
        double deltaFlow = Math.min(u.excessFlow, pipe.capacity - pipe.flow);

        u.excessFlow = u.excessFlow - deltaFlow;  // relieving Vertex u of some/all excess flow
        pipe.B.excessFlow = pipe.B.excessFlow + deltaFlow;  // destination Vertex gets excess flow
        pipe.flow = pipe.flow + deltaFlow;

        // now that we have pushed on an Edge, we must update the corresponding reverse Edge:
        // this.updateReverseEdge(pipe, deltaFlow);
        boolean reversePipeDoesNotExist = true;
        for (PREdge e : pipe.B.E) {
          if (e.B.equals(pipe.A)) {
            e.flow = e.flow - deltaFlow;
            reversePipeDoesNotExist = false;
          }
        }

        if (reversePipeDoesNotExist) {  // must create reverse pipe is does not exist
          pipe.B.addEdge(pipe.A, -deltaFlow, 0, false);
        }
      }
    }
  }

  /**
   * Relabels PRVertex u by finding the node among those that PRVertex u has an Edge to which has
   *     the lowest height attribute, and raises PRVertex u's height to be 1 greater than that.
   *
   * @param u PRVertex which we are interested in relabelling.
   */
  public void relabel(PRVertex u) {
    int minHeight = 2 * this.s.height - 1;  // max height for any PRVertex\{s,t}

    for (PREdge pipe : u.E) {  // for each "pipe"/Edge leading out of PRVertex u
      if ((pipe.B.height < minHeight) && (pipe.flow != pipe.capacity)) {
        minHeight = pipe.B.height;
        u.height = minHeight + 1;
      }
    }
  }

  /**
   * Initializes PushRelabelNetwork's preflow and then executes the appropriate sequence of pushes
   *     and relabels.
   *
   * @return double representing sink t's excess flow, equal to the max flow of the flow network.
   */
  public double pushRelabel() {
    // 1. initialize preflow for PushRelabelNetwork:
    this.initializePreflow();

    // 2. while there exists an applicable push or relabel operation:
    PRVertex activeNode = this.getExcessFlowVertex();

    while (activeNode != null) {
      // select an applicable push or relabel operation and perform it:
      if (this.checkCanPush(activeNode)) {  // check if we can push on activeNode...
        System.out.println("PRINTING RESIDUAL GRAPH BEFORE PUSHING ON " + activeNode);
        this.printResidualGraph();

        this.push(activeNode);

        System.out.println("PRINTING RESIDUAL GRAPH AFTER PUSHING ON " + activeNode);
        this.printResidualGraph();
      }
      else {  // otherwise, we relabel activeNode...
        System.out.println("PRINTING RESIDUAL GRAPH BEFORE RELABELING " + activeNode);
        this.printResidualGraph();
        System.out.println("PRINTING " + activeNode + " HEIGHT BEFORE RELABELING:" + activeNode.height + "\n");

        this.relabel(activeNode);

        System.out.println("PRINTING RESIDUAL GRAPH AFTER RELABELING " + activeNode);
        this.printResidualGraph();
        System.out.println("PRINTING " + activeNode + " HEIGHT AFTER RELABELING:" + activeNode.height + "\n");
      }

      System.out.println("=============================================\n");
      activeNode = this.getExcessFlowVertex();
    }

    // 3. the series of pushes and relabels is complete, so we can now return the max flow:
    return this.t.excessFlow;
  }

  /**
   * Looks through PushRelabelNetwork's vertices in this.V and returns one (not this.s or this.t)
   *     that has excess flow; used in this.pushRelabel method.
   *
   * @return returns a PRVertex in this.V\{s,t} that has excess flow greater than 0; returns null if
   *     no such PRVertex exists.
   */
  public PRVertex getExcessFlowVertex() {
    for (PRVertex v : this.V) {
      if (v.excessFlow > 0 && !(v.equals(this.s)) && !(v.equals(this.t))) {
        return v;
      }
    }

    return null;
  }

  /**
   * Prints residual graph, G_f = (V, E_f), where E_f only contains residual edges.
   */
  public void printResidualGraph() {
    StringBuilder sb = new StringBuilder();

    for (PRVertex v : this.V) {
      sb.append(v + ".excessFlow = " + v.excessFlow + ", " + v + ".height=" + v.height + "\n\t");

      for (PREdge e : v.E) {
        if (e.capacity != e.flow) {
          sb.append(e + "; ");
        }
      }
      sb.append("\n");
    }

    System.out.println(sb.toString());
  }

  /**
   * Prints entire graph, G = (V,E), regardless of edge residuals.
   */
  public void printNetwork() {
    for (PRVertex v : this.V) {
      System.out.println(v.toString()
              + ", v.excessFlow = " + v.excessFlow
              + ", v.height=" + v.height
              + "; \n\tEdges: " + v.E);
    }
    System.out.println("\n");
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (PRVertex v : this.V) {
      sb.append("PRVertex: " + v + "; PREdges: " + v.E);
    }

    return sb.toString();
  }
}
