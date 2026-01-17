# Workout Tracker API

A RESTful API for tracking workouts and fitness progress.

## Features

- Create and manage workouts
- Track exercises and sets
- Progress tracking
- Workout history
- Statistics and analytics

## API Endpoints

### Workouts
- `POST /api/workouts` - Create workout
- `GET /api/workouts` - Get all workouts
- `GET /api/workouts/{id}` - Get workout details
- `PUT /api/workouts/{id}` - Update workout
- `DELETE /api/workouts/{id}` - Delete workout

### Exercises
- `POST /api/workouts/{workoutId}/exercises` - Add exercise to workout
- `GET /api/exercises` - Get all exercises
- `PUT /api/exercises/{id}` - Update exercise
- `DELETE /api/exercises/{id}` - Remove exercise

### Statistics
- `GET /api/stats/progress` - Get progress statistics
- `GET /api/stats/exercises` - Get exercise statistics
- `GET /api/stats/calendar` - Get workout calendar

## Data Models

### Workout
```json
{
  "id": 1,
  "name": "Chest Day",
  "date": "2024-01-17",
  "duration": 60,
  "exercises": [
    {
      "name": "Bench Press",
      "sets": [
        {"reps": 10, "weight": 80},
        {"reps": 8, "weight": 85}
      ]
    }
  ]
}
```

### Exercise
```json
{
  "name": "Bench Press",
  "muscleGroup": "Chest",
  "sets": [
    {"reps": 10, "weight": 80, "rest": 60}
  ]
}
```

## Statistics

- Total workouts
- Total volume lifted
- Personal records
- Progress charts
- Workout frequency
