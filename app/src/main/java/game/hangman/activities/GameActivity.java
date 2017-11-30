package game.hangman.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import game.hangman.R;
import game.hangman.data.dao.HighscoreDAO;
import game.hangman.data.dao.IHighscoreDAO;
import game.hangman.logic.GameLogic;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private final int btn_amount = 26;
    private ImageView hangman_image;
    private TextView hiddenWord, life, score;
    private GameLogic game = GameLogic.getInstance();
    private IHighscoreDAO dao = HighscoreDAO.getInstance();


    private final Button[] btnArray = new Button[btn_amount];
    private final Integer[] butId = {R.id.btn_a, R.id.btn_b, R.id.btn_c, R.id.btn_d, R.id.btn_e, R.id.btn_f, R.id.btn_g, R.id.btn_h, R.id.btn_i, R.id.btn_j, R.id.btn_k, R.id.btn_l, R.id.btn_m, R.id.btn_n, R.id.btn_o, R.id.btn_p, R.id.btn_q, R.id.btn_r, R.id.btn_s, R.id.btn_t, R.id.btn_u, R.id.btn_v, R.id.btn_w, R.id.btn_x, R.id.btn_y, R.id.btn_z};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        for (int i = 0; i < butId.length; i++) {
            btnArray[i] = findViewById(butId[i]);
            btnArray[i].setOnClickListener(this);
        }

        hangman_image = findViewById(R.id.hangman_image);
        hiddenWord = findViewById(R.id.hiddenWord);
        life = findViewById(R.id.life);
        score = findViewById(R.id.score);

        updateDisplay();
    }

    private void finishGame() {
        if (game.erSpilletVundet()) {
            Intent intentWon = new Intent(this, WonGameActivity.class);
            startActivity(intentWon);
        } else if (game.erSpilletTabt()) {
            Intent intentLost = new Intent(this, LostGameActivity.class);
            startActivity(intentLost);
        }
    }

    private int getLife() {
        return 6 - game.getAntalForkerteBogstaver();
    }

    private void updateDisplay() {


        hiddenWord.setText(game.getSynligtOrd());
        life.setText("Life: " + Integer.toString(getLife()));
        score.setText("Score: " + Integer.toString(game.getScore()));

        switch (game.getAntalForkerteBogstaver()) {
            case 0:
                hangman_image.setImageResource(R.drawable.galge);
                break;
            case 1:
                hangman_image.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                hangman_image.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                hangman_image.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                hangman_image.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                hangman_image.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                hangman_image.setImageResource(R.drawable.forkert6);
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View view) {
        if (view instanceof Button) {
            game.gætBogstav(((Button) view).getText().toString().toLowerCase());

            view.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.fadeout));
            view.setEnabled(false);
            view.setVisibility(View.INVISIBLE);

            updateDisplay();
            finishGame();
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
