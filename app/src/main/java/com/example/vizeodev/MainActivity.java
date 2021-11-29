package com.example.vizeodev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    CountDownTimer t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent ikinci=new Intent(this,activity2.class);

        t=new CountDownTimer(2000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish()
            {
                startActivity(ikinci);
            }
        }.start();
    }
}