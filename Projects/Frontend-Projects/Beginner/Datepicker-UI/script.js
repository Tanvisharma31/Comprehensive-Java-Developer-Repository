document.getElementById('date-input').addEventListener('change', function(e) {
    const selectedDate = e.target.value;
    const displayElement = document.getElementById('selected-date-display');
    
    if (selectedDate) {
        const date = new Date(selectedDate);
        const formattedDate = date.toLocaleDateString('en-US', {
            weekday: 'long',
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
        displayElement.textContent = formattedDate;
    } else {
        displayElement.textContent = 'No date selected';
    }
});
