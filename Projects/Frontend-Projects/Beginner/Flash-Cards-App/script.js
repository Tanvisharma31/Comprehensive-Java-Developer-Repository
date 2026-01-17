const flashcards = [
    { question: "What is the capital of France?", answer: "Paris" },
    { question: "What is 2 + 2?", answer: "4" },
    { question: "What is the largest planet in our solar system?", answer: "Jupiter" },
    { question: "What is the chemical symbol for water?", answer: "H2O" },
    { question: "What year did World War II end?", answer: "1945" }
];

let currentIndex = 0;
let score = 0;

const flashcard = document.getElementById('flashcard');
const questionEl = document.getElementById('question');
const answerEl = document.getElementById('answer');
const currentCardEl = document.getElementById('current-card');
const totalCardsEl = document.getElementById('total-cards');
const scoreEl = document.getElementById('score');
const progressFill = document.getElementById('progress-fill');

totalCardsEl.textContent = flashcards.length;
updateCard();

document.getElementById('flip-btn').addEventListener('click', function() {
    flashcard.classList.toggle('flipped');
});

document.getElementById('next-btn').addEventListener('click', function() {
    if (currentIndex < flashcards.length - 1) {
        currentIndex++;
        flashcard.classList.remove('flipped');
        updateCard();
    }
});

document.getElementById('prev-btn').addEventListener('click', function() {
    if (currentIndex > 0) {
        currentIndex--;
        flashcard.classList.remove('flipped');
        updateCard();
    }
});

function updateCard() {
    const card = flashcards[currentIndex];
    questionEl.textContent = card.question;
    answerEl.textContent = card.answer;
    currentCardEl.textContent = currentIndex + 1;
    
    const progress = ((currentIndex + 1) / flashcards.length) * 100;
    progressFill.style.width = progress + '%';
}
