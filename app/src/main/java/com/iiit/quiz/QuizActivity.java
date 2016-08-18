package com.iiit.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {


    TextView tv;
    Button yes;
    Button no;
    Button next;
    Random r = new Random();
    static int number;
    static boolean isprime;
    String saved_number;
    String correct;
    String incorrect;
    String unknown;
    String empty;
    String textDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        saved_number = getString(R.string.saved_number);
        correct = getString(R.string.correct_string);
        incorrect = getString(R.string.incorrect_string);
        unknown = getString(R.string.not_known);
        empty = getString(R.string.blank);
        tv = (TextView)findViewById(R.id.question);
        yes = (Button)findViewById(R.id.yesButton);
        no = (Button)findViewById(R.id.noButton);
        next = (Button)findViewById(R.id.nextButton);
        if(savedInstanceState==null)
        {
            number = 1 + r.nextInt(1000);
        }
        else
        {
            number=savedInstanceState.getInt(saved_number);
        }

        CheckPrime();
        textDisplay = number+empty;
        tv.setText(textDisplay);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check for prime number for number
                String disp; //correct or incorrecttextDisplay = number+empty;
                if (isprime) {
                    //correct
                    disp = correct;
                } else {
                    disp = incorrect;
                }
                if (number == 1) {
                    disp = unknown;
                }

                Toast.makeText(QuizActivity.this, disp, Toast.LENGTH_SHORT).show();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check for prime number for number
                String disp; //correct or incorrect
                if(!isprime)
                {
                    //correct
                    disp=correct;
                }
                else
                {
                    disp=incorrect;
                }
                if(number==1)
                {
                    disp = unknown;
                }
                Toast.makeText(QuizActivity.this,disp,Toast.LENGTH_SHORT).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = r.nextInt(1001);
                CheckPrime();
                textDisplay = number+empty;
                tv.setText(textDisplay);

            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(saved_number, number);
    }


    private void CheckPrime()
    {
        boolean flag=true;
        int i = 2;
        double check = Math.sqrt(number);
        for(;i<=(int)check;i++)
        {
            if(number%i==0)
            {
                flag=false;
                break;
            }
        }
        isprime = flag;
    }
}
