package dhe.digital.library.haryana.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.models.GetbooktypeResponse;

public class SpinnerBookTypeAdapter extends BaseAdapter {
    Context context;
    private List<GetbooktypeResponse.Datum> booktypeList = new ArrayList<GetbooktypeResponse.Datum>();

    LayoutInflater inflter;

    public SpinnerBookTypeAdapter(Context applicationContext, List<GetbooktypeResponse.Datum> booktypeList) {
        this.context = applicationContext;
        this.booktypeList = booktypeList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return booktypeList.size();
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
       icon.setImageResource(R.drawable.appcolorlibrary_books_24);
        names.setText(booktypeList.get(i).getBookType());
        return view;
    }
}