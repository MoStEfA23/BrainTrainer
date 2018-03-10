package com.example.mostefa.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button mGoButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int mCorrectAnswerLocation;
    TextView mResultTextView;
    int mScore = 0;
    int mNumberOfQuestions = 0;
    TextView mScoreTextView;
    TextView mSumTextView;
    TextView mTimerTextView;

    Button mButton0;
    Button mButton1;
    Button mButton2;
    Button mButton3;

    Button mPlayAgainButton;

    ConstraintLayout mGameLayout;

    public void start(View view)
    {
        mGoButton.setVisibility(View.INVISIBLE);
        playAgain(view);
        mGameLayout.setVisibility(View.VISIBLE);
    }

    public  void playAgain(View view)
    {
        mScore = 0;
        mNumberOfQuestions = 0;
        mTimerTextView.setText("30s");
        mScoreTextView.setText(Integer.toString(mScore)+" / "+Integer.toString(mNumberOfQuestions));
        mPlayAgainButton.setVisibility(View.INVISIBLE);
        mResultTextView.setText("");
        changeQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTimerTextView.setText(String.valueOf(millisUntilFinished/1000)+ "s");
            }

            @Override
            public void onFinish() {
                mResultTextView.setText("Game over");
                mPlayAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view)
    {
        if(Integer.toString(mCorrectAnswerLocation).equals(view.getTag().toString()))
        {
            mResultTextView.setText("Correct !");
            mScore++;
        }
        else
        {
            mResultTextView.setText("Wrong !");
        }
        mNumberOfQuestions++;
        mScoreTextView.setText(Integer.toString(mScore)+" / "+Integer.toString(mNumberOfQuestions));

        changeQuestion();
    }

    public void changeQuestion()
    {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        mSumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        mCorrectAnswerLocation = random.nextInt(4);

        answers.clear();
        for(int i=0; i < 4; i++)
        {
            if(i == mCorrectAnswerLocation)
            {
                answers.add(a+b);
            }
            else
            {
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer == (a+b))
                {
                    wrongAnswer = random.nextInt(41);
                }

                answers.add(random.nextInt(41));
            }
        }

        mButton0.setText(Integer.toString(answers.get(0)));
        mButton1.setText(Integer.toString(answers.get(1)));
        mButton2.setText(Integer.toString(answers.get(2)));
        mButton3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoButton = (Button)findViewById(R.id.goButton);
        mGoButton.setVisibility(View.VISIBLE);
        mGameLayout = (ConstraintLayout)findViewById(R.id.gameLayout);
        mGameLayout.setVisibility(View.INVISIBLE);

        mSumTextView = findViewById(R.id.sumTextView);
        mResultTextView = (TextView)findViewById(R.id.resultTextView);
        mScoreTextView = (TextView)findViewById(R.id.scoreTextView);

        mButton0 = (Button) findViewById(R.id.button0);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);

        mPlayAgainButton = (Button) findViewById(R.id.playAgainButton);

        mTimerTextView = (TextView) findViewById(R.id.timerTextView);

    }
}
