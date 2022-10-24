package Algorithms.QuickSelect;

import java.util.ArrayList;
import java.util.List;

public class QuickSelect {

  /**
   * ????????? add note about Integer.MIN_VALUE
   * Time Complexity:
   *
   * @param A list of Integers representing the array to perform the partition on (in place).
   * @param start
   * @param end
   * @param customPartitionIndex
   * @return
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
   *
   *
   * @param A
   * @param start
   * @param end
   * @param k
   * @return
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
   *
   *
   * @param A
   * @param start
   * @param end
   * @param k
   * @return
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
