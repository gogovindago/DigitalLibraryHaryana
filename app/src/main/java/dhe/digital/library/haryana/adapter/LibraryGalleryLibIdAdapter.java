package dhe.digital.library.haryana.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.DraweeView;

import java.util.ArrayList;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.models.LibraryGalleryResponse;

public class LibraryGalleryLibIdAdapter extends RecyclerView.Adapter<LibraryGalleryLibIdAdapter.ViewHolder> {

    ArrayList<LibraryGalleryResponse.Datum> mValues = new ArrayList<LibraryGalleryResponse.Datum>();

    Context mContext;
    protected ItemListener mListener;
    public int currposition;

    public LibraryGalleryLibIdAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int currposition;
        public TextView textViewTitle, textViewAuthor, txtpublisher, textViewquantity, txtserialno;
        public DraweeView imageView;
        public RelativeLayout ll;
        LibraryGalleryResponse.Datum item;

        public ViewHolder(View v) {

            super(v);

            //  v.setOnClickListener(this);

            imageView =  v.findViewById(R.id.imageView);
            textViewTitle = (TextView) v.findViewById(R.id.textViewTitle);

            ll = (RelativeLayout) v.findViewById(R.id.ll);

            imageView.setOnClickListener(this);

        }

        public void setData(LibraryGalleryResponse.Datum item, int currposition) {
            this.item = item;
            this.currposition = currposition;


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {



             /*   Glide.with(mContext)
                        .load(item.getImageURL()) // image url
                        .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                        .error(R.mipmap.ic_launcher_round)  // any image in case of error
                        .override(140, 140) // resizing
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);*/

                imageView.setImageURI(Uri.parse(item.getImageURL()));


                textViewTitle.setText(Html.fromHtml("<strong> </strong>" + item.getPhotoTitle(), Html.FROM_HTML_MODE_COMPACT));

            } else {

                textViewTitle.setText(Html.fromHtml("<strong></strong>" + item.getPhotoTitle()));


               /* Glide.with(mContext)
                        .load(item.getImageURL()) // image url
                        .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                        .error(R.mipmap.ic_launcher_round)  // any image in case of error
                        .override(140, 140) // resizing
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);*/

            }


        }


        @Override
        public void onClick(View view) {


//            if (mListener != null) {
//                mListener.onItemClick(item, currposition);



            switch (view.getId()) {

                case R.id.imageView:

                    if (mListener != null) {

                        mListener.onItemClick(item, currposition, "bookdetail");


                    }
                    break;


            }

        }
    }

    @Override
    public LibraryGalleryLibIdAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_libgallery, parent, false);

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
        void onItemClick(LibraryGalleryResponse.Datum item, int currposition, String type);
    }
}