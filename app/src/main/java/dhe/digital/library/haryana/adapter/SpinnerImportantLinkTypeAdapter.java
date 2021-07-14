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
import dhe.digital.library.haryana.models.ImportantLinksTypeResponse;

public class SpinnerImportantLinkTypeAdapter extends BaseAdapter {
    Context context;
    int flags[];
    private List<ImportantLinksTypeResponse.Datum> data = new ArrayList<ImportantLinksTypeResponse.Datum>();
    LayoutInflater inflter;

    public SpinnerImportantLinkTypeAdapter(Context applicationContext, List<ImportantLinksTypeResponse.Datum> data) {
        this.context = applicationContext;
        this.flags = flags;
        this.data = data;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return data.size();
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
        icon.setImageResource(R.drawable.ic_baseline_apps_24);
        names.setText(data.get(i).getLinkType());
        return view;
    }
}