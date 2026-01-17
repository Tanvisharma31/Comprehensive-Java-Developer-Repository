const questions = [
    {
        question: "What is the default value of a boolean variable in Java?",
        options: ["true", "false", "null", "0"],
        correct: 1
    },
    {
        question: "Which keyword is used to inherit a class in Java?",
        options: ["extends", "implements", "inherits", "super"],
        correct: 0
    },
    {
        question: "What is the size of int in Java?",
        options: ["16 bits", "32 bits", "64 bits", "8 bits"],
        correct: 1
    },
    {
        question: "Which method is used to start a thread in Java?",
        options: ["run()", "start()", "execute()", "begin()"],
        correct: 1
    },
    {
        question: "What is Spring Boot?",
        options: ["A database", "A framework", "A language", "An IDE"],
        correct: 1
    }
];

let currentQuestion = 0;
let score = 0;

document.getElementById('startBtn').addEventListener('click', startQuiz);
document.getElementById('nextBtn').addEventListener('click', nextQuestion);
document.getElementById('restartBtn').addEventListener('click', () => {
    currentQuestion = 0;
    score = 0;
    startQuiz();
});

function startQuiz() {
    document.getElementById('startScreen').classList.add('hidden');
    document.getElementById('resultScreen').classList.add('hidden');
    document.getElementById('quizScreen').classList.remove('hidden');
    showQuestion();
}

function showQuestion() {
    const question = questions[currentQuestion];
    document.getElementById('question').textContent = question.question;
    document.getElementById('questionNum').textContent = currentQuestion + 1;
    document.getElementById('totalQuestions').textContent = questions.length;
    
    const optionsDiv = document.getElementById('options');
    optionsDiv.innerHTML = '';
    
    question.options.forEach((option, index) => {
        const button = document.createElement('button');
        button.className = 'option-btn';
        button.textContent = option;
        button.onclick = () => selectOption(index);
        optionsDiv.appendChild(button);
    });
    
    document.getElementById('nextBtn').classList.add('hidden');
    updateProgress();
}

function selectOption(selectedIndex) {
    const question = questions[currentQuestion];
    const buttons = document.querySelectorAll('.option-btn');
    
    buttons.forEach((btn, index) => {
        btn.disabled = true;
        if (index === question.correct) {
            btn.classList.add('correct');
        } else if (index === selectedIndex && index !== question.correct) {
            btn.classList.add('wrong');
        }
    });
    
    if (selectedIndex === question.correct) {
        score++;
    }
    
    document.getElementById('nextBtn').classList.remove('hidden');
}

function nextQuestion() {
    currentQuestion++;
    if (currentQuestion < questions.length) {
        showQuestion();
    } else {
        showResult();
    }
}

function showResult() {
    document.getElementById('quizScreen').classList.add('hidden');
    document.getElementById('resultScreen').classList.remove('hidden');
    document.getElementById('score').textContent = score;
    document.getElementById('maxScore').textContent = questions.length;
    const percentage = (score / questions.length) * 100;
    document.getElementById('percentage').textContent = `${percentage.toFixed(0)}%`;
}

function updateProgress() {
    const progress = ((currentQuestion + 1) / questions.length) * 100;
    document.getElementById('progress').style.width = progress + '%';
}
