package game.hangman.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import game.hangman.R;

public class HowToPlayActivity extends AppCompatActivity implements View.OnClickListener {


    private Button play_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);


        play_2 = findViewById(R.id.play_2);
        play_2.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_2:
                Intent intent_playMenu = new Intent(this, GameActivity.class);
                startActivity(intent_playMenu);
        }

    }
}
