package com.example.TopQuiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.TopQuiz.R;

import java.util.ArrayList;
import java.util.Map;

public class GameActivity extends AppCompatActivity {

    private int score;//retreive from userinfo class
    private int name;

    private ArrayList<Button> buttons;
    private Map<String, TextView> labels;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        System.out.println("HI");
    }




}
