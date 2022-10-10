package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonTrue;
    private Button buttonFalse;
    private Button buttonNext;
    private TextView questionTextView;

    private Question[] questions = new Question[] {
            new Question(R.string.q_activity, true),
            new Question(R.string.q_find_resources, false),
            new Question(R.string.q_listener, true),
            new Question(R.string.q_resources, true),
            new Question(R.string.q_version, false)
    };
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTrue = findViewById(R.id.button_true);
        buttonFalse = findViewById(R.id.button_false);
        buttonNext = findViewById(R.id.button_next);
        questionTextView = findViewById(R.id.question_text_view);

        buttonTrue.setOnClickListener(v -> checkAnswerCorrectness(true));
        buttonFalse.setOnClickListener(v -> checkAnswerCorrectness(false));
        buttonNext.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % questions.length;
            setNextQuestion();
        });
        setNextQuestion();
    }

    private void checkAnswerCorrectness(boolean userAnswer) {

        boolean correctAnswer = questions[currentIndex].getTrueAnswer();
        int resultMessageId = 0;
        if (userAnswer == correctAnswer) {
            resultMessageId = R.string.correct_answer;
        } else {
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }
}