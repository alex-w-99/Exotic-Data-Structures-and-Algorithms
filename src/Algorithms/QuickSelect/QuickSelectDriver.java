package Algorithms.QuickSelect;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSelectDriver {

  public static void main(String[] args) {
    ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
    ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(15, 2, 18, 13, 5, 20, 11, 8, 9, 12, 7, 10, 4, 14, 1, 16, 19, 3, 17, 6));
    ArrayList<Integer> l4 = new ArrayList<>(Arrays.asList(44, 17, 32, 109, 0, 1, 4, -5, 21, 54));
    ArrayList<Integer> l5 = new ArrayList<>(Arrays.asList(39, 103, 81, -3, 79, 60, 53, 99, 17, 0, 23, 44, 56, 62));
    ArrayList<Integer> l6 = new ArrayList<>(Arrays.asList(-773, 99, -33, -54, 63, -79, -9, -18, 1847, 24, 1001, 871, 5, 51, -909, 312, 7, -1, -81, 100, 792, -404, 2, -202, -5, 304, 3, 23, 0, 9, 56));
    ArrayList<Integer> l7 = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

    System.out.println("l1=" + l1);
    System.out.println("\tl1, k=3 -> QS: " + QuickSelect.QuickSelect(l1, 0, l1.size()-1, 3) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l1, 0, l1.size()-1, 3));  // 3
    System.out.println("\tl1, k=5 -> QS: " + QuickSelect.QuickSelect(l1, 0, l1.size()-1, 5) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l1, 0, l1.size()-1, 5));  // 5
    System.out.println("\tl1, k=6 -> QS: " + QuickSelect.QuickSelect(l1, 0, l1.size()-1, 6) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l1, 0, l1.size()-1, 6));  // 6

    System.out.println("\n");

    System.out.println("l2=" + l2);
    System.out.println("\tl2, k=1 -> QS: " + QuickSelect.QuickSelect(l2, 0, l2.size()-1, 1) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l2, 0, l2.size()-1, 1));  // 1
    System.out.println("\tl2, k=3 -> QS: " + QuickSelect.QuickSelect(l2, 0, l2.size()-1, 3) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l2, 0, l2.size()-1, 3));  // 3
    System.out.println("\tl2, k=4 -> QS: " + QuickSelect.QuickSelect(l2, 0, l2.size()-1, 4) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l2, 0, l2.size()-1, 4));  // 4

    System.out.println("\n");

    System.out.println("l3=" + l3);
    System.out.println("\tl3, k=9 -> QS: " + QuickSelect.QuickSelect(l3, 0, l3.size()-1, 9) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l3, 0, l3.size()-1, 9));  // 9
    System.out.println("\tl3, k=18 -> QS: " + QuickSelect.QuickSelect(l3, 0, l3.size()-1, 18) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l3, 0, l3.size()-1, 18));  // 18
    System.out.println("\tl3, k=11 -> QS: " + QuickSelect.QuickSelect(l3, 0, l3.size()-1, 11) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l3, 0, l3.size()-1, 11));  // 11

    System.out.println("\n");

    System.out.println("l4=" + l4);
    System.out.println("\tl4, k=1 -> QS: " + QuickSelect.QuickSelect(l4, 0, l4.size()-1, 1) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l4, 0, l4.size()-1, 1));  // -5
    System.out.println("\tl4, k=2 -> QS: " + QuickSelect.QuickSelect(l4, 0, l4.size()-1, 2) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l4, 0, l4.size()-1, 2));  // 0
    System.out.println("\tl4, k=6 -> QS: " + QuickSelect.QuickSelect(l4, 0, l4.size()-1, 6) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l4, 0, l4.size()-1, 6));  // 32

    System.out.println("\n");

    System.out.println("l5=" + l5);
    System.out.println("\tl5, k=1 -> QS: " + QuickSelect.QuickSelect(l5, 0, l5.size()-1, 1) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l5, 0, l5.size()-1, 1));  // -3
    System.out.println("\tl5, k=2 -> QS: " + QuickSelect.QuickSelect(l5, 0, l5.size()-1, 2) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l5, 0, l5.size()-1, 2));  // 0
    System.out.println("\tl5, k=8 -> QS: " + QuickSelect.QuickSelect(l5, 0, l5.size()-1, 8) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l5, 0, l5.size()-1, 8));  // 56

    System.out.println("\n");

    System.out.println("l6=" + l6);
    System.out.println("\tl6, k=1 -> QS: " + QuickSelect.QuickSelect(l6, 0, l6.size()-1, 1) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l6, 0, l6.size()-1, 1));  // -909
    System.out.println("\tl6, k=17 -> QS: " + QuickSelect.QuickSelect(l6, 0, l6.size()-1, 17) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l6, 0, l6.size()-1, 17));  // 7
    System.out.println("\tl6, k=31 -> QS: " + QuickSelect.QuickSelect(l6, 0, l6.size()-1, 31) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l6, 0, l6.size()-1, 31));  // 1847

    System.out.println("\n");

    System.out.println("l7=" + l7);
    System.out.println("\tl7, k=1 -> QS: " + QuickSelect.QuickSelect(l7, 0, l7.size()-1, 1) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l7, 0, l7.size()-1, 1));  // 0
    System.out.println("\tl7, k=3 -> QS: " + QuickSelect.QuickSelect(l7, 0, l7.size()-1, 3) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l7, 0, l7.size()-1, 3));  // 0
    System.out.println("\tl7, k=7 -> QS: " + QuickSelect.QuickSelect(l7, 0, l7.size()-1, 7) + "; MQS: " + QuickSelect.ModifiedQuickSelect(l7, 0, l7.size()-1, 7));  // 0





  }
}
