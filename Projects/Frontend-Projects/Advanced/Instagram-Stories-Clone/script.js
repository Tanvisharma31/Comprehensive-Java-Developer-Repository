let currentStory = 0;
let progressInterval;
const storyDuration = 5000; // 5 seconds per story

const storiesContainer = document.getElementById('stories-container');
const storiesGrid = document.getElementById('stories-grid');
const storyItems = document.querySelectorAll('.story-item');
const progressFill = document.getElementById('progress-fill');
const closeBtn = document.getElementById('close-btn');
const prevBtn = document.getElementById('prev-btn');
const nextBtn = document.getElementById('next-btn');
const storyThumbnails = document.querySelectorAll('.story-thumbnail');

// Open story viewer
storyThumbnails.forEach((thumbnail, index) => {
    thumbnail.addEventListener('click', () => {
        currentStory = index;
        openStory();
    });
});

// Close story viewer
closeBtn.addEventListener('click', closeStory);

// Navigation
prevBtn.addEventListener('click', () => {
    if (currentStory > 0) {
        currentStory--;
        showStory(currentStory);
    }
});

nextBtn.addEventListener('click', () => {
    if (currentStory < storyItems.length - 1) {
        currentStory++;
        showStory(currentStory);
    } else {
        closeStory();
    }
});

// Keyboard navigation
document.addEventListener('keydown', (e) => {
    if (!storiesContainer.classList.contains('active')) return;
    
    if (e.key === 'ArrowLeft') {
        prevBtn.click();
    } else if (e.key === 'ArrowRight' || e.key === ' ') {
        nextBtn.click();
    } else if (e.key === 'Escape') {
        closeStory();
    }
});

function openStory() {
    storiesContainer.classList.add('active');
    storiesGrid.style.display = 'none';
    showStory(currentStory);
    startProgress();
}

function closeStory() {
    storiesContainer.classList.remove('active');
    storiesGrid.style.display = 'flex';
    stopProgress();
    currentStory = 0;
}

function showStory(index) {
    storyItems.forEach((item, i) => {
        item.classList.toggle('active', i === index);
    });
    
    stopProgress();
    startProgress();
}

function startProgress() {
    progressFill.style.width = '0%';
    progressFill.style.transition = `width ${storyDuration}ms linear`;
    
    setTimeout(() => {
        progressFill.style.width = '100%';
    }, 10);
    
    progressInterval = setTimeout(() => {
        if (currentStory < storyItems.length - 1) {
            currentStory++;
            showStory(currentStory);
        } else {
            closeStory();
        }
    }, storyDuration);
}

function stopProgress() {
    if (progressInterval) {
        clearTimeout(progressInterval);
    }
    progressFill.style.width = '0%';
}
