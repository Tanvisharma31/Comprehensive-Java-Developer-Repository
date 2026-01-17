class PomodoroTimer {
    constructor() {
        this.mode = 'pomodoro';
        this.timeLeft = 25 * 60; // 25 minutes in seconds
        this.isRunning = false;
        this.interval = null;
        this.pomodoroCount = parseInt(localStorage.getItem('pomodoroCount') || '0');
        this.init();
    }

    init() {
        this.loadSettings();
        this.updateDisplay();
        this.setupEventListeners();
        this.updateStats();
    }

    setupEventListeners() {
        document.querySelectorAll('.mode-btn').forEach(btn => {
            btn.addEventListener('click', (e) => this.setMode(e.target.dataset.mode));
        });
        document.getElementById('startBtn').addEventListener('click', () => this.start());
        document.getElementById('pauseBtn').addEventListener('click', () => this.pause());
        document.getElementById('resetBtn').addEventListener('click', () => this.reset());
        
        ['pomodoroDuration', 'shortBreakDuration', 'longBreakDuration'].forEach(id => {
            document.getElementById(id).addEventListener('change', () => this.saveSettings());
        });
    }

    setMode(mode) {
        this.mode = mode;
        this.pause();
        document.querySelectorAll('.mode-btn').forEach(btn => btn.classList.remove('active'));
        document.querySelector(`[data-mode="${mode}"]`).classList.add('active');
        this.loadTimeForMode();
        this.updateDisplay();
    }

    loadTimeForMode() {
        const durations = {
            pomodoro: parseInt(document.getElementById('pomodoroDuration').value),
            short: parseInt(document.getElementById('shortBreakDuration').value),
            long: parseInt(document.getElementById('longBreakDuration').value)
        };
        this.timeLeft = durations[this.mode === 'pomodoro' ? 'pomodoro' : this.mode === 'short' ? 'short' : 'long'] * 60;
    }

    start() {
        if (!this.isRunning) {
            this.isRunning = true;
            document.getElementById('startBtn').style.display = 'none';
            document.getElementById('pauseBtn').style.display = 'inline-block';
            
            this.interval = setInterval(() => {
                this.timeLeft--;
                this.updateDisplay();
                this.updateProgress();
                
                if (this.timeLeft <= 0) {
                    this.complete();
                }
            }, 1000);
        }
    }

    pause() {
        this.isRunning = false;
        document.getElementById('startBtn').style.display = 'inline-block';
        document.getElementById('pauseBtn').style.display = 'none';
        if (this.interval) {
            clearInterval(this.interval);
        }
    }

    reset() {
        this.pause();
        this.loadTimeForMode();
        this.updateDisplay();
        this.updateProgress();
    }

    complete() {
        this.pause();
        if (this.mode === 'pomodoro') {
            this.pomodoroCount++;
            localStorage.setItem('pomodoroCount', this.pomodoroCount);
            this.updateStats();
            alert('Pomodoro completed! Take a break.');
        } else {
            alert('Break completed! Ready for next pomodoro.');
        }
        this.reset();
    }

    updateDisplay() {
        const minutes = Math.floor(this.timeLeft / 60);
        const seconds = this.timeLeft % 60;
        document.getElementById('time').textContent = 
            `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    }

    updateProgress() {
        const durations = {
            pomodoro: parseInt(document.getElementById('pomodoroDuration').value) * 60,
            short: parseInt(document.getElementById('shortBreakDuration').value) * 60,
            long: parseInt(document.getElementById('longBreakDuration').value) * 60
        };
        const total = durations[this.mode === 'pomodoro' ? 'pomodoro' : this.mode === 'short' ? 'short' : 'long'];
        const remaining = total - this.timeLeft;
        const percentage = (remaining / total) * 100;
        const circumference = 2 * Math.PI * 115; // radius = 115
        const offset = circumference - (percentage / 100) * circumference;
        document.getElementById('progressCircle').style.strokeDashoffset = offset;
    }

    updateStats() {
        document.getElementById('pomodoroCount').textContent = this.pomodoroCount;
    }

    saveSettings() {
        const settings = {
            pomodoro: document.getElementById('pomodoroDuration').value,
            short: document.getElementById('shortBreakDuration').value,
            long: document.getElementById('longBreakDuration').value
        };
        localStorage.setItem('pomodoroSettings', JSON.stringify(settings));
        if (!this.isRunning) {
            this.loadTimeForMode();
            this.updateDisplay();
        }
    }

    loadSettings() {
        const saved = localStorage.getItem('pomodoroSettings');
        if (saved) {
            const settings = JSON.parse(saved);
            document.getElementById('pomodoroDuration').value = settings.pomodoro;
            document.getElementById('shortBreakDuration').value = settings.short;
            document.getElementById('longBreakDuration').value = settings.long;
        }
    }
}

const timer = new PomodoroTimer();
