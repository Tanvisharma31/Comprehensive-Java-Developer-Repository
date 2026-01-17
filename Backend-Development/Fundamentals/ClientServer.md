# Backend Fundamentals

## 1. Client-Server Architecture
-   **Client**: Requesting entity (Browser, Mobile App).
-   **Server**: Providing entity (Backend API, Database).
-   **Request/Response Cycle**: Client sends HTTP Request -> Server processes -> Server sends HTTP Response.

## 2. HTTP Protocol
-   **Verbs**: GET, POST, PUT, DELETE, PATCH.
-   **Status Codes**:
    -   2xx: Success (200 OK, 201 Created)
    -   3xx: Redirection
    -   4xx: Client Error (400 Bad Request, 401 Unauthorized, 404 Not Found)
    -   5xx: Server Error (500 Internal Server Error)

## 3. Statelessness
-   Each request from client to server must contain all of the information necessary to understand the request, and cannot take advantage of any stored context on the server.
-   Session state is kept entirely on the client (or in a separate store like Redis).
