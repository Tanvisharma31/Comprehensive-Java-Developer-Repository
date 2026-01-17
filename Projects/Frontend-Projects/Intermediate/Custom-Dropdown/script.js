const dropdown = document.getElementById('custom-dropdown');
const selected = document.getElementById('dropdown-selected');
const options = document.getElementById('dropdown-options');
const selectedValueDisplay = document.getElementById('selected-value-display');

selected.addEventListener('click', function(e) {
    e.stopPropagation();
    dropdown.classList.toggle('active');
});

document.querySelectorAll('.dropdown-option').forEach(option => {
    option.addEventListener('click', function() {
        const value = this.getAttribute('data-value');
        const text = this.textContent;
        
        // Update selected display
        selected.querySelector('span:first-child').textContent = text;
        selectedValueDisplay.textContent = text;
        
        // Update selected state
        document.querySelectorAll('.dropdown-option').forEach(opt => {
            opt.classList.remove('selected');
        });
        this.classList.add('selected');
        
        // Close dropdown
        dropdown.classList.remove('active');
    });
});

// Close dropdown when clicking outside
document.addEventListener('click', function(e) {
    if (!dropdown.contains(e.target)) {
        dropdown.classList.remove('active');
    }
});
