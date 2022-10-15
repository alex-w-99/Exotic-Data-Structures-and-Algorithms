# Exotic Data Structures and Algorithms

A collection of some of my favorite rare and efficient data structures and algorithms written in Java.


The purpose of this project is not just to break down the exact mechanisms by which these data structures and algorithms per se function, but also to provide a deeper context for the rationale behind and the tradeoffs associated with the all data structures and algorithms that underlie different aspects of the software we program, test, and use today.

For example, the fact that Fibonacci Heaps are able to reduce their order of time-complexity when ???? by lazily defering clean-up until the next `Extract Min` operation helps us understand that (Binomial Heaps???).


Please note that, given the eductional purpose of this project, some Java best practices like using `private` access modifiers in conjunction with "getter" and "setter" methods are disregarded.

Concepts covered in this project include (but are not limited to) ?????>. 

Built and tested using IntelliJ IDEA. 

## The Data Structures
- <ins>Skip Lists:</ins> A linked list that is augmented with additional pointers so that each operation runs in average-case log n time. This is done by maintaining a linked hierarchy (i.e., a level of sub-sequences), with each successive sub-sequence skipping over fewer elements than the previous one; the selection of which elements exist in a given layer may be done randomly or deterministically, so that there are approximately log n layers. Skip List are sometimes considered as an alternative to balanced binary trees.
  - **Advantages:** Skip Lists have the best feature of a sorted array (namely, searching in log n time), while maintaining a linked list-like structure that allows insertion (not possible for a static array). 
  - **Disadvantages:** Skip Lists suffer from two major problems: (1) Skip Lists are essentially keeping track of many linked lists at once, which negatively impacts its space-complexity (indeed, its worst-case space-complexity is O(n log n), which highlights this fact), and (2) Skip Lists have a deletion function which can be quite tricky to implement. 
  - **Visualization**: Please find an example immediately below. For a fantastic visualization of how Skip Lists are built, please [click here](https://cmps-people.ok.ubc.ca/ylucet/DS/SkipList.html "UBC Skip List Visualization").
    <p align="center"?
      <img src="https://github.com/alex-w-99/Exotic-Data-Structures-and-Algorithms/blob/main/Images/SkipLists.png" width="500">
    </p>
    
- <ins>Red-Black Trees:</ins> ???
  - ???
  <p align="center">
    <img src="https://github.com/alex-w-99/Exotic-Data-Structures-and-Algorithms/blob/main/Images/SkipLists.png" width="500">
  </p>

- <ins>Binomial Heaps:</ins> ???
  - ???

- <ins>Fibonacci Heaps:</ins> ???
  - ???

## The Algorithms
- <ins>Quick Select:</ins> ???
  - ???
- <ins>Push-Relabel Network:</ins> ???
  - ???

## Areas for Future Improvement
- [ ] asdf
- [ ] asdf

## Screenshots

?????????????????????

## Acknowledgements

- Professor Virgil Pavlu, my Algorithms professor.
- The University of British Columbia, Okanagan Campus, for their fantastic Skip List visualization tool that I linked in my section on Skip Lists above.  

## Contact Information
- Alexander Wilcox
- Email: alexander.w.wilcox@gmail.com
