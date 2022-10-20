# Pedagogic Data Structures and Algorithms

A collection of some of my favorite rare and efficient data structures and algorithms written in Java.


The purpose of this project is not just to break down the exact mechanisms by which these data structures and algorithms per se function, but also to provide a deeper context for the rationale behind and the tradeoffs associated with the all data structures and algorithms that underlie different aspects of the software we program, test, and use today.

For example, the fact that Fibonacci Heaps are able to reduce their order of time-complexity when ???? by lazily defering clean-up until the next `Extract Min` operation helps us understand that (Binomial Heaps???).


Please note that, given the eductional purpose of this project, some Java best practices like using `private` access modifiers in conjunction with "getter" and "setter" methods are disregarded.

Concepts covered in this project include (but are not limited to) ?????>. 

Built and tested using IntelliJ IDEA. 

## The Data Structures
- <ins>Skip Lists:</ins> A skip list is a type of linked list that is augmented with additional pointers so that each operation runs in average-case *log n* time. This is done by maintaining a linked hierarchy/levels of sub-sequences, with each successive sub-sequence skipping over fewer elements than the previous one; the selection of which elements exist in a given layer may be done randomly or deterministically (implemented randomly here, with a 50% chance an element added makes it up to the next tier), so that there are approximately *log n* layers. Skip lists are sometimes considered as an alternative to balanced binary trees.
  - **Advantages**: Skip lists have the best feature of a sorted array (namely, searching in *log n* time), while maintaining a linked list-like structure that allows insertion (not possible for a static array). 
  - **Disadvantages**: Skip lists suffer from two major problems: (1) skip lists are essentially keeping track of many linked lists at once, which negatively impacts its space-complexity (indeed, its worst-case space-complexity is O(*n log n*), which highlights this fact), and (2) skip lists have a deletion function which can be quite tricky to implement. 
  - **Visualization**: Please find an example of a skip list immediately below. For a fantastic animation of how skip lists are built and used, [please click here](https://cmps-people.ok.ubc.ca/ylucet/DS/SkipList.html "UBC Skip List Visualization").
    <p align="center">
      <img src="https://github.com/alex-w-99/Pedagogic-Data-Structures-and-Algorithms/blob/main/Images/SkipLists.png" width="500">
    </p>

- <ins>Red-Black Trees:</ins> A red-black tree is a type of self-balancing binary search tree (BST). Red-black trees have an additional color attribute, and must satisfy the following properties: (1) every node's color attribute is either `RED` or `BLACK`, (2) the root is `BLACK`, (3) every leaf (`NIL`) is `BLACK`, (4) if a node is `RED`, both of its children are `BLACK`, and (5) for each node, every path to its descendant leaves contains exactly *x* `BLACK` nodes. Some common alternatives to red-black trees include AVL trees, Splay trees, and treaps.
  - **Review of Related Terms/Concepts**: 
    - **Binary Search Tree (BST)**: A binary tree satisfying the following properties: (1) the left sub-tree contains only nodes with keys lesser than its parent's, (2) the right sub-tree contains only nodes with keys greater than its parent's, (3) the left and right sub-trees are themselves BSTs, and (4) there are no duplicate nodes. Note that `INSERT` and `DELETE` can be implemented with linear time complexity with respect to the BST's height (i.e., O(*h*)-time). 
    - **Self-Balancing BST**: A BST which automatically "compresses" the tree following insertion and deletion operations when possible.
    - **Why Red-Black Trees Work**: In the worst-case of a BST, nodes are added in strictly decreasing or increasing order, resulting in a glorified linked-list (and thus linear time searches, insertions, and deletions). In contrast, the properties of a red-black tree mean that its height will be at most 2*log(n+1)*, meaning all red-black tree operations can implemented in O(*log n*) time.
  - **Advantages**: A regular BST is not self-balancing, meaning that its operations will have varying time complexities (see example of linear time operations in worst-case scenario immediately above). Meanwhile, red-black trees ensure logarithmic time for all operations no matter what. 
  - **Disadvantages**: In order to preserve the "red-black" properties following `INSERT` and `DELETE` operations, the color attributes of some nodes and the tree's pointer structure must be modified. Restoring these properties involves a *rotation* operation, of which there are two types: a left rotation, and a right rotation (see image below). Suffice it to say, the 3 cases of `INSERT` and the 4 cases of `DELETE` are quite tedious to implement.
    <p align="center">
      <img src="https://github.com/alex-w-99/Pedagogic-Data-Structures-and-Algorithms/blob/main/Images/RedBlackTreeRotations.png" width="500">
    </p>  
  - **Visualization**: Please find the red-black tree example immediately below, couresy of Wikipedia's page on [red-black trees](https://en.wikipedia.org/wiki/Red-black_tree "Red-Black Trees Wikipedia").
    <p align="center">
      <img src="https://github.com/alex-w-99/Pedagogic-Data-Structures-and-Algorithms/blob/main/Images/RedBlackTreeExample.png" width="500">
    </p>

- <ins>Binomial Heaps:</ins> A binomial heap is a type of mergeable heap that acts as a priority queue; binomial heaps contain a collection of binomial trees linked at their roots, such that the roots form a linked list. Importantly, a binomial heap can only have 0 or 1 binomial tree of a given order. To that end, when calling `EXTRACT-MIN` on a binomial heap, the "orphaned" children of the obviated node are reversed in order (so to be in increasing order of order), placed in the linked list of binomial tree roots, and then `UNION` is continuously called until the binomial heap has no more than 1 binomial tree of a given order. Binomial heaps are so named because the number of nodes at a given depth correspond to elements of Pascal's Triangle.
  - **Review of Related Terms/Concepts:**
    - **Mergeable Heap**: A data structure containing several min- or max-heaps which themselves may be merged with one another via a `UNION` operation.
    - **Binomial Tree**: An ordered tree that is defined recursively; the binomial tree *B*<sub>*0*</sub> consists of a single node, and the binomial tree *B*<sub>*k*</sub> consists of two binomial trees *B*<sub>*k-1*</sub> that are linked together so that the root of one is the leftmost child of the root of the other. Binomial trees exhibit several interesting properties; for instance, a binomial tree of order *k* will have height *k* and 2<sup>*k*</sup> total nodes. 
  - **Advantages**: The `UNION` operation in a binomial heap runs in *log n* time, as compared to linear time in a binary heap.
  - **Disadvantages**: The [amortized costs](https://en.wikipedia.org/wiki/Amortized_analysis "Amortized Analysis Wikipedia") associated with the `INSERT`, `DECREASE-KEY`, `UNION`, `FIND-MIN` operations are *log n* time in a binomial heap, whereas these operations run in constant time in a Fibonacci heap (see below).
  - **Visualization**: Please find the generic binomial heap example immediately below, comprised of binomial trees *B*<sub>*0*</sub> through *B*<sub>*4*</sub>.
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
  - **Advantages**: ?
  - **Disadvantages**: ?
  - **Visualization**: ?

- <ins>Push-Relabel Algorithm:</ins> Given a flow network *G = (V,E)*, a source *s*, and a sink *t*, the push-relabel algorithm finds the max flow. Unlike the Ford-Fulkerson algorithm (see below), push-relabel works one vertex at a time, only considering the vertex's neighbors in the residual network, and push-relabel does not maintain the flow-conservation property throughout execution (i.e., nodes here can have capacity during execution). The basic intuition is, coming from the Ford-Fulkerson algorithm, directed edges still correspond to "pipes"; vertices, however, have two new properties: (1) each vertex has an arbitrarily large reservoir that can accumulate excess, and (2) each vertex sits on a platform whose height increases as the algorithm progresses. Vertex heights determine how flow may be pushed (flow can only be pushed downhill). Moreover, the source *s* is fixed at height *V*, and sink *t* is fixed at height 0; all other vertices start at height 0 and may increase over time.
  - **Push-Relabel Order of Operations**: (1) The algorithm sends flow from vertex *s* to vertices directly connected to it by filling each outgoing pipe to capacity; i.e., saturates across the cut (*s*, *V*&#92;{*s*}). When this first flow enters the immediate vertices of source *s*, it is stored in their reservoirs (before eventually being pushed downhill). (2) At some point, we will find that the only pipes that leave a vertex *u* and are not already saturated connect to vertices with heights equal to/less than *h(u)*. To rid vertex *u* of its excess flow, we `RELABEL(u)`, increasing *h(u)* to be 1 greater than the height of vertex *u*'s lowest neighbor to which *u* has an unsaturated pipe connection. (3) Eventually, all the flow that can possibly get through to sink *t* has arrived there, constrained by pipe capacities (the amount of flow across any cut is still limited by its capacity). To make the pre-flow saturation "legal", the excess collected in the reservoirs of overflowing vertices is sent back to *s* by continuously relabeling all overflowed vertices to above *h(s) = V*. Once we have emptied these reservoirs, we have a legal, maximum flow.
  - **Review of Related Terms/Concepts:**
    - **Flow Network**: A direct graph *G = (V,E)* where each edge *(u,v)* in *G* has a non-negative capacity (or "weight"), *w(u,v)* ≥ 0. Flow networks have two special vertices: (1) a **source** *s*, a vertex with no incoming edges, and (2) a **sink** *t*, a vertex with no outgoing edges. Finally, each edge receives a flow, where the amount of flow through an edge cannot exceed its capacity *w*. Please find the flow network example image with labeled edge weights immediately below.
      <p align="center">
        <img src="https://github.com/alex-w-99/Pedagogic-Data-Structures-and-Algorithms/blob/main/Images/FlowNetworkExample.png" width="500">
      </p>
    - **Max Flow Problem**: Given a flow network *G* with source *s* and sink *t*, find the maximum possible flow value; in other words, with source *s* having an infinite outflow capacity, find the maximum possible inflow to sink *t*. The max flow problem has two non-obvious applications: (1) [bipartite matching](https://www.geeksforgeeks.org/maximum-bipartite-matching/ "GeeksforGeeks Bipartite Matching"), and (2) finding the number of disjoint paths from source *s* to sink *t* (here, all existing edges have weights of 1). 
    - **Residual Network**: Given a flow network *G* and a flow *f*, recall that the residual network *G*<sub>*f*</sub> consists of edges with capacities that represent how we can change the flow on edges of *G*. More intuitively, the residual network allows us to "cancel" an already assigned flow between two edges. The only *initial* different between a residual network and a flow network is that the former may contain both an edge *(u,v)* and its reversal *(v,u)*, while the latter only contains *(u,v)*. For a more concrete explanation/example of residual networks, please watch [this excellent video](https://www.youtube.com/watch?v=XPpmzulEmjA&ab_channel=Udacity "Residual Network Explanation Video") from Georgia Tech on YouTube. 
    - **Ford-Fulkerson Method**: The most common solution to the max flow problem (referred to as a "method" rather than an "algorithm" here because it encompasses several different implementations with varying complexities). The Ford-Fulkerson method first initializes the flow *f* to 0. Next, while there exists an augmenting path *p* in the residual network *G*<sub>*f*</sub>, it augments the flow *f* along path *p* (note that this flow must be optimal by the [Max-Flow Min-Cut Theorem](https://en.wikipedia.org/wiki/Max-flow_min-cut_theorem "Max-Flow Min-Cut Theorem Wikipedia")). Using DFS to find augmenting paths, finding a path in *G*<sub>*f*</sub> is O(*E*); with max flow *f*, there are at most *f* iterations; hence, Ford-Fulkerson's time-complexity is O(*E* * *f*). To understand why this complexity's worst-case scenario can be horrendous, please read [this article](https://www.cyberpointllc.com/blog-posts/cp-worst-case-complexity-of-ford-fulkerson.php "Ford-Fulkerson worst-case scenario").
  - **Advantages**: The push-relabel algorithm runs in O(*V*<sup>2</sup> * *E*), which is asymptotically superior to the O(*V* * *E*<sup>2</sup>) time-complexity of the [Edmonds-Karp algorithm](https://en.wikipedia.org/wiki/Edmonds-Karp_algorithm "Edmonds-Karp algorithm Wikipedia"). Moreover, the push-relabel algorithm avoids the worst-case scenario of Ford-Fulkerson (see above).
  - **Disadvantages**: As is probably obvious at this point, the push-relabel algorithm can be extremely tedious to implement (at least in my experience coding it in Java). 

## Areas for Future Improvement
- [ ] Modify code such that it follows Java best practices (e.g., using `private` access modifiers with getter and setter functions, utilizing Java interfaces, etc.).
- [ ] asdf

## Screenshots

?????????????????????

## Acknowledgements

- Professor Virgil Pavlu, my Algorithms professor.
- *Introduction to Algorithms* (CLRS) by Cormen, Leiserson, Rivest, and Stein, both for being the source of some images used above, and for providing me with the quintessential graduate algorithms textbook that cost me so many hours of lost sleep.
- The University of British Columbia, Okanagan Campus, for their fantastic skip list visualization tool that I linked in my section on skip lists above.  

## Contact Information
- Alexander Wilcox
- Email: alexander.w.wilcox@gmail.com
