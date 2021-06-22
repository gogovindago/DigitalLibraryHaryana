package dhe.digital.library.haryana.adapter;

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
import dhe.digital.library.haryana.ui.activity.MainActivity;
import dhe.digital.library.haryana.utility.CSPreferences;

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
            textViewquantity =  v.findViewById(R.id.textViewquantity);
            txtserialno = (TextView) v.findViewById(R.id.txtserialno);
            ll = (RelativeLayout) v.findViewById(R.id.ll);

            textViewquantity.setOnClickListener(this);

        }

        public void setData(LibraryEventsActivitieResponse.Datum item, int currposition) {
            this.item = item;
            this.currposition = currposition;


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


              //  textViewTitle.setText("Book Title:-\n " + item.getBookTitle());

                Glide.with(mContext)
                        .load(item.getImageURL()) // image url
                        .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                        .error(R.mipmap.ic_launcher_round)  // any image in case of error
                        .override(140, 140) // resizing
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);


              //  textViewAuthor.setText("Events/Activities Title:-\n" + item.getEventTitle());
                txtpublisher.setText("Events/Activities Date:-\n" + item.getEventDate());
                textViewquantity.setText("Events/Activities Detail:-" + String.valueOf(item.getEventDetails()));
                txtserialno.setText("Sr.no. " + String.valueOf(item.getSrno()));



                textViewTitle.setText(Html.fromHtml("<strong>Events/Activities Title:-<br> </strong>"+ item.getEventTitle(), Html.FROM_HTML_MODE_COMPACT));

            } else {

                textViewTitle.setText(Html.fromHtml("<strong>Events/Activities Title:-<br></strong>"+ item.getEventTitle()));

               // textViewTitle.setText("Book Title:-\n " + item.getBookTitle());

                Glide.with(mContext)
                        .load(item.getImageURL()) // image url
                        .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                        .error(R.mipmap.ic_launcher_round)  // any image in case of error
                        .override(140, 140) // resizing
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
                textViewAuthor.setText("Events/Activities Title:-\n" + item.getEventTitle());
                txtpublisher.setText("Events/Activities Date:-\n" + item.getEventDate());
                textViewquantity.setText("Events/Activities Detail:-" + String.valueOf(item.getEventDetails()));
                txtserialno.setText("Sr.no. " + String.valueOf(item.getSrno()));


                // binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Books Available in " + titleOfPage + "</h6>"));
            }

//            textViewTitle.setText("Book Title:-\n " + item.getBookTitle());
//            textViewAuthor.setText("Author:-\n" + item.getAuthor());
//            txtpublisher.setText("Publisher:-\n " + item.getPublishers());
//            textViewquantity.setText("Quantity:-" + String.valueOf(item.getQuantity()));
//            txtserialno.setText("Sr.no. " + String.valueOf(item.getSrno()));

//if (currposition%2==0) {
//    ll.setBackgroundColor(R.drawable.spinner_border);
//}

        }


        @Override
        public void onClick(View view) {



//            if (mListener != null) {
//                mListener.onItemClick(item, currposition);


                switch (view.getId()){

                    case R.id.textViewquantity:

                        if (mListener != null) {

                            mListener.onItemClick(item, currposition,"bookdetail");


                        }
                        break;



            }
        }
    }

    @Override
    public LibraryEventsandActivitiesLibIdAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_books, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        currposition = position;
        holder.setData(mValues.get(position), currposition);

    }


    @Override
    public int getItemCount() {

        return mValues.size();

    }

    public interface ItemListener {
        void onItemClick(LibraryEventsActivitieResponse.Datum item, int currposition,String type);
    }
}