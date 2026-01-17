// Check if user has already made a choice
const cookieConsent = localStorage.getItem('cookieConsent');

if (!cookieConsent) {
    // Show banner after a short delay
    setTimeout(() => {
        document.getElementById('cookie-banner').classList.add('show');
    }, 1000);
}

// Accept cookies
document.getElementById('accept-cookies').addEventListener('click', function() {
    localStorage.setItem('cookieConsent', 'accepted');
    hideBanner();
});

// Decline cookies
document.getElementById('decline-cookies').addEventListener('click', function() {
    localStorage.setItem('cookieConsent', 'declined');
    hideBanner();
});

// Customize cookies
document.getElementById('customize-cookies').addEventListener('click', function() {
    alert('Customize functionality would open a preferences panel here.');
    // In a real implementation, this would open a modal with cookie preferences
});

function hideBanner() {
    const banner = document.getElementById('cookie-banner');
    banner.classList.remove('show');
    setTimeout(() => {
        banner.style.display = 'none';
    }, 300);
}
