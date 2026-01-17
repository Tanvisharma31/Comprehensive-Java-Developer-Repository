package com.google.career.ds;

import java.util.*;

public class DataStructureExamples {

    public static void main(String[] args) {
        // --- List ---
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        System.out.println("List: " + list);

        // --- Set ---
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1); // Duplicate ignored
        set.add(2);
        System.out.println("Set: " + set);

        // --- Map ---
        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 30);
        map.put("Bob", 25);
        System.out.println("Map: " + map);

        // --- Stack ---
        Stack<String> stack = new Stack<>();
        stack.push("Bottom");
        stack.push("Top");
        System.out.println("Popped from stack: " + stack.pop());

        // --- Queue ---
        Queue<String> queue = new LinkedList<>();
        queue.add("First");
        queue.add("Second");
        System.out.println("Polled from queue: " + queue.poll());
        
        // --- Tree (TreeMap) ---
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "Three");
        treeMap.put(1, "One");
        treeMap.put(2, "Two");
        System.out.println("Ordered keys: " + treeMap.keySet());
    }
}
