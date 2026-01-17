const celsiusInput = document.getElementById('celsius');
const fahrenheitInput = document.getElementById('fahrenheit');
const kelvinInput = document.getElementById('kelvin');

let updating = false;

celsiusInput.addEventListener('input', function() {
    if (updating) return;
    updating = true;
    const celsius = parseFloat(this.value) || 0;
    fahrenheitInput.value = (celsius * 9/5 + 32).toFixed(2);
    kelvinInput.value = (celsius + 273.15).toFixed(2);
    updating = false;
});

fahrenheitInput.addEventListener('input', function() {
    if (updating) return;
    updating = true;
    const fahrenheit = parseFloat(this.value) || 0;
    const celsius = (fahrenheit - 32) * 5/9;
    celsiusInput.value = celsius.toFixed(2);
    kelvinInput.value = (celsius + 273.15).toFixed(2);
    updating = false;
});

kelvinInput.addEventListener('input', function() {
    if (updating) return;
    updating = true;
    const kelvin = parseFloat(this.value) || 0;
    const celsius = kelvin - 273.15;
    celsiusInput.value = celsius.toFixed(2);
    fahrenheitInput.value = (celsius * 9/5 + 32).toFixed(2);
    updating = false;
});
