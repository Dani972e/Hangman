package game.hangman.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import game.hangman.R;
import game.hangman.data.dao.DALExceptions;
import game.hangman.data.dao.HighscoreDAO;
import game.hangman.data.dao.IHighscoreDAO;
import game.hangman.data.dto.HighscoreDTO;
import game.hangman.logic.GameLogic;

public class LostGameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView theCorrectWord, amountOfGuesses, scoreAmount;
    private Button highscore_btn;

    private GameLogic game = GameLogic.getInstance();
    private IHighscoreDAO dao = HighscoreDAO.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_game);

        MediaPlayer lost = MediaPlayer.create(this, R.raw.evil_laugh);
        lost.start();

        theCorrectWord = findViewById(R.id.theCorrectWord);
        highscore_btn = findViewById(R.id.highscore_btn);
        amountOfGuesses = findViewById(R.id.amountOfGuesses);
        scoreAmount = findViewById(R.id.scoreAmount);

        highscore_btn.setOnClickListener(this);
        theCorrectWord.setText(game.getOrdet());
        scoreAmount.setText(Integer.toString(game.getScore()));


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.highscore_btn:
                try {
                    dao.add(new HighscoreDTO(game.getScore(), "Danny"));
                    dao.save(getBaseContext());
                } catch (DALExceptions dalExceptions) {
                    dalExceptions.printStackTrace();
                }
                Intent highscore_menu = new Intent(this, HighscoreActivity.class);
                startActivity(highscore_menu);
                break;

        }

    }

    @Override
    public void onBackPressed() {
        game.nulstil();
        game.nulstilScore();
        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }

}
