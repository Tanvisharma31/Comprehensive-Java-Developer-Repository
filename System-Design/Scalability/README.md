# Scalability

## Types of Scaling
1.  **Vertical Scaling (Scale Up)**: Adding more power (CPU, RAM) to an existing machine.
    -   *Pros*: Simple.
    -   *Cons*: Hardware limit, single point of failure.
2.  **Horizontal Scaling (Scale Out)**: Adding more machines to the pool of resources.
    -   *Pros*: Unlimited scaling, redundancy.
    -   *Cons*: Complexity in data consistency and management.

## Load Balancing
Distributes incoming network traffic across multiple servers.
-   **Algorithms**: Round Robin, Least Connections, IP Hash.
-   **Layer 4 (Transport)**: Based on IP/Port.
-   **Layer 7 (Application)**: Based on content (URL, Cookies).

## Caching
Temporary storage for high-speed access.
-   **Application Cache**: Memcached, Redis.
-   **Database Cache**: Buffer pool.
-   **CDN**: Content Delivery Network for static assets.
-   **Cache Invalidation**: Write-through, Write-around, Write-back.

## Database Sharding
Partitioning data across multiple databases.
-   **Horizontal Partitioning**: Split by range or hash of key (e.g., UserID 1-1000 in DB1).
-   **Vertical Partitioning**: Split by feature (e.g., User tables in DB1, Photos in DB2).
