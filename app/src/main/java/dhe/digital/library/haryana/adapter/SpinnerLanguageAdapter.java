package dhe.digital.library.haryana.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.models.LanguageModelData;


public class SpinnerLanguageAdapter extends BaseAdapter {
    Context context;
    int flags[];
    ArrayList<LanguageModelData> countryNames;
    LayoutInflater inflter;

    public SpinnerLanguageAdapter(Context applicationContext, ArrayList<LanguageModelData> countryNames) {
        this.context = applicationContext;
        this.flags = flags;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryNames.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(countryNames.get(i).getImage());
        names.setText(countryNames.get(i).getName());
        return view;
    }
}