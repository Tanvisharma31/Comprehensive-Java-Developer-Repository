# URL Shortener API

A RESTful API service that shortens long URLs.

## Features

- Shorten long URLs
- Redirect to original URL
- Track click statistics
- Custom short codes
- Expiration dates

## API Endpoints

- `POST /api/shorten` - Create short URL
- `GET /{shortCode}` - Redirect to original URL
- `GET /api/stats/{shortCode}` - Get URL statistics
- `DELETE /api/{shortCode}` - Delete short URL

## Request/Response

### Shorten URL
```json
POST /api/shorten
{
  "longUrl": "https://example.com/very/long/url",
  "customCode": "optional",
  "expiresIn": 30
}

Response:
{
  "shortCode": "abc123",
  "shortUrl": "http://localhost:8080/abc123",
  "longUrl": "https://example.com/very/long/url",
  "createdAt": "2024-01-17T10:00:00",
  "expiresAt": "2024-02-16T10:00:00"
}
```

## Implementation Details

- Uses base62 encoding for short codes
- Stores mappings in database
- Validates URL format
- Handles collisions
