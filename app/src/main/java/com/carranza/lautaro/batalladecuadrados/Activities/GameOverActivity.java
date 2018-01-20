package com.carranza.lautaro.batalladecuadrados.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.carranza.lautaro.batalladecuadrados.R;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


        Button reset = (Button) findViewById(R.id.buttonReset);


        reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {

                Intent volver = new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(volver);
            }
        });

    }




}
