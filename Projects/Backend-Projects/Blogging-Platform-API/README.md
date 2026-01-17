# Blogging Platform API

A complete RESTful API for a blogging platform with posts, comments, and user management.

## Features

- User authentication (JWT)
- Create, read, update, delete blog posts
- Comment system
- Tag management
- Search functionality
- Pagination

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user
- `POST /api/auth/refresh` - Refresh token

### Posts
- `GET /api/posts` - Get all posts (paginated)
- `GET /api/posts/{id}` - Get post by ID
- `POST /api/posts` - Create new post (authenticated)
- `PUT /api/posts/{id}` - Update post (author only)
- `DELETE /api/posts/{id}` - Delete post (author only)

### Comments
- `GET /api/posts/{postId}/comments` - Get comments for post
- `POST /api/posts/{postId}/comments` - Add comment (authenticated)
- `DELETE /api/comments/{id}` - Delete comment (author only)

## Data Models

### Post
```json
{
  "id": 1,
  "title": "My First Post",
  "content": "Post content...",
  "author": "user123",
  "tags": ["java", "spring"],
  "createdAt": "2024-01-17T10:00:00",
  "updatedAt": "2024-01-17T10:00:00"
}
```

## Security

- JWT-based authentication
- Password hashing (BCrypt)
- Role-based access control
- Input validation
