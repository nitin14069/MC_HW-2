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

public class HintActivity extends AppCompatActivity {

    TextView hint;
    Button hint_Button;
    Boolean access_Hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        access_Hint=false;



        hint=(TextView)findViewById(R.id.Hint_Text);
        hint_Button=(Button)findViewById(R.id.ask_Hint);

        if(savedInstanceState==null)
        {

        }
        else
        {
            if(savedInstanceState.getBoolean(getString(R.string.saved_state))==true)
            {
                access_Hint=true;
                hint.setText(getString(R.string.give_hint));
                hint_Button.setVisibility(View.INVISIBLE);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(getString(R.string.access), true);
                setResult(Activity.RESULT_OK, returnIntent);
            }
            else
            {
                access_Hint=false;
                Intent returnIntent = new Intent();
                returnIntent.putExtra(getString(R.string.access), false);
                setResult(Activity.RESULT_OK, returnIntent);
            }

        }
        hint_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                access_Hint=true;
                hint.setText(getString(R.string.give_hint));
                hint_Button.setVisibility(View.INVISIBLE);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(getString(R.string.access), access_Hint);
                setResult(Activity.RESULT_OK, returnIntent);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(getString(R.string.saved_state), access_Hint);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //access_Hint=false;
       // finish();

    }
}
