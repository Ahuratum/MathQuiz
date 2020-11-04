package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button startButton, answerOne, answerTwo, answerThree, answerFour;
    ProgressBar timerBar;
    TextView question, score, time, bottomMessage;
    int secondsRemaining = 30;

    //Creates a new game object
    Game game = new Game();

    //Creates a new timer object
    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;

            //Updates the number timer to the screen
            time.setText(Integer.toString(secondsRemaining) + "sec");

            //Updates the progress bar to the screen
            timerBar.setProgress(secondsRemaining);
        }

        @Override
        public void onFinish() {

            answerOne.setEnabled(false);
            answerTwo.setEnabled(false);
            answerThree.setEnabled(false);
            answerFour.setEnabled(false);
            bottomMessage.setText("Out of time! You got " + game.getNumberCorrect() + " out of " + game.getTotal() + " correct!");

            bottomMessage.setTextSize(14);

            //Create Handler
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startButton.setVisibility(View.VISIBLE);
                }
            }, 4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        answerOne = findViewById(R.id.answerOne);
        answerTwo = findViewById(R.id.answerTwo);
        answerThree = findViewById(R.id.answerThree);
        answerFour = findViewById(R.id.answerFour);
        timerBar = findViewById(R.id.timer);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        time = findViewById(R.id.time);
        bottomMessage = findViewById(R.id.bottomMessage);

        time.setText("0");
        question.setText("");
        bottomMessage.setText("Press Go!");
        score.setText("0");

        //Sets the button to invisible after the first click
        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button startButton = (Button) v;
                startButton.setVisibility(View.INVISIBLE);
                secondsRemaining = 30;
                game = new Game();
                nextTurn();
                timer.start();

            }
        };

        View.OnClickListener answerButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button answerButtonClicked = (Button) v;

                //Gets the integer value for the specific button that was pressed
                int answerSelected = Integer.parseInt(answerButtonClicked.getText().toString());

                game.checkAnswer(answerSelected);

                score.setText(Integer.toString(game.getScore()) + " pts");
                nextTurn();
            }
        };

        //Creates blank buttons that call to methods for each button
        startButton.setOnClickListener(startButtonClickListener);
        answerOne.setOnClickListener(answerButton);
        answerTwo.setOnClickListener(answerButton);
        answerThree.setOnClickListener(answerButton);
        answerFour.setOnClickListener(answerButton);
    }

    private void nextTurn() {
        game.makeNewQuestion();
        int[] answers = game.getCurrentQuestion().getNumArray();

        //Set answers to buttons
        //Convert answers from integers to strings
        answerOne.setText(Integer.toString(answers[0]));
        answerOne.setTextSize(50);
        answerTwo.setText(Integer.toString(answers[1]));
        answerTwo.setTextSize(50);
        answerThree.setText(Integer.toString(answers[2]));
        answerThree.setTextSize(50);
        answerFour.setText(Integer.toString(answers[3]));
        answerFour.setTextSize(50);

        answerOne.setEnabled(true);
        answerTwo.setEnabled(true);
        answerThree.setEnabled(true);
        answerFour.setEnabled(true);

        //Generate a new question for the user
        //Calls for current question in Game class
        //THEN
        //Calls for question from the Question class
        question.setText(game.getCurrentQuestion().getQuestion());
        question.setTextSize(50);
        bottomMessage.setText(game.getNumberCorrect() + "/" + (game.getTotal() - 1));

    }
}