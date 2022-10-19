# Pedagogic Data Structures and Algorithms

A collection of some of my favorite rare and efficient data structures and algorithms written in Java.


The purpose of this project is not just to break down the exact mechanisms by which these data structures and algorithms per se function, but also to provide a deeper context for the rationale behind and the tradeoffs associated with the all data structures and algorithms that underlie different aspects of the software we program, test, and use today.

For example, the fact that Fibonacci Heaps are able to reduce their order of time-complexity when ???? by lazily defering clean-up until the next `Extract Min` operation helps us understand that (Binomial Heaps???).


Please note that, given the eductional purpose of this project, some Java best practices like using `private` access modifiers in conjunction with "getter" and "setter" methods are disregarded.

Concepts covered in this project include (but are not limited to) ?????>. 

Built and tested using IntelliJ IDEA. 

## The Data Structures
- <ins>Skip Lists:</ins> A Skip List is a type of linked list that is augmented with additional pointers so that each operation runs in average-case *log n* time. This is done by maintaining a linked hierarchy/levels of sub-sequences, with each successive sub-sequence skipping over fewer elements than the previous one; the selection of which elements exist in a given layer may be done randomly or deterministically (implemented randomly here, with a 50% chance an element added makes it up to the next tier), so that there are approximately *log n* layers. Skip List are sometimes considered as an alternative to balanced binary trees.
  - **Advantages:** Skip Lists have the best feature of a sorted array (namely, searching in *log n* time), while maintaining a linked list-like structure that allows insertion (not possible for a static array). 
  - **Disadvantages:** Skip Lists suffer from two major problems: (1) Skip Lists are essentially keeping track of many linked lists at once, which negatively impacts its space-complexity (indeed, its worst-case space-complexity is O(*n log n*), which highlights this fact), and (2) Skip Lists have a deletion function which can be quite tricky to implement. 
  - **Visualization**: Please find an example of a Skip List immediately below. For a fantastic animation of how Skip Lists are built and used, [please click here](https://cmps-people.ok.ubc.ca/ylucet/DS/SkipList.html "UBC Skip List Visualization").
  <p align="center">
    <img src="https://github.com/alex-w-99/Pedagogic-Data-Structures-and-Algorithms/blob/main/Images/SkipLists.png" width="500">
  </p>

- <ins>Red-Black Trees:</ins> ???
  - ???

- <ins>Binomial Heaps:</ins> A binomial heap is a type of mergable heap that acts as a priority queue; binomial heap contain a collection of binomial trees linked at their roots, such that the roots form a linked list. Importantly, a binomial heap can only have 0 or 1 binomial tree of a given order. To that end, when calling `EXTRACT-MIN` on a binomial heap, the "orphaned" children of the obviated node are reversed in order (so to be in increasing order of order), placed in the linked list of binomial tree roots, and then `UNION` is continuously called until the binomial heap has no more than 1 binomial tree of a given order. Binomial heaps are so named because the number of nodes at a given depth correspond to elements of Pascal's Triangle.
  - **Review of Related Terms/Concepts:**
    - Mergable Heap: A data structure containing several min- or max-heaps which themselves may be merged with one another via a `UNION` operation.
    - Binomial Tree: An ordered tree that is defined recursively; the binomial tree *B*<sub>*0*</sub> consists of a single node, and the binomial tree *B*<sub>*k*</sub> consists of two binomial trees *B*<sub>*k-1*</sub> that are linked together so that the root of one is the leftmost child of the root of the other. Binomial trees exhibit several interesting properties; for instance, a binomial tree of order *k* will have height *k* and 2<sup>*k*</sup> total nodes. 
  - **Advantages:** The `UNION` operation in a binomial heap runs in *log n* time, whereas `UNION` runs in linear time in a binary heap.
  - **Disadvantages:** The [amortized costs](https://en.wikipedia.org/wiki/Amortized_analysis "Amortized Analysis (Wikipedia)") associated with the `INSERT`, `DECREASE-KEY`, `UNION`, `FIND-MIN` operations are *log n* time in a binomial heap, whereas these operations run in constant time in a Fibonacci heap (see below).
  - **Visualization**: Please find the generic binomial heap immediately below, comprised of binomial trees *B*<sub>*0*</sub> through *B*<sub>*4*</sub>.
  <p align="center">
    <img src="https://github.com/alex-w-99/Pedagogic-Data-Structures-and-Algorithms/blob/main/Images/BinomialHeap.png" width="500">
  </p>

- <ins>Fibonacci Heaps:</ins> ???
  - ???
  - **Advantages**: ?
  - **Disadvantages**: ?
  - **Visualization**: ?

## The Algorithms
- <ins>Quick Select:</ins> ???
  - ???
- <ins>Push-Relabel Network:</ins> ???
  - ???

## Areas for Future Improvement
- [ ] Modify code such that it follows Java best practices (e.g., using `private` access modifiers with getter and setter functions, utilizing Java interfaces, etc.).
- [ ] asdf

## Screenshots

?????????????????????

## Acknowledgements

- Professor Virgil Pavlu, my Algorithms professor.
- *Introduction to Algorithms* (CLRS) by Cormen, Leiserson, Rivest, and Stein, for providing me with the quintessential graduate algorithms textbook that cost me so many hours of lost sleep.
- The University of British Columbia, Okanagan Campus, for their fantastic Skip List visualization tool that I linked in my section on Skip Lists above.  

## Contact Information
- Alexander Wilcox
- Email: alexander.w.wilcox@gmail.com
