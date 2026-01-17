# Design a URL Shortener (e.g., TinyURL)

## 1. Requirements
-   **Functional**:
    -   Given a long URL, return a unique short URL.
    -   Clicking short URL redirects to original long URL.
    -   Link expiration (optional).
-   **Non-Functional**:
    -   Highly available.
    -   Low latency for redirection.
    -   URL should not be predictable (security).

## 2. Estimation
-   New URLs: 100M/month -> ~40/sec.
-   Reads: 100:1 ratio -> 4000/sec.
-   Storage: 500 bytes/URL. 100M * 500 = 50GB/month. 5 years = 3TB.

## 3. Data Model
**Table: URL_Mapping**
-   `Hash`: VARCHAR(16) [PK]
-   `OriginalURL`: VARCHAR(512)
-   `CreationDate`: Timestamp
-   `ExpirationDate`: Timestamp

## 4. API Design
-   `createShortURL(api_dev_key, original_url, expire_date?) -> short_url`
-   `getOriginalURL(short_url) -> original_url` (Usually HTTP 301/302 Redirect)

## 5. High Level Design
`Client` -> `Load Balancer` -> `App Servers` -> `Cache (Redis)` -> `Database`

## 6. Detailed Design
-   **Encoding**: Base62 (a-z, A-Z, 0-9).
    -   6 chars: 62^6 = ~56 billion combinations. Sufficient.
-   **Generating Key**:
    -   A: Hash (MD5) of URL -> take first 6 chars. (Collision possible?)
    -   B: KGS (Key Generation Service). Pre-generate keys and store in DB. App server requests key from KGS.
-   **Cache**: Store hot URLs in Redis.
