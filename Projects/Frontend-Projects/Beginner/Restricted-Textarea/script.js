const textarea = document.getElementById('message');
const currentCount = document.getElementById('current-count');
const maxCount = document.getElementById('max-count');
const progressFill = document.getElementById('progress-fill');
const charCount = document.querySelector('.char-count');

const maxLength = parseInt(textarea.getAttribute('maxlength'));
maxCount.textContent = maxLength;

textarea.addEventListener('input', function() {
    const currentLength = this.value.length;
    const percentage = (currentLength / maxLength) * 100;
    
    currentCount.textContent = currentLength;
    progressFill.style.width = percentage + '%';
    
    // Update colors based on usage
    progressFill.classList.remove('warning', 'danger');
    charCount.classList.remove('warning', 'danger');
    
    if (percentage >= 90) {
        progressFill.classList.add('danger');
        charCount.classList.add('danger');
    } else if (percentage >= 75) {
        progressFill.classList.add('warning');
        charCount.classList.add('warning');
    }
});
