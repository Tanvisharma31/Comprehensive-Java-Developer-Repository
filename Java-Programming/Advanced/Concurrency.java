package Advanced;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Java Concurrency and Multithreading
 * 
 * Demonstrates:
 * - Thread creation and management
 * - Synchronization
 * - Thread pools (ExecutorService)
 * - Concurrent collections
 * - Atomic variables
 * - CompletableFuture
 * - Locks and Semaphores
 */

// Example class for synchronization
class Counter {
    private int count = 0;
    
    // Synchronized method - only one thread can execute at a time
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}

// Thread-safe counter using AtomicInteger
class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);
    
    public void increment() {
        count.incrementAndGet();
    }
    
    public int getCount() {
        return count.get();
    }
}

public class Concurrency {
    
    // Example: Creating threads
    public static void threadCreationExample() {
        System.out.println("=== Thread Creation ===");
        
        // Method 1: Extend Thread class
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        // Method 2: Implement Runnable interface
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Thread 2: " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join(); // Wait for thread1 to complete
            thread2.join(); // Wait for thread2 to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Example: Synchronization
    public static void synchronizationExample() {
        System.out.println("\n=== Synchronization ===");
        Counter counter = new Counter();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Final count (should be 2000): " + counter.getCount());
    }
    
    // Example: Atomic variables
    public static void atomicExample() {
        System.out.println("\n=== Atomic Variables ===");
        AtomicCounter atomicCounter = new AtomicCounter();
        
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        for (int i = 0; i < 4; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicCounter.increment();
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Final atomic count (should be 4000): " + atomicCounter.getCount());
    }
    
    // Example: ExecutorService (Thread Pool)
    public static void executorServiceExample() {
        System.out.println("\n=== ExecutorService (Thread Pool) ===");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Example: Concurrent Collections
    public static void concurrentCollectionsExample() {
        System.out.println("\n=== Concurrent Collections ===");
        
        // ConcurrentHashMap - thread-safe HashMap
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        System.out.println("ConcurrentHashMap: " + map);
        
        // CopyOnWriteArrayList - thread-safe ArrayList
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        System.out.println("CopyOnWriteArrayList: " + list);
        
        // BlockingQueue - thread-safe queue
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("Task1");
        queue.offer("Task2");
        queue.offer("Task3");
        System.out.println("BlockingQueue: " + queue);
    }
    
    // Example: CompletableFuture (Async programming)
    public static void completableFutureExample() {
        System.out.println("\n=== CompletableFuture (Async) ===");
        
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello from async task!";
        });
        
        future.thenAccept(result -> {
            System.out.println("Result: " + result);
        });
        
        // Chain multiple async operations
        CompletableFuture<Integer> future2 = CompletableFuture
            .supplyAsync(() -> 10)
            .thenApply(x -> x * 2)
            .thenApply(x -> x + 5);
        
        try {
            System.out.println("Chained result: " + future2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    // Example: Locks
    public static void locksExample() {
        System.out.println("\n=== Locks ===");
        ReentrantLock lock = new ReentrantLock();
        Counter counter = new Counter();
        
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 5; i++) {
                    counter.increment();
                    System.out.println("Thread 1 incremented, count: " + counter.getCount());
                }
            } finally {
                lock.unlock();
            }
        });
        
        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 5; i++) {
                    counter.increment();
                    System.out.println("Thread 2 incremented, count: " + counter.getCount());
                }
            } finally {
                lock.unlock();
            }
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Example: Semaphore
    public static void semaphoreExample() {
        System.out.println("\n=== Semaphore ===");
        Semaphore semaphore = new Semaphore(2); // Allow 2 permits
        
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Task " + taskId + " acquired permit");
                    Thread.sleep(1000);
                    System.out.println("Task " + taskId + " releasing permit");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Java Concurrency Demo ===\n");
        
        threadCreationExample();
        synchronizationExample();
        atomicExample();
        executorServiceExample();
        concurrentCollectionsExample();
        completableFutureExample();
        locksExample();
        semaphoreExample();
        
        System.out.println("\n=== Concurrency Concepts ===");
        System.out.println("✓ Thread: Basic unit of execution");
        System.out.println("✓ Synchronization: Prevent race conditions");
        System.out.println("✓ Thread Pool: Reuse threads for efficiency");
        System.out.println("✓ Atomic Variables: Lock-free thread-safe operations");
        System.out.println("✓ Concurrent Collections: Thread-safe data structures");
        System.out.println("✓ CompletableFuture: Async programming");
        System.out.println("✓ Locks: Fine-grained synchronization");
        System.out.println("✓ Semaphore: Control access to resources");
    }
}
