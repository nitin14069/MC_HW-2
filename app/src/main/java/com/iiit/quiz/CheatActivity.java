package com.iiit.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CheatActivity extends AppCompatActivity {

    TextView cheat;
    Button cheat_Button;
    Boolean access_Cheat;
    Boolean answer;
    String cheating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        access_Cheat=false;
        answer=getIntent().getBooleanExtra(getString(R.string.access),false);
        if(answer==true)
        {
            cheating=getString(R.string.yes_cheat_answer);
        }
        else
        {
            cheating=getString(R.string.no_cheat_answer);
        }


        cheat=(TextView)findViewById(R.id.Cheat_Text);
        cheat_Button=(Button)findViewById(R.id.ask_Cheat);

        if(savedInstanceState==null)
        {

        }
        else
        {
            //Toast.makeText(this,"yo"+savedInstanceState.getBoolean(getString(R.string.saved_state)),2000).show();
            if(savedInstanceState.getBoolean(getString(R.string.saved_state))==true)
            {
                access_Cheat=true;
                cheat.setText(cheating);
                cheat_Button.setVisibility(View.INVISIBLE);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(getString(R.string.access), true);
                setResult(Activity.RESULT_OK, returnIntent);
            }
            else
            {
                access_Cheat=false;
                Intent returnIntent = new Intent();
                returnIntent.putExtra(getString(R.string.access), false);
                setResult(Activity.RESULT_OK, returnIntent);
            }

        }
        cheat_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                access_Cheat = true;
                cheat.setText(cheating);
                cheat_Button.setVisibility(View.INVISIBLE);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(getString(R.string.access), access_Cheat);
                setResult(Activity.RESULT_OK, returnIntent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(getString(R.string.saved_state), access_Cheat);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //access_Cheat=false;
        // finish();

    }

}
