# Docker Basics

Docker containerization for Java applications.

## Files

- `Dockerfile` - Single-stage and multi-stage build examples
- `docker-compose.yml` - Multi-container application setup

## Commands

### Build Image
```bash
docker build -t todo-api:latest .
```

### Run Container
```bash
docker run -p 8080:8080 todo-api:latest
```

### Docker Compose
```bash
# Start all services
docker-compose up -d

# Stop all services
docker-compose down

# View logs
docker-compose logs -f
```

## Multi-Stage Build

The Dockerfile includes a multi-stage build example that:
1. Builds the application in a Maven image
2. Copies only the JAR to a smaller runtime image

This reduces the final image size significantly.

## Docker Compose Services

- **app**: Java Spring Boot application
- **db**: PostgreSQL database
- **redis**: Redis cache

## Benefits

- Consistent environments
- Easy deployment
- Isolation
- Scalability
- Resource efficiency
