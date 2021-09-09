package dhe.digital.library.haryana.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.models.LibraryTypeByIdResponse;

public class ViewLibByIdlItemsAdapter extends RecyclerView.Adapter<ViewLibByIdlItemsAdapter.ViewHolder> {

    List<LibraryTypeByIdResponse.Datum> mValues = new ArrayList<LibraryTypeByIdResponse.Datum>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;
    String imptypeId;

    public ViewLibByIdlItemsAdapter(Context context, List values, ItemListener itemListener, String i) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
        imptypeId = i;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public SimpleDraweeView imageView;
        public RelativeLayout relativeLayout;
        LibraryTypeByIdResponse.Datum item;
        public int currposition;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

        }

        public void setData(LibraryTypeByIdResponse.Datum item, int currposition) {
            this.currposition = currposition;
            this.item = item;
            textView.setText(item.getDescription());
            imageView.setImageURI(item.getFilePath());
            // relativeLayout.setBackgroundColor(Color.parseColor(item.color));

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item, currposition);
            }
        }
    }

    @Override
    public ViewLibByIdlItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (imptypeId.equalsIgnoreCase("6")) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_view_link_item_row, parent, false);

        } else {

            view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_itemall, parent, false);

        }


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        currposition = position;
        holder.setData(mValues.get(position), currposition);

    }


    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(LibraryTypeByIdResponse.Datum item, int currposition);
    }
}