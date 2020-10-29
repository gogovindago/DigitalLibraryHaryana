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
import dhe.digital.library.haryana.models.LibraryTypeAndCoutResponse;
public class SpinnerLibraryTypeAdapter extends BaseAdapter {
    Context context;
    int flags[];
    private List<LibraryTypeAndCoutResponse.Datum> allCollegeByDistrictID = new ArrayList<LibraryTypeAndCoutResponse.Datum>();
    LayoutInflater inflter;

    public SpinnerLibraryTypeAdapter(Context applicationContext, List<LibraryTypeAndCoutResponse.Datum> allCollegeByDistrictID) {
        this.context = applicationContext;
        this.flags = flags;
        this.allCollegeByDistrictID = allCollegeByDistrictID;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return allCollegeByDistrictID.size();
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
        icon.setImageResource(R.drawable.ic_place);
        names.setText(allCollegeByDistrictID.get(i).getLibraryType());
        return view;
    }
}