package dhe.digital.library.haryana.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.models.LibraryEventsActivitieResponse;

public class LibraryEventsandActivitiesLibIdAdapter extends RecyclerView.Adapter<LibraryEventsandActivitiesLibIdAdapter.ViewHolder> {

    ArrayList<LibraryEventsActivitieResponse.Datum> mValues = new ArrayList<LibraryEventsActivitieResponse.Datum>();

    Context mContext;
    protected ItemListener mListener;
    public int currposition;

    public LibraryEventsandActivitiesLibIdAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int currposition;
        public TextView textViewTitle, textViewAuthor, txtpublisher, textViewquantity, txtserialno;
        public ImageView imageView;
        public RelativeLayout ll;
        LibraryEventsActivitieResponse.Datum item;

        public ViewHolder(View v) {

            super(v);

            //  v.setOnClickListener(this);

            imageView = (ImageView) v.findViewById(R.id.imageView);
            textViewTitle = (TextView) v.findViewById(R.id.textViewTitle);
            textViewAuthor = (TextView) v.findViewById(R.id.textViewAuthor);
            txtpublisher = (TextView) v.findViewById(R.id.txtpublisher);
            textViewquantity = v.findViewById(R.id.textViewquantity);
            txtserialno = (TextView) v.findViewById(R.id.txtserialno);
            ll = (RelativeLayout) v.findViewById(R.id.ll);

            textViewquantity.setOnClickListener(this);

        }

        public void setData(LibraryEventsActivitieResponse.Datum item, int currposition) {
            this.item = item;
            this.currposition = currposition;


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


                Glide.with(mContext)
                        .load(item.getImageURL()) // image url
                        .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                        .error(R.mipmap.ic_launcher_round)  // any image in case of error
                        .override(140, 140) // resizing
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);


                textViewTitle.setText(Html.fromHtml("<strong>Events/Activities Title:-<br> </strong>" + item.getEventTitle(), Html.FROM_HTML_MODE_COMPACT));
                txtpublisher.setText(Html.fromHtml("<strong> Events/Activities Date:-<br> </strong>" + item.getEventDate(), Html.FROM_HTML_MODE_COMPACT));
                textViewAuthor.setText(Html.fromHtml("<strong>Events/Activities Detail:-<br> </strong>" + item.getEventDetails(), Html.FROM_HTML_MODE_COMPACT));
                txtserialno.setText(Html.fromHtml("<strong></strong>" + item.getSrno(), Html.FROM_HTML_MODE_COMPACT));

            } else {

                textViewTitle.setText(Html.fromHtml("<strong>Events/Activities Title:-<br></strong>" + item.getEventTitle()));

                txtpublisher.setText(Html.fromHtml("<strong> Events/Activities Date:- <br> </strong>" + item.getEventDate()));
                textViewAuthor.setText(Html.fromHtml("<strong>Events/Activities Detail:-<br></strong>" + item.getEventDetails()));
                txtserialno.setText(Html.fromHtml("<strong></strong>" + item.getSrno()));

                Glide.with(mContext)
                        .load(item.getImageURL()) // image url
                        .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                        .error(R.mipmap.ic_launcher_round)  // any image in case of error
                        .override(140, 140) // resizing
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);

            }

        }


        @Override
        public void onClick(View view) {


//            if (mListener != null) {
//                mListener.onItemClick(item, currposition);


            switch (view.getId()) {

                case R.id.textViewquantity:

                    if (mListener != null) {

                        mListener.onItemClick(item, currposition, "bookdetail");


                    }
                    break;


            }
        }
    }

    @Override
    public LibraryEventsandActivitiesLibIdAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_lib_activity_events, parent, false);

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
        void onItemClick(LibraryEventsActivitieResponse.Datum item, int currposition, String type);
    }
}