package game.hangman.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import game.hangman.R;
import game.hangman.asynctask.AsyncTaskWords;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button play, howToPlay, highscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTaskWords object = new AsyncTaskWords();
        object.execute();

        play = findViewById(R.id.play);
        howToPlay = findViewById(R.id.howToPlay);
        highscore = findViewById(R.id.highscore);

        play.setOnClickListener(this);
        howToPlay.setOnClickListener(this);
        highscore.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                Intent intent_game = new Intent(this, GameActivity.class);
                startActivity(intent_game);
                break;
            case R.id.howToPlay:
                Intent intent_howToPlay = new Intent(this, HowToPlayActivity.class);
                startActivity(intent_howToPlay);
                break;
            case R.id.highscore:
                Intent intent_highscore = new Intent(this, HighscoreActivity.class);
                startActivity(intent_highscore);
                break;
        }

    }

}
