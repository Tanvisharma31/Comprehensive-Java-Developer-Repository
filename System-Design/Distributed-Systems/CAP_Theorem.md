# CAP Theorem

In a distributed data store, you can only provide **two** of the following three guarantees:

1.  **Consistency (C)**: Every read receives the most recent write or an error.
2.  **Availability (A)**: Every request receives a (non-error) response, without the guarantee that it contains the most recent write.
3.  **Partition Tolerance (P)**: The system continues to operate despite an arbitrary number of messages being dropped or delayed by the network between nodes.

## Implications
Since network partitions (P) are inevitable in distributed systems, you must choose between C and A.

-   **CP (Consistency + Partition Tolerance)**: Waiting for data to sync. System might be unavailable during partition. (e.g., MongoDB, HBase).
-   **AP (Availability + Partition Tolerance)**: Return the most recent version available, even if stale. (e.g., Cassandra, DynamoDB).
-   **CA**: Not really possible in distributed systems over a network. RDBMS are CA in a single node cluster.

## PACELC Theorem
An extension of CAP.
-   If there is a Partition (P), choose A or C.
-   Else (E), choose Latency (L) or Consistency (C).
