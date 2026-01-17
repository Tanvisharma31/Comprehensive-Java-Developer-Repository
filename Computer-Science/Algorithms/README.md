# Algorithms

Algorithms are step-by-step procedures or sets of rules to solve a specific problem. In computer science, they are the foundation of efficient programming.

## Core Concepts

1.  **Time Complexity**: How the runtime of an algorithm increases with the size of the input (Big O Notation).
2.  **Space Complexity**: How much memory an algorithm uses.

## Key Categories

### 1. Sorting Algorithms
Sorting is arranging data in a particular format.
-   **Bubble Sort**: Repeatedly steps through the list, compares adjacent elements and swaps them if they are in the wrong order. O(n^2).
-   **Merge Sort**: Divide and Conquer algorithm. Divides the array into halves, sorts them and merges them. O(n log n).
-   **Quick Sort**: Picks an element as pivot and partitions the given array around the picked pivot. O(n log n) equivalent.
-   **Heap Sort**: Comparison based sorting technique based on Binary Heap data structure. O(n log n).

### 2. Searching Algorithms
-   **Linear Search**: Iterate through the collection one by one. O(n).
-   **Binary Search**: Search a sorted array by repeatedly dividing the search interval in half. O(log n).

### 3. Graph Algorithms
-   **BFS (Breadth First Search)**: Traverses a graph breadthward motion and uses a Queue.
-   **DFS (Depth First Search)**: Traverses a graph depthward motion and uses a Stack.
-   **Dijkstra's Algorithm**: Finds the shortest path from a source node to all other nodes in a weighted graph.

### 4. Dynamic Programming
-   Breaking a problem into simpler subproblems and storing the results to avoid recomputing.
-   Examples: Fibonacci, Knapsack Problem, Longest Common Subsequence.

## Java Implementation
Check the `src` directory for Java implementations of these algorithms.
