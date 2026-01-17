# Todo List API

A RESTful API for managing todo items built with Spring Boot.

## Features

- Create, read, update, delete todos
- Mark todos as complete
- Filter todos by status
- RESTful API design
- JSON request/response

## API Endpoints

- `GET /api/todos` - Get all todos
- `GET /api/todos/{id}` - Get todo by ID
- `POST /api/todos` - Create new todo
- `PUT /api/todos/{id}` - Update todo
- `DELETE /api/todos/{id}` - Delete todo
- `PATCH /api/todos/{id}/complete` - Mark todo as complete

## Request/Response Examples

### Create Todo
```json
POST /api/todos
{
  "title": "Buy groceries",
  "description": "Milk, bread, eggs"
}

Response:
{
  "id": 1,
  "title": "Buy groceries",
  "description": "Milk, bread, eggs",
  "completed": false,
  "createdAt": "2024-01-17T10:00:00"
}
```

### Get All Todos
```json
GET /api/todos

Response:
[
  {
    "id": 1,
    "title": "Buy groceries",
    "description": "Milk, bread, eggs",
    "completed": false,
    "createdAt": "2024-01-17T10:00:00"
  }
]
```

## Setup

1. Install dependencies (Maven)
2. Run the application
3. API available at `http://localhost:8080/api/todos`
