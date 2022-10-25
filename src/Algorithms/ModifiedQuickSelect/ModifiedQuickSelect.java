package Algorithms.ModifiedQuickSelect;

import java.util.ArrayList;
import java.util.List;

public class ModifiedQuickSelect {

  /**
   * A helper function to QuickSelect and ModifiedQuickSelect; this function partitions on an array
   *     value, ensuring that all elements to the left of the partition value are less than it, and
   *     all elements to the right of the partition value are greater than it
   *     NOTE: if the customPartitionIndex argument is set to Integer.MIN_VALUE, this method will
   *           partition on the final array element; otherwise, it will partition on the value
   *           in the array corresponding to the custom partition index, A[customPartitionIndex]
   * Time Complexity: O(n)
   *
   * @param A list of Integers representing the array to perform the partition on (in place)
   * @param start int representing the start index to partition on in the array A
   * @param end int representing the end index to partition on in the array A
   * @param customPartitionIndex int representing a desired custom partition index (see note above)
   * @return i int representing the border of the partition
   */
  public static int Partition(List<Integer> A, int start, int end, int customPartitionIndex) {
    int x;  // partition value
    if (customPartitionIndex == Integer.MIN_VALUE) {  // for default partition index
      x = A.get(end);  // this partitions on final element of array
    }
    else {  // for custom partition index
      // swap(A[customPartitionIndex]=x, A[end]);
      x = A.get(customPartitionIndex);  // custom partition index
      A.set(customPartitionIndex, A.get(end));
      A.set(end, x);
    }

    int i = start - 1;  // index i keeps track of elements less than/equal to A[x]

    for (int j = start; j < end; j++) {  // index j from start to end
      if (A.get(j) <= x) {  // if A[j] <= A[end] OR if A[j] <= A[customPartition]
        i++;
        // swap(A[i], A[j]);  place item on "less than/equal to" half of array
        int tmp = A.get(i);
        A.set(i, A.get(j));  // A[i] = A[j];
        A.set(j, tmp);  // A[j] = tmp;
      }
    }
    // swap(A[i+1], A[end])
    int tmp = A.get(i+1);
    A.set(i+1, A.get(end));  // A[i+1] = A[end];
    A.set(end, tmp);  // A[end] = tmp;

    return i + 1;
  }

  /**
   * A recursive algorithm which finds the k-th ranked item in an unsorted array; operates in a
   *     similar way to Quick Sort
   *     NOTE: QuickSelect calls Partition such that the array A is partitioned on its final value
   * Time Complexity: Theta(n); O(n^2)
   *
   * @param A list of Integers representing the array in which to find the k-th ranked element
   * @param start int representing the start index of the array A
   * @param end int representing the end index of the array A
   * @param k int representing the k-th ranked item in A which we seek
   * @return int representing the k-th ranked item in array A
   */
  public static int QuickSelect(ArrayList<Integer> A, int start, int end, int k) {
    if (start == end) {
      return A.get(start);
    }

    int p = Partition(A, start, end, Integer.MIN_VALUE);  // helper function; Theta(n)

    int i = p - start + 1;
    if (k == i) {
      return A.get(p);
    }
    else if (k < i) {
      return QuickSelect(A, start, p-1, k);
    }
    else {  // if (k > i)
      return QuickSelect(A, p+1, end, k-i);
    }
  }

  /**
   * A recursive algorithm which finds the k-th ranked item in an unsorted array; operates in a
   *     similar way to QuickSelect; unlike QuickSelect, however, this algorithm guarantees a
   *     worst-case time complexity of O(n) using median statistics (see README.md)
   *     NOTE: ModifiedQuickSelect calls Partition such that the array A is partitioned on M.
   * Time Complexity: O(n)
   *
   * @param A list of Integers representing the array in which to find the k-th ranked element.
   * @param start int representing the start index of the array A
   * @param end int representing the end index of the array A
   * @param k int representing the k-th ranked item in A which we seek
   * @return int representing the k-th ranked item in array A
   */
  public static int ModifiedQuickSelect(List<Integer> A, int start, int end, int k) {
    // 0. possible base case; check if array of 1 element
    if (start == end) {
      return A.get(start);
    }

    // 1. split elements into groups of 5
    double itemsPerSubArrayAvg = (double) (end - start + 1) / 5;
    int totalSubArrays = (int) java.lang.Math.ceil(itemsPerSubArrayAvg);

    // 2. sort each group (of 5 elements each) with insertion sort; Theta(1) * n/5
    for (int i = 0; i < totalSubArrays; i++) {  // insertion sort algo
      int subArrayStart = start + i*5;
      int subArrayEnd = Math.min(subArrayStart + 4, end);

      for (int partition = subArrayStart; partition <= subArrayEnd && partition < A.size(); partition++) {  // A[0:partition]
        int key = A.get(partition);
        int idx = partition - 1;

        while (idx >= subArrayStart && A.get(idx) > key) {  // move all x in A[0:partition-1] with x>key over
          A.set(idx+1, A.get(idx));  // A[idx+1] = A[idx];
          idx--;
        }
        A.set(idx+1, key);  // A[idx+1] = key;
      }
    }

    // 3. create list containing the indices of the medians of each group
    ArrayList<Integer> mediansIndices = new ArrayList<>();
    for (int i = 0; i < totalSubArrays; i++) {  // adding median values of groups of 5 to "medians"
      int subArrayStart = start + i*5;
      int subArrayEnd = Math.min(subArrayStart + 4, end);

      int middleIndex;
      if ((subArrayEnd - subArrayStart) % 2 == 0) {  // i.e., even total elements in sub-array
        middleIndex = (subArrayEnd - subArrayStart) / 2 + subArrayStart;
      }
      else {  // i.e., odd total elements in sub-array (take higher of two middle indices)
        middleIndex = (int) java.lang.Math.ceil(subArrayEnd - subArrayStart) / 2 + subArrayStart;
      }
      mediansIndices.add(middleIndex);
    }

    // 4. get index of median of medians, M
    ArrayList<Integer> mediansValues = new ArrayList<>();  // values of median indices
    for (int j = 0; j < mediansIndices.size(); j++) {
      int medianIndex = mediansIndices.get(j);
      mediansValues.add(A.get(medianIndex));  // for each index in mediansIndices, add its corresponding value in A
    }

    int valueOfMedianOfMedians;  // e.g., mediansValues = [18, 13, 11, 7, 16] -> 13
    if (mediansValues.size() == 1) {
      valueOfMedianOfMedians = mediansValues.get(0);
    }
    else {
      // want the 50-th percentile value in mediansValues:
      int indexOfMedianOfMedians = (int) java.lang.Math.floor((double) mediansValues.size() / 2);
      valueOfMedianOfMedians = ModifiedQuickSelect(mediansValues,
              0, mediansValues.size()-1, indexOfMedianOfMedians);
    }
    int M = A.indexOf(valueOfMedianOfMedians);  // M = *index* of median of medians

    // 5. using M as pivot, do quick select recursion as normal
    int p = Partition(A, start, end, M);

    int i = p - start + 1;
    if (k == i) {
      return A.get(p);
    }
    else if (k < i) {
      return ModifiedQuickSelect(A, start, p-1, k);
    }
    else {  // if (k > i)
      return ModifiedQuickSelect(A, p+1, end, k-i);
    }
  }
}
