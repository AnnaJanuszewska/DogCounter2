package com.example.michal.dogcounter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String SCORE_PREFERENCE_NAME = "scores";

    private static final String DOGS_PREFS_KEY = "counter";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        Button addButton = findViewById(R.id.addDog);
        Button resetButton = findViewById(R.id.reset);

        final TextView counterView = findViewById(R.id.counter);

        final SharedPreferences scorePreferences = getSharedPreferences(SCORE_PREFERENCE_NAME, 0);

        counterView.setText(String.valueOf(scorePreferences.getInt(DOGS_PREFS_KEY, 0)));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter = scorePreferences.getInt(DOGS_PREFS_KEY, 0);
                counter += 1;

                SharedPreferences.Editor editor = scorePreferences.edit();
                editor.putInt(DOGS_PREFS_KEY, counter);
                editor.apply();

                counterView.setText(String.valueOf(counter));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences(SCORE_PREFERENCE_NAME, 0)
                        .edit()
                        .clear() //Each call here returns an Editor so we can chain the calls like this
                        .apply();

                counterView.setText("0");
            }
        });

    }
}