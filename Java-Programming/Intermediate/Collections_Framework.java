package Intermediate;

import java.util.*;

/**
 * Java Collections Framework
 * 
 * Demonstrates:
 * - List (ArrayList, LinkedList)
 * - Set (HashSet, TreeSet, LinkedHashSet)
 * - Map (HashMap, TreeMap, LinkedHashMap)
 * - Queue (PriorityQueue, ArrayDeque)
 * - Collections utility methods
 */

public class Collections_Framework {
    public static void main(String[] args) {
        System.out.println("=== Java Collections Framework Demo ===\n");
        
        // ========== LIST ==========
        System.out.println("1. LIST - Ordered collection, allows duplicates");
        
        // ArrayList - Dynamic array, fast random access
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");
        arrayList.add("Apple"); // Duplicates allowed
        System.out.println("ArrayList: " + arrayList);
        System.out.println("Get element at index 1: " + arrayList.get(1));
        
        // LinkedList - Doubly linked list, fast insertion/deletion
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        System.out.println("LinkedList: " + linkedList);
        
        // ========== SET ==========
        System.out.println("\n2. SET - No duplicates, unordered (except TreeSet)");
        
        // HashSet - Fast lookup, no order
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Red");
        hashSet.add("Green");
        hashSet.add("Blue");
        hashSet.add("Red"); // Duplicate ignored
        System.out.println("HashSet: " + hashSet);
        
        // TreeSet - Sorted order
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(20);
        System.out.println("TreeSet (sorted): " + treeSet);
        
        // LinkedHashSet - Insertion order preserved
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("First");
        linkedHashSet.add("Second");
        linkedHashSet.add("Third");
        System.out.println("LinkedHashSet: " + linkedHashSet);
        
        // ========== MAP ==========
        System.out.println("\n3. MAP - Key-value pairs");
        
        // HashMap - Fast lookup, no order
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Alice", 25);
        hashMap.put("Bob", 30);
        hashMap.put("Charlie", 28);
        System.out.println("HashMap: " + hashMap);
        System.out.println("Bob's age: " + hashMap.get("Bob"));
        
        // TreeMap - Sorted by keys
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("Zebra", "Animal");
        treeMap.put("Apple", "Fruit");
        treeMap.put("Book", "Object");
        System.out.println("TreeMap (sorted by key): " + treeMap);
        
        // LinkedHashMap - Insertion order preserved
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(3, "Third");
        linkedHashMap.put(1, "First");
        linkedHashMap.put(2, "Second");
        System.out.println("LinkedHashMap: " + linkedHashMap);
        
        // ========== QUEUE ==========
        System.out.println("\n4. QUEUE - FIFO (First In First Out)");
        
        // PriorityQueue - Elements ordered by priority
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(30);
        priorityQueue.offer(10);
        priorityQueue.offer(20);
        System.out.println("PriorityQueue: " + priorityQueue);
        System.out.println("Poll (remove highest priority): " + priorityQueue.poll());
        
        // ArrayDeque - Double-ended queue
        Deque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst("First");
        arrayDeque.addLast("Last");
        arrayDeque.addFirst("New First");
        System.out.println("ArrayDeque: " + arrayDeque);
        
        // ========== COLLECTIONS UTILITY METHODS ==========
        System.out.println("\n5. Collections Utility Methods");
        
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("Original: " + numbers);
        
        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);
        
        Collections.reverse(numbers);
        System.out.println("Reversed: " + numbers);
        
        Collections.shuffle(numbers);
        System.out.println("Shuffled: " + numbers);
        
        System.out.println("Max: " + Collections.max(numbers));
        System.out.println("Min: " + Collections.min(numbers));
        
        // ========== ITERATION ==========
        System.out.println("\n6. Iterating Collections");
        
        System.out.println("Using enhanced for loop:");
        for (String fruit : arrayList) {
            System.out.print(fruit + " ");
        }
        System.out.println();
        
        System.out.println("Using Iterator:");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        System.out.println("Using forEach (Java 8+):");
        arrayList.forEach(fruit -> System.out.print(fruit + " "));
        System.out.println();
        
        System.out.println("\n=== Collection Characteristics ===");
        System.out.println("List: Ordered, allows duplicates, indexed access");
        System.out.println("Set: No duplicates, unordered (except TreeSet)");
        System.out.println("Map: Key-value pairs, no duplicate keys");
        System.out.println("Queue: FIFO, priority-based ordering");
    }
}
