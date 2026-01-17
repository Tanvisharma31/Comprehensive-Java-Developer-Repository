# REST API Principles

REST (Representational State Transfer) is an architectural style for providing standards between computer systems on the web.

## Guiding Principles of REST

1.  **Client-Server**: Separation of concerns.
2.  **Stateless**: No client context stored on the server between requests.
3.  **Cacheable**: Responses must define strictly whether they are cacheable or not.
4.  **Uniform Interface**:
    -   **Resource Identification**: URI (e.g., `/users/123`)
    -   **Resource Manipulation**: Through representations (JSON/XML).
    -   **Self-descriptive messages**: Media types (Content-Type).
    -   **HATEOAS**: Hypermedia as the Engine of Application State.
5.  **Layered System**: Helper servers (proxies, load balancers) can intercept traffic.
6.  **Code on Demand (Optional)**: Transferring executable code (JS).

## Resource Naming Guide
-   Use Nouns, not Verbs (`/users` ✅, `/getUsers` ❌)
-   Plural nouns (`/users` ✅, `/user` ❌)
-   Use HTTP methods for actions (GET, POST, PUT, DELETE)
-   Sub-resources: `/users/123/orders` (Orders of user 123)
