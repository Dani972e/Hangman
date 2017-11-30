package game.hangman.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import game.hangman.R;
import game.hangman.adapter.ListAdapter;
import game.hangman.data.dao.DALExceptions;
import game.hangman.data.dao.HighscoreDAO;
import game.hangman.data.dao.IHighscoreDAO;
import game.hangman.logic.GameLogic;

public class HighscoreActivity extends AppCompatActivity implements View.OnClickListener {

    private GameLogic game = GameLogic.getInstance();
    private IHighscoreDAO highscoreDAO = HighscoreDAO.getInstance();

    private ListView listView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);


        try {
            highscoreDAO.read(getBaseContext());
        } catch (DALExceptions dalExceptions) {
            dalExceptions.printStackTrace();
        }

        try {
            adapter = new ListAdapter(getBaseContext(), R.layout.highscore_item, highscoreDAO.getList());
        } catch (DALExceptions dalExceptions) {
            dalExceptions.printStackTrace();
        }

        listView = findViewById(R.id.highscore_list);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
        game.nulstil();
        game.nulstilScore();
        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }
}
