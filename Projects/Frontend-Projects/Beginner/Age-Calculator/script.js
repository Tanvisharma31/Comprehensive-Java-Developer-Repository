// Set max date to today
document.getElementById('birthdate').max = new Date().toISOString().split('T')[0];

document.getElementById('calculate-btn').addEventListener('click', function() {
    const birthdate = document.getElementById('birthdate').value;
    
    if (!birthdate) {
        alert('Please select your birthdate');
        return;
    }
    
    const birth = new Date(birthdate);
    const today = new Date();
    
    let years = today.getFullYear() - birth.getFullYear();
    let months = today.getMonth() - birth.getMonth();
    let days = today.getDate() - birth.getDate();
    
    if (days < 0) {
        months--;
        const lastMonth = new Date(today.getFullYear(), today.getMonth(), 0);
        days += lastMonth.getDate();
    }
    
    if (months < 0) {
        years--;
        months += 12;
    }
    
    // Calculate total days
    const diffTime = Math.abs(today - birth);
    const totalDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    
    // Update display
    document.getElementById('years').textContent = years;
    document.getElementById('months').textContent = months;
    document.getElementById('days').textContent = days;
    document.getElementById('total-days').textContent = `Total: ${totalDays} days old`;
    
    // Show result
    document.getElementById('result').hidden = false;
});
