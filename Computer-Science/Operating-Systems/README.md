# Operating Systems

An Operating System (OS) is system software that manages computer hardware, software resources, and provides common services for computer programs.

## Key Concepts

### 1. Process Management
-   **Process**: A program in execution.
-   **Thread**: The smallest sequence of programmed instructions that can be managed independently by a scheduler.
-   **Context Switching**: Storing the state of a process or thread so that it can be restored and resume execution later.
-   **Scheduling**: Method by which threads, processes or data flows are given access to system resources (e.g., Round Robin, FCFS).

### 2. Memory Management
-   **Virtual Memory**: Maps memory addresses used by a program, called virtual addresses, into physical addresses in computer memory.
-   **Paging**: A memory management scheme that eliminates the need for contiguous allocation of physical memory.
-   **Thrashing**: Occurs when a computer's virtual memory subsystem is constantly paging.

### 3. Concurrency
-   **Deadlock**: A state in which each member of a group is waiting for another member, including itself, to take action.
-   **Mutex/Semaphore**: Synchronization primitives to prevent race conditions.

### 4. File Systems
-   **Inode**: Data structure in a Unix-style file system that describes a file-system object like a file or a directory.

## Java & OS
Java abstracts many OS details, but understanding threads and concurrency is crucial.

-   **Process**: `ProcessBuilder`
-   **Threads**: `Thread` class, `Runnable` interface.
-   **Concurrency**: `java.util.concurrent` package.
