// Weather Web App - Frontend
// Integrates with Java backend API

const API_BASE_URL = 'http://localhost:8080/api/weather';

const cityInput = document.getElementById('cityInput');
const searchBtn = document.getElementById('searchBtn');
const weatherInfo = document.getElementById('weatherInfo');
const errorDiv = document.getElementById('error');

// Event listeners
searchBtn.addEventListener('click', searchWeather);
cityInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        searchWeather();
    }
});

async function searchWeather() {
    const city = cityInput.value.trim();
    
    if (!city) {
        showError('Please enter a city name');
        return;
    }
    
    try {
        // Call backend API
        const response = await fetch(`${API_BASE_URL}/${encodeURIComponent(city)}`);
        
        if (!response.ok) {
            if (response.status === 404) {
                throw new Error('City not found');
            }
            throw new Error('Failed to fetch weather data');
        }
        
        const data = await response.json();
        displayWeather(data);
        hideError();
        
    } catch (error) {
        showError(error.message);
        hideWeather();
    }
}

function displayWeather(data) {
    document.getElementById('cityName').textContent = data.city;
    document.getElementById('temp').textContent = Math.round(data.temperature);
    document.getElementById('description').textContent = data.description;
    document.getElementById('humidity').textContent = `${data.humidity}%`;
    document.getElementById('windSpeed').textContent = `${data.windSpeed} km/h}`;
    
    weatherInfo.classList.remove('hidden');
}

function hideWeather() {
    weatherInfo.classList.add('hidden');
}

function showError(message) {
    errorDiv.textContent = message;
    errorDiv.classList.remove('hidden');
}

function hideError() {
    errorDiv.classList.add('hidden');
}

// Example: Mock data for testing without backend
function displayMockWeather() {
    const mockData = {
        city: 'London',
        temperature: 15.5,
        description: 'Partly cloudy',
        humidity: 65,
        windSpeed: 10.2
    };
    displayWeather(mockData);
}
