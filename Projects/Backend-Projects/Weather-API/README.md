# Weather API

A RESTful API that fetches and returns weather data using external weather service.

## Features

- Get current weather by city
- Get weather forecast
- Error handling
- API key management

## API Endpoints

- `GET /api/weather/current?city={cityName}` - Get current weather
- `GET /api/weather/forecast?city={cityName}&days={days}` - Get weather forecast

## Example Response

```json
{
  "city": "London",
  "temperature": 15.5,
  "description": "Partly cloudy",
  "humidity": 65,
  "windSpeed": 12.3,
  "timestamp": "2024-01-17T10:00:00"
}
```

## Setup

1. Get API key from weather service (e.g., OpenWeatherMap)
2. Set environment variable: `WEATHER_API_KEY=your_key`
3. Run the application

## Usage

```bash
curl http://localhost:8080/api/weather/current?city=London
```
