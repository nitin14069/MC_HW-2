package com.iiit.quiz;

import android.content.Intent;
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
    Button Hint;
    Button Cheat;
    Random r = new Random();
    static int number;
    static boolean isprime;
    String saved_number;
    String correct;
    String incorrect;
    String unknown;
    String empty;
    String textDisplay;
    static int hint_Activity=1;
    static int cheat_Activity=2;
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
        Hint = (Button)findViewById(R.id.hintButton);
        Cheat = (Button)findViewById(R.id.cheatButton);
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

        Cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check for prime number for number
                Intent cheat_Intent = new Intent(QuizActivity.this, CheatActivity.class);
                cheat_Intent.putExtra(getString(R.string.access), isprime);
                startActivityForResult(cheat_Intent, cheat_Activity);
            }
        });

        Hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check for prime number for number
                Intent hint_Intent = new Intent(QuizActivity.this,HintActivity.class);
                startActivityForResult(hint_Intent,hint_Activity);
            }
        });

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
                /*
                yes.setVisibility(View.INVISIBLE);
                no.setVisibility(View.INVISIBLE);
                Hint.setVisibility(View.INVISIBLE);
                Cheat.setVisibility(View.INVISIBLE);
                */
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
                /*
                yes.setVisibility(View.INVISIBLE);
                no.setVisibility(View.INVISIBLE);
                Hint.setVisibility(View.INVISIBLE);
                Cheat.setVisibility(View.INVISIBLE);
                */
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==hint_Activity)
        {
            Boolean access;
            if(data==null)
            {
                access=false;
            }
            else
            {
                access=data.getBooleanExtra(getString(R.string.access),false);
            }

            if(access==true)
            {
                Toast.makeText(QuizActivity.this,getString(R.string.yes_string_access_hint),Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(QuizActivity.this,getString(R.string.no_string_access_hint),Toast.LENGTH_SHORT).show();
            }

        }
        if(requestCode==cheat_Activity)
        {
            Boolean access;
            if(data==null)
            {
                access=false;
            }
            else
            {
                access=data.getBooleanExtra(getString(R.string.access),false);
            }

            if(access==true)
            {
                Toast.makeText(QuizActivity.this,getString(R.string.yes_string_access_cheat),Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(QuizActivity.this,getString(R.string.no_string_access_cheat),Toast.LENGTH_SHORT).show();
            }

        }
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
