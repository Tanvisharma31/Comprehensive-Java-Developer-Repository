# Data Structures

Data structures are formats for organizing and storing data.

## Common Data Structures

### 1. Linear Data Structures
-   **Arrays**: Fixed-size collection of elements of the same type. O(1) access.
-   **Linked Lists**: Elements are stored in nodes, each pointing to the next. O(1) insertion/deletion at known position, O(n) access.
-   **Stacks**: LIFO (Last In First Out). Operations: push, pop, peek.
-   **Queues**: FIFO (First In First Out). Operations: enqueue, dequeue.

### 2. Nonlinear Data Structures
-   **Trees**: Hierarchical structure. Root node with children.
    -   **Binary Tree**: Max 2 children per node.
    -   **BST (Binary Search Tree)**: Left child < Parent < Right child.
    -   **Heap**: Complete binary tree (Min-Heap or Max-Heap).
-   **Graphs**: Nodes (vertices) connected by edges.

### 3. Hashing
-   **HashMap**: Key-value pairs. O(1) average access. Uses hash function to map keys to indices.

## Java Collections Framework
-   `List`: `ArrayList`, `LinkedList`
-   `Set`: `HashSet`, `TreeSet`
-   `Map`: `HashMap`, `TreeMap`
-   `Queue`: `PriorityQueue`, `ArrayDeque`
