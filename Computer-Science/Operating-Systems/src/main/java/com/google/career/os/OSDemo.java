package com.google.career.os;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class OSDemo {

    // --- Process Demo (Conceptual) ---
    // Java runs in a JVM process. We can spawn other processes.
    public static void spawnProcess() {
        try {
            ProcessBuilder builder = new ProcessBuilder("echo", "Hello from a new process!");
            // In a real shell this works, on Windows might need cmd /c
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                builder.command("cmd.exe", "/c", "echo Hello from Windows Process");
            }
            Process process = builder.start();
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- Thread Demo ---
    public static void threadDemo() {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 running: " + Thread.currentThread().getName());
        });
        t1.start();
    }

    // --- Concurrency / Semaphore Demo ---
    static class SharedResource {
        private Semaphore semaphore = new Semaphore(1);

        void accessResource(String threadName) {
            try {
                System.out.println(threadName + " waiting for permit.");
                semaphore.acquire();
                System.out.println(threadName + " gets permit.");
                Thread.sleep(100); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(threadName + " releases permit.");
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("--- OS Concepts Demo ---");
        
        spawnProcess();
        threadDemo();

        SharedResource resource = new SharedResource();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for (int i = 0; i < 3; i++) {
            final int id = i;
            executor.submit(() -> resource.accessResource("Worker-" + id));
        }
        
        executor.shutdown();
    }
}
