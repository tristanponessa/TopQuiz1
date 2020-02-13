package com.example.TopQuiz.controller;

import com.example.TopQuiz.model.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.TopQuiz.R;


public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;

    private UserInfo player1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//init the layout in R

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);

        mPlayButton.setEnabled(false);


        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_game_activity();
            }
        });
    }

    private void start_game_activity() {
        Intent gameActivity = send_data_to_GameActivity();
        startActivityForResult(gameActivity, Ids.GAME_ACTIVITY_REQUEST_CODE);

        System.out.println("SE from main" + this.player1.getName());
        System.out.println("SE from main" + this.player1.getScore());
    }

    private void retreive_data_from_GameActivity(int requestCode, int resultCode, Intent game_intent) {

        if (Ids.GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {

            Bundle extras = game_intent.getExtras();
            player1 = (UserInfo) extras.get(Ids.BUNDLE_EXTRA_USER);

            System.out.println("RE from game" + this.player1.getName());
            System.out.println("RE from game" + this.player1.getScore());
        }
    }

    private Intent send_data_to_GameActivity() {

        player1 = new UserInfo(mNameInput.getText().toString(), 0);

        Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
        gameActivity.putExtra(Ids.BUNDLE_EXTRA_USER, player1);
        return gameActivity;
    }

    //runs once game activity closes with finish()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        retreive_data_from_GameActivity(requestCode, resultCode, data);
    }
}
