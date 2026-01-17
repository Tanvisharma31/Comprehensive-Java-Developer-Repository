# Movie Reservation System

A RESTful API for a movie ticket reservation system.

## Features

- Movie catalog
- Theater and showtime management
- Seat selection and reservation
- Booking management
- Payment processing
- Booking confirmation

## API Endpoints

### Movies
- `GET /api/movies` - Get all movies
- `GET /api/movies/{id}` - Get movie details
- `GET /api/movies/{id}/showtimes` - Get showtimes for movie

### Theaters
- `GET /api/theaters` - Get all theaters
- `GET /api/theaters/{id}` - Get theater details
- `GET /api/theaters/{id}/seats` - Get seat layout

### Bookings
- `POST /api/bookings` - Create booking
- `GET /api/bookings/{id}` - Get booking details
- `PUT /api/bookings/{id}/cancel` - Cancel booking
- `GET /api/bookings/user/{userId}` - Get user bookings

### Showtimes
- `GET /api/showtimes` - Get available showtimes
- `GET /api/showtimes/{id}/seats` - Get available seats

## Data Models

### Movie
```json
{
  "id": 1,
  "title": "Inception",
  "genre": "Sci-Fi",
  "duration": 148,
  "rating": "PG-13",
  "posterUrl": "url"
}
```

### Showtime
```json
{
  "id": 1,
  "movieId": 1,
  "theaterId": 1,
  "startTime": "2024-01-17T18:00:00",
  "endTime": "2024-01-17T20:28:00",
  "price": 12.50
}
```

### Booking
```json
{
  "id": 1,
  "showtimeId": 1,
  "seats": ["A1", "A2"],
  "totalPrice": 25.00,
  "status": "CONFIRMED",
  "userId": "user123"
}
```

## Features

- Real-time seat availability
- Seat locking during booking
- Payment integration
- Email confirmation
- Booking cancellation
- Refund processing
