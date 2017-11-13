package game.hangman.asynctask;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import game.hangman.logic.GameLogic;


public class AsyncTaskWords extends AsyncTask<String, String, List<String>> {


    private GameLogic game = GameLogic.getInstance();


    @Override
    protected List<String> doInBackground(String... strings) {
        List<String> words = new ArrayList<>();

        try {
            String rssdata = game.hentUrl("https://www.nytimes.com/");
            rssdata = rssdata.replaceAll("<.+?>", " ").toLowerCase().replaceAll("[^a-zæøå]", " ");
            words.addAll(new HashSet<>(Arrays.asList(rssdata.split(" "))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return words;
    }


    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute(strings);
        game.setData(strings);
        game.nulstil();

        for (String word : strings) {
            System.out.println("word: " + word);
        }

    }
}
