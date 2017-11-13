package game.hangman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import game.hangman.R;
import game.hangman.data.dto.HighscoreDTO;


public class ListAdapter extends ArrayAdapter<HighscoreDTO> {

    List<HighscoreDTO> items;

    class ViewHolder {
        TextView name_View, name, highscore_view, highscore;

    }

    public ListAdapter(Context context, int resource, List<HighscoreDTO> items) {
        super(context, resource, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder viewHolder = new ViewHolder();

        HighscoreDTO dto = items.get(position);

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.highscore_item, null);
        }

        viewHolder.name_View = v.findViewById(R.id.name_view);
        viewHolder.name = v.findViewById(R.id.name);
        viewHolder.highscore = v.findViewById(R.id.highscore);
        viewHolder.highscore_view = v.findViewById(R.id.highscore_view);

        viewHolder.name.setText(dto.getName());
        viewHolder.highscore.setText(String.valueOf(dto.getHighscore()));

        return v;
    }

}