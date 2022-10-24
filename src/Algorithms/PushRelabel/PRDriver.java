package Algorithms.PushRelabel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PRDriver {

  public static void main(String[] args) {

    // 1. creating PRNetwork
    PRNetwork network = new PRNetwork();

    // 2. adding weighted edges; (vertex1, vertex2, edgeWeight)
    List<ArrayList<Double>> weightedEdges = new ArrayList<>();
    weightedEdges.add(new ArrayList<>(Arrays.asList(1.0, 2.0, 16.0)));
    weightedEdges.add(new ArrayList<>(Arrays.asList(1.0, 4.0, 13.0)));
    weightedEdges.add(new ArrayList<>(Arrays.asList(2.0, 3.0, 13.0)));
    weightedEdges.add(new ArrayList<>(Arrays.asList(3.0, 6.0, 20.0)));
    weightedEdges.add(new ArrayList<>(Arrays.asList(3.0, 4.0, 9.0)));
    weightedEdges.add(new ArrayList<>(Arrays.asList(4.0, 2.0, 4.0)));
    weightedEdges.add(new ArrayList<>(Arrays.asList(4.0, 5.0, 14.0)));
    weightedEdges.add(new ArrayList<>(Arrays.asList(5.0, 3.0, 7.0)));
    weightedEdges.add(new ArrayList<>(Arrays.asList(5.0, 6.0, 4.0)));
    network.processInput(weightedEdges);

    // 3. printing residual graph before any pushes or relabels
    System.out.println("Printing residual graph before pushing and relabeling:");
    network.printResidualGraph();
    System.out.println("================================================================");
    System.out.println("================================================================");
    System.out.println("================================================================");

    // 4. running pushes and relabels
    System.out.println("Running pushes and relabels...\n");
    double maxFlow = network.pushRelabel();
    System.out.println("================================================================");
    System.out.println("================================================================");
    System.out.println("================================================================");

    // 5. printing max flow value and finalized network
    System.out.println("PUSH-RELABEL COMPLETE.");
    System.out.println("MAX FLOW=" + maxFlow + "\n");
    System.out.println("PRINTING ENTIRE NETWORK (not just residual graph):");
    network.printNetwork();
  }
}
