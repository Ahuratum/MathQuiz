package com.example.mathquiz;

import java.util.Random;

public class Question {

    private int firstNumber;
    private int secondNumber;
    private int answer;
    private int upperLimit;

    //Array for the four possible answers the user can choose from
    //These will be applied to the answerButtons for the user
    private int[] answerArray;

    private int answerPosition;

    //Question converted to string for user to see
    private String question;

    //Initializing constructor to generate new question
    public Question(int upperLimit) {

        this.upperLimit = upperLimit;
        Random random = new Random();

        this.firstNumber = random.nextInt(upperLimit);
        this.secondNumber = random.nextInt(upperLimit);

        //Generates the question for the user
        this.answer = this.firstNumber + this.secondNumber;

        //Converts the question into a string to be placed into textview
        this.question = firstNumber + " + " + secondNumber;

        this.answerPosition = random.nextInt(4);
        this.answerArray = new int[] {0, 1, 2, 3};


        this.answerArray[0] = answer + 1;
        this.answerArray[1] = answer + 2;
        this.answerArray[2] = answer - 1;
        this.answerArray[3] = answer - 2;

        this.answerArray = shuffleArray(this.answerArray);

        answerArray[answerPosition] = answer;
    }

    private int[] shuffleArray(int[] array) {
        int index, temp;
        Random random = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getNumArray() {
        return answerArray;
    }

    public void setNumArray(int[] numArray) {
        this.answerArray = numArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
