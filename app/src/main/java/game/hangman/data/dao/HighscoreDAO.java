package game.hangman.data.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.hangman.data.dto.HighscoreDTO;

public class HighscoreDAO implements IHighscoreDAO {

    private static HighscoreDAO instance;
    private List<HighscoreDTO> highscoreList = new ArrayList<>();

    private HighscoreDAO() {

    }

    public static synchronized HighscoreDAO getInstance() {
        if (instance == null) {
            instance = new HighscoreDAO();
            return instance;
        } else {
            return instance;
        }

    }


    @Override
    public void add(HighscoreDTO dto) throws DALExceptions {
        highscoreList.add(dto);
        Collections.sort(highscoreList);


    }

    @Override
    public void read(Context context) throws DALExceptions {
        SharedPreferences preferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        String json = preferences.getString("key", null);

        Gson read = new Gson();
        Type type = new TypeToken<ArrayList<HighscoreDTO>>() {
        }.getType();

        highscoreList = (ArrayList<HighscoreDTO>) read.fromJson(json, type);

        if (highscoreList == null) {
            highscoreList = new ArrayList<>();
            save(context);
        }

    }

    @Override
    public void update(HighscoreDTO dto) throws DALExceptions {



    }

    @Override
    public void delete(HighscoreDTO dto) throws DALExceptions {

    }

    @Override
    public void save(Context context) throws DALExceptions {
        Gson save = new Gson();

        SharedPreferences preferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String json = save.toJson(highscoreList);
        editor.putString("key", json);

        editor.apply();

    }

    @Override
    public List<HighscoreDTO> getList() throws DALExceptions {
        return highscoreList;
    }

}
