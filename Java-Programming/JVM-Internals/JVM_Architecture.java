package JVM_Internals;

/**
 * JVM (Java Virtual Machine) Architecture
 * 
 * This file explains the JVM architecture and memory model.
 * 
 * JVM Components:
 * 1. Class Loader Subsystem
 * 2. Runtime Data Areas
 *    - Method Area
 *    - Heap Area
 *    - Stack Area
 *    - PC Registers
 *    - Native Method Stack
 * 3. Execution Engine
 *    - Interpreter
 *    - JIT Compiler
 *    - Garbage Collector
 * 4. Native Method Interface (JNI)
 * 5. Native Method Libraries
 */

public class JVM_Architecture {
    
    /**
     * Memory Areas in JVM:
     * 
     * 1. METHOD AREA (Shared)
     *    - Stores class metadata
     *    - Static variables
     *    - Method bytecode
     *    - Runtime constant pool
     * 
     * 2. HEAP AREA (Shared)
     *    - Young Generation
     *      * Eden Space
     *      * Survivor Space (S0, S1)
     *    - Old Generation (Tenured)
     *    - Stores object instances
     * 
     * 3. STACK AREA (Per Thread)
     *    - Local variables
     *    - Method parameters
     *    - Return addresses
     *    - Each method call creates a stack frame
     * 
     * 4. PC REGISTERS (Per Thread)
     *    - Stores address of current instruction
     * 
     * 5. NATIVE METHOD STACK (Per Thread)
     *    - For native methods (C/C++)
     */
    
    // Example: Understanding heap memory
    public static void demonstrateHeapMemory() {
        System.out.println("=== Heap Memory Demonstration ===");
        
        // Objects are stored in heap
        String str1 = new String("Hello"); // Object in heap
        String str2 = new String("World");  // Object in heap
        
        // String literals may be in string pool (part of heap)
        String str3 = "Hello"; // May reference string pool
        
        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
        System.out.println("str3: " + str3);
        System.out.println("str1 == str3: " + (str1 == str3)); // Different references
        System.out.println("str1.equals(str3): " + str1.equals(str3)); // Same content
    }
    
    // Example: Understanding stack memory
    public static void demonstrateStackMemory() {
        System.out.println("\n=== Stack Memory Demonstration ===");
        
        int localVar = 10; // Stored in stack
        String localStr = "Stack"; // Reference in stack, object in heap
        
        System.out.println("Local variable: " + localVar);
        System.out.println("Local string: " + localStr);
        
        // Each method call creates a new stack frame
        recursiveMethod(3);
    }
    
    private static void recursiveMethod(int depth) {
        if (depth > 0) {
            int local = depth; // Each call has its own stack frame
            System.out.println("Recursion depth: " + depth + ", local: " + local);
            recursiveMethod(depth - 1);
        }
    }
    
    // Example: Memory information
    public static void showMemoryInfo() {
        System.out.println("\n=== Memory Information ===");
        
        Runtime runtime = Runtime.getRuntime();
        
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        System.out.println("Max Memory: " + (maxMemory / 1024 / 1024) + " MB");
        System.out.println("Total Memory: " + (totalMemory / 1024 / 1024) + " MB");
        System.out.println("Free Memory: " + (freeMemory / 1024 / 1024) + " MB");
        System.out.println("Used Memory: " + (usedMemory / 1024 / 1024) + " MB");
    }
    
    // Example: Garbage Collection hint
    public static void demonstrateGC() {
        System.out.println("\n=== Garbage Collection ===");
        
        // Create objects that will become eligible for GC
        for (int i = 0; i < 1000; i++) {
            String temp = new String("Temp" + i);
            // temp goes out of scope, becomes eligible for GC
        }
        
        // Suggest GC (but doesn't guarantee it)
        System.gc();
        
        System.out.println("Created 1000 temporary objects");
        System.out.println("They are now eligible for garbage collection");
    }
    
    public static void main(String[] args) {
        System.out.println("=== JVM Architecture Overview ===\n");
        
        demonstrateHeapMemory();
        demonstrateStackMemory();
        showMemoryInfo();
        demonstrateGC();
        
        System.out.println("\n=== JVM Memory Areas Summary ===");
        System.out.println("1. Method Area: Class metadata, static variables");
        System.out.println("2. Heap: Object instances (Young + Old Generation)");
        System.out.println("3. Stack: Local variables, method calls (per thread)");
        System.out.println("4. PC Registers: Current instruction pointer (per thread)");
        System.out.println("5. Native Method Stack: For native code (per thread)");
        
        System.out.println("\n=== Garbage Collection ===");
        System.out.println("Young Generation GC (Minor GC):");
        System.out.println("  - Eden space: New objects");
        System.out.println("  - Survivor spaces: Objects that survived one GC");
        System.out.println("Old Generation GC (Major GC):");
        System.out.println("  - Long-lived objects");
        System.out.println("  - Takes longer time");
        
        System.out.println("\n=== JVM Tuning Tips ===");
        System.out.println("-Xms: Initial heap size");
        System.out.println("-Xmx: Maximum heap size");
        System.out.println("-XX:NewRatio: Ratio of old to young generation");
        System.out.println("-XX:SurvivorRatio: Ratio of Eden to Survivor spaces");
    }
}
