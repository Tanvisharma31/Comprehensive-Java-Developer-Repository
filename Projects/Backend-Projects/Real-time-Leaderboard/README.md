# Real-time Leaderboard API

A real-time leaderboard system built with Spring Boot, WebSocket, and Redis for live ranking updates.

## Features

- Real-time score updates via WebSocket
- Leaderboard ranking algorithms
- Redis caching for performance
- RESTful API for score management
- Top N players retrieval
- User ranking position
- Score history tracking

## Technologies

- Java 17+
- Spring Boot 3.x
- Spring WebSocket
- Redis
- PostgreSQL (optional for persistence)
- Maven

## API Endpoints

### REST Endpoints

- `POST /api/scores` - Submit a new score
- `GET /api/leaderboard` - Get top N players
- `GET /api/leaderboard/{userId}` - Get user's rank
- `GET /api/scores/{userId}` - Get user's score history

### WebSocket

- Connect to `/ws/leaderboard` for real-time updates
- Receives leaderboard updates when scores change

## Setup

1. **Prerequisites:**
   ```bash
   - Java 17+
   - Maven
   - Redis (running on localhost:6379)
   ```

2. **Run Redis:**
   ```bash
   docker run -d -p 6379:6379 redis:latest
   ```

3. **Build and Run:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access:**
   - API: `http://localhost:8080/api`
   - WebSocket: `ws://localhost:8080/ws/leaderboard`

## Usage Examples

### Submit Score
```bash
POST /api/scores
Content-Type: application/json

{
  "userId": "user123",
  "username": "John Doe",
  "score": 1500,
  "gameType": "arcade"
}
```

### Get Top 10
```bash
GET /api/leaderboard?limit=10
```

### WebSocket Connection
```javascript
const ws = new WebSocket('ws://localhost:8080/ws/leaderboard');
ws.onmessage = (event) => {
  const leaderboard = JSON.parse(event.data);
  console.log('Updated leaderboard:', leaderboard);
};
```

## Architecture

- **Controller Layer**: REST endpoints for score management
- **Service Layer**: Business logic and ranking algorithms
- **WebSocket Handler**: Real-time updates broadcasting
- **Redis Cache**: Fast leaderboard retrieval
- **Repository**: Optional database persistence

## Ranking Algorithm

- Scores are sorted in descending order
- Ties are resolved by timestamp (earlier submission ranks higher)
- Real-time updates broadcast to all connected clients

## Configuration

Update `application.properties`:
```properties
spring.redis.host=localhost
spring.redis.port=6379
server.port=8080
```
