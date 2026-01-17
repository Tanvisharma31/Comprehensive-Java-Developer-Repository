package Computer_Networks;

import java.io.*;
import java.net.*;

/**
 * Socket Programming in Java
 * 
 * Demonstrates:
 * - TCP Socket programming
 * - Client-Server communication
 * - HTTP request/response
 * - Network I/O
 */

// Simple TCP Server
class TCPServer {
    public static void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            System.out.println("Waiting for client connection...");
            
            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());
            
            // Read from client
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
            );
            PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true
            );
            
            // Read client message
            String clientMessage = in.readLine();
            System.out.println("Received from client: " + clientMessage);
            
            // Send response to client
            out.println("Server received: " + clientMessage);
            
            // Close connections
            clientSocket.close();
            System.out.println("Connection closed");
            
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}

// Simple TCP Client
class TCPClient {
    public static void connectToServer(String host, int port, String message) {
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to server: " + host + ":" + port);
            
            // Send message to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
            
            // Read response from server
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            String response = in.readLine();
            System.out.println("Server response: " + response);
            
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}

// HTTP Client example
class HTTPClient {
    public static void sendHTTPRequest(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Set request method
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Java HTTP Client");
            
            // Get response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            // Read response
            BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
            );
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Response: " + response.toString().substring(0, 
                Math.min(200, response.length())) + "...");
            
        } catch (IOException e) {
            System.err.println("HTTP request error: " + e.getMessage());
        }
    }
}

public class Socket_Programming {
    public static void main(String[] args) {
        System.out.println("=== Socket Programming Demo ===\n");
        
        // Note: In a real application, server and client would run in separate processes
        // For demonstration, we show the code structure
        
        System.out.println("1. TCP Server Code Structure:");
        System.out.println("   - Create ServerSocket on a port");
        System.out.println("   - Accept client connections");
        System.out.println("   - Read/write data");
        System.out.println("   - Close connections");
        
        System.out.println("\n2. TCP Client Code Structure:");
        System.out.println("   - Create Socket to connect to server");
        System.out.println("   - Send data to server");
        System.out.println("   - Read response from server");
        System.out.println("   - Close connection");
        
        System.out.println("\n3. HTTP Client Example:");
        // Uncomment to test (requires internet connection)
        // HTTPClient.sendHTTPRequest("https://www.example.com");
        
        System.out.println("\n=== Network Concepts ===");
        System.out.println("✓ TCP: Reliable, connection-oriented");
        System.out.println("✓ UDP: Fast, connectionless");
        System.out.println("✓ Socket: Endpoint for communication");
        System.out.println("✓ Port: Identifies application on host");
        System.out.println("✓ IP Address: Identifies host on network");
        
        System.out.println("\n=== Java Networking Classes ===");
        System.out.println("✓ Socket: TCP client socket");
        System.out.println("✓ ServerSocket: TCP server socket");
        System.out.println("✓ DatagramSocket: UDP socket");
        System.out.println("✓ URL/URLConnection: HTTP client");
        System.out.println("✓ HttpURLConnection: Advanced HTTP client");
    }
}
