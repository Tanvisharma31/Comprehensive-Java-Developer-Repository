document.getElementById('contact-form').addEventListener('submit', function(e) {
    e.preventDefault();
    
    // Clear previous errors
    document.querySelectorAll('.error').forEach(el => el.textContent = '');
    
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const message = document.getElementById('message').value.trim();
    
    let isValid = true;
    
    if (!name) {
        document.getElementById('name-error').textContent = 'Name is required';
        isValid = false;
    }
    
    if (!email || !email.includes('@')) {
        document.getElementById('email-error').textContent = 'Valid email is required';
        isValid = false;
    }
    
    if (!message) {
        document.getElementById('message-error').textContent = 'Message is required';
        isValid = false;
    }
    
    if (isValid) {
        const successMessage = document.getElementById('success-message');
        successMessage.textContent = 'Form submitted successfully!';
        successMessage.hidden = false;
        this.reset();
        
        setTimeout(() => {
            successMessage.hidden = true;
        }, 5000);
    }
});
