package game.hangman.activities;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import game.hangman.R;
import game.hangman.data.dao.HighscoreDAO;
import game.hangman.data.dao.IHighscoreDAO;
import game.hangman.logic.GameLogic;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class WonGameActivity extends AppCompatActivity implements View.OnClickListener {
    private Button continue_btn;
    private TextView won_view, guesses_view, score_view, amountOfGuesses, scoreAmount;

    private GameLogic game = GameLogic.getInstance();
    private IHighscoreDAO dao = HighscoreDAO.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won_game);


        MediaPlayer winner = MediaPlayer.create(this, R.raw.tada);
        winner.start();

        //took this konfetti code from: https://github.com/DanielMartinus/Konfetti
        final KonfettiView konfettiView = (KonfettiView) findViewById(R.id.viewKonfetti);
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.BLUE, Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .stream(300, 5000L);

        continue_btn = (Button) findViewById(R.id.continue_btn);
        won_view = (TextView) findViewById(R.id.won_view);
        guesses_view = (TextView) findViewById(R.id.guesses_view);
        score_view = (TextView) findViewById(R.id.score_view);
        amountOfGuesses = (TextView) findViewById(R.id.amountOfGuesses);
        scoreAmount = (TextView) findViewById(R.id.scoreAmount);


        continue_btn.setOnClickListener(this);

        scoreAmount.setText(Integer.toString(game.getScore()));
        amountOfGuesses.setText(Integer.toString(game.getTotalGuesses()));
        game.nulstil();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.continue_btn:
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
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
