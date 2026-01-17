# System Design Fundamentals

## 1. System Design Interview Process
1.  **Requirements Clarification**: Functional (what it does) and Non-Functional (performance, scale) requirements.
2.  **Back-of-the-envelope Estimation**: Traffic, storage, bandwidth, memory estimates.
3.  **System Interface Definition**: Define APIs.
4.  **Data Model**: Schema design (SQL vs NoSQL).
5.  **High-Level Design**: Draw block diagram.
6.  **Detailed Design**: Dig deeper into components (partitions, cache).
7.  **Bottlenecks**: Identify and resolve.

## 2. Key Concepts
-   **Latency**: Time to perform an action.
-   **Throughput**: Number of actions per time unit.
-   **Availability**: Uptime % given a time duration.
-   **Reliability**: Probability that the system will work correct.
-   **Consistency**: Data uniformity across nodes.

## 3. ACID Properties
-   **Atomicity**: Transaction is all or nothing.
-   **Consistency**: Transaction brings DB from one valid state to another.
-   **Isolation**: Concurrent execution results in system state that would be obtained if transactions were executed serially.
-   **Durability**: Committed transactions are permanent.
