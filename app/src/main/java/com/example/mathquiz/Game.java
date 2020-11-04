package com.example.mathquiz;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Question> questions;
    private int numberCorrect, numberIncorrect, total, score;
    private Question currentQuestion;

    //Initializing Constructor to start a new game
    public Game() {
        numberCorrect = 0;
        numberIncorrect = 0;
        total = 0;
        score = 0;
        questions = new ArrayList<Question>();
        currentQuestion = new Question(10);
    }

    //Generates a new question for the user
    //Updates the difficulty of the question each iteration
    //Tracks the total number of questions the user has been asked
    public void makeNewQuestion() {
        currentQuestion = new Question(total * 2 + 10);
        questions.add(currentQuestion);
        total++;
    }

    //Checks the user answer
    //Updates the number of correct and incorrect answers
    //Updates the point system
    public boolean checkAnswer(int answer) {
        boolean isCorrect;

        if (currentQuestion.getAnswer() == answer) {
            numberCorrect++;
            isCorrect = true;
        }
        else {
            numberIncorrect++;
            isCorrect = false;
        }
        score = numberCorrect * 10 - numberIncorrect * 10;
        return isCorrect;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorrect() {
        return numberIncorrect;
    }

    public void setNumberIncorrect(int numberIncorrect) {
        this.numberIncorrect = numberIncorrect;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}
