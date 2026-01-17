package JVM_Internals;

import java.lang.ref.WeakReference;
import java.lang.ref.SoftReference;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Garbage Collection in Java
 * 
 * Garbage Collection automatically manages memory by reclaiming
 * objects that are no longer referenced.
 * 
 * GC Algorithms:
 * - Serial GC
 * - Parallel GC
 * - CMS (Concurrent Mark Sweep)
 * - G1 GC (Garbage First)
 * - ZGC (Z Garbage Collector)
 * - Shenandoah
 */

class GCObject {
    private String name;
    private byte[] data;
    
    public GCObject(String name, int size) {
        this.name = name;
        this.data = new byte[size]; // Allocate memory
    }
    
    @Override
    protected void finalize() throws Throwable {
        System.out.println("GCObject " + name + " is being garbage collected");
        super.finalize();
    }
    
    @Override
    public String toString() {
        return "GCObject{" + name + "}";
    }
}

public class Garbage_Collection {
    
    // Demonstrate object lifecycle
    public static void objectLifecycle() {
        System.out.println("=== Object Lifecycle ===");
        
        GCObject obj1 = new GCObject("Object1", 1024);
        GCObject obj2 = new GCObject("Object2", 2048);
        
        System.out.println("Created: " + obj1);
        System.out.println("Created: " + obj2);
        
        // obj1 is still referenced
        obj1 = null; // obj1 is now eligible for GC
        
        System.out.println("obj1 set to null - eligible for GC");
        
        // Suggest GC (for demonstration only)
        System.gc();
        
        try {
            Thread.sleep(100); // Give GC time to run
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Demonstrate reference types
    public static void referenceTypes() {
        System.out.println("\n=== Reference Types ===");
        
        // Strong Reference (default)
        GCObject strongRef = new GCObject("Strong", 1024);
        System.out.println("Strong Reference: " + strongRef);
        
        // Soft Reference - GC'd only when memory is low
        SoftReference<GCObject> softRef = new SoftReference<>(
            new GCObject("Soft", 1024)
        );
        System.out.println("Soft Reference: " + softRef.get());
        
        // Weak Reference - GC'd when no strong references
        WeakReference<GCObject> weakRef = new WeakReference<>(
            new GCObject("Weak", 1024)
        );
        System.out.println("Weak Reference: " + weakRef.get());
        
        // Phantom Reference - Used for cleanup operations
        ReferenceQueue<GCObject> queue = new ReferenceQueue<>();
        PhantomReference<GCObject> phantomRef = new PhantomReference<>(
            new GCObject("Phantom", 1024), queue
        );
        System.out.println("Phantom Reference: " + phantomRef.get()); // Always null
        
        // Clear strong reference
        strongRef = null;
        System.gc();
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("After GC:");
        System.out.println("Soft Reference: " + softRef.get());
        System.out.println("Weak Reference: " + weakRef.get());
    }
    
    // Demonstrate memory allocation
    public static void memoryAllocation() {
        System.out.println("\n=== Memory Allocation ===");
        
        Runtime runtime = Runtime.getRuntime();
        
        long before = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory before: " + (before / 1024) + " KB");
        
        // Allocate objects
        GCObject[] objects = new GCObject[100];
        for (int i = 0; i < 100; i++) {
            objects[i] = new GCObject("Obj" + i, 1024);
        }
        
        long after = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory after allocation: " + (after / 1024) + " KB");
        System.out.println("Memory used: " + ((after - before) / 1024) + " KB");
        
        // Clear references
        objects = null;
        System.gc();
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long afterGC = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory after GC: " + (afterGC / 1024) + " KB");
    }
    
    // Demonstrate GC generations
    public static void gcGenerations() {
        System.out.println("\n=== GC Generations ===");
        
        System.out.println("Young Generation:");
        System.out.println("  - Eden Space: New objects allocated here");
        System.out.println("  - Survivor Space (S0, S1): Objects that survived one GC");
        System.out.println("  - Minor GC: Collects young generation");
        
        System.out.println("\nOld Generation:");
        System.out.println("  - Tenured Space: Long-lived objects");
        System.out.println("  - Major GC: Collects old generation (slower)");
        
        // Create objects that will age
        GCObject[] longLived = new GCObject[10];
        for (int i = 0; i < 10; i++) {
            longLived[i] = new GCObject("LongLived" + i, 1024);
            // Multiple GC cycles would promote these to old generation
        }
        
        System.out.println("\nCreated 10 objects that may survive to old generation");
    }
    
    public static void main(String[] args) {
        System.out.println("=== Garbage Collection Demo ===\n");
        
        objectLifecycle();
        referenceTypes();
        memoryAllocation();
        gcGenerations();
        
        System.out.println("\n=== GC Algorithms ===");
        System.out.println("1. Serial GC: Single-threaded, good for small apps");
        System.out.println("2. Parallel GC: Multi-threaded, throughput focused");
        System.out.println("3. CMS: Low pause time, deprecated in Java 14+");
        System.out.println("4. G1 GC: Balanced, good for large heaps");
        System.out.println("5. ZGC: Ultra-low latency, large heaps");
        System.out.println("6. Shenandoah: Low pause time, concurrent");
        
        System.out.println("\n=== GC Tuning Flags ===");
        System.out.println("-XX:+UseSerialGC: Use Serial GC");
        System.out.println("-XX:+UseParallelGC: Use Parallel GC");
        System.out.println("-XX:+UseG1GC: Use G1 GC");
        System.out.println("-XX:MaxGCPauseMillis=200: Target max pause time");
        System.out.println("-XX:+PrintGCDetails: Print GC details");
        
        System.out.println("\n=== Best Practices ===");
        System.out.println("✓ Avoid creating unnecessary objects");
        System.out.println("✓ Use object pooling for frequently created objects");
        System.out.println("✓ Set appropriate heap size (-Xms, -Xmx)");
        System.out.println("✓ Monitor GC logs");
        System.out.println("✓ Choose appropriate GC algorithm for your use case");
    }
}
