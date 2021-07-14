package dhe.digital.library.haryana.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.models.CommitteeDetailsResponse;

public class CommitteeDetailAdapter extends RecyclerView.Adapter<CommitteeDetailAdapter.ViewHolder> {

    ArrayList<CommitteeDetailsResponse.Datum> mValues = new ArrayList<CommitteeDetailsResponse.Datum>();

    Context mContext;
    protected ItemListener mListener;
    public int currposition;

    public CommitteeDetailAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int currposition;
        public TextView txtMemberNamevalue, txtdesignationvalue, txtsrno;
        public ImageView imageView;
        public LinearLayout ll;
        CommitteeDetailsResponse.Datum item;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);

            txtsrno = (TextView) v.findViewById(R.id.txtsrno);
            txtMemberNamevalue = (TextView) v.findViewById(R.id.txtMemberNamevalue);
            txtdesignationvalue = (TextView) v.findViewById(R.id.txtdesignationvalue);

            ll = v.findViewById(R.id.ll);

        }

        public void setData(CommitteeDetailsResponse.Datum item, int currposition) {
            this.item = item;
            this.currposition = currposition;

            txtsrno.setText( String.valueOf(currposition+1)+".");
            txtMemberNamevalue.setText( item.getMemberName());
            txtdesignationvalue.setText(item.getDesignation());


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


                txtMemberNamevalue.setText( item.getMemberName());
                txtdesignationvalue.setText(item.getDesignation());


//
//                textViewTitle.setText(Html.fromHtml("<strong>&#128196; Book Title:-<br></strong>" + item.getBookTitle(), Html.FROM_HTML_MODE_COMPACT));
//
//                txtlinname.setText(Html.fromHtml("<strong>&#127979-</strong>" + item.getLibraryName(), Html.FROM_HTML_MODE_COMPACT));

            } else {

//                textViewTitle.setText(Html.fromHtml("<strong> &#128196;Book Title:-<br></strong>" + item.getBookTitle()));
//
//                txtlinname.setText(Html.fromHtml("<strong>&#127979</strong>" + item.getLibraryName()));


                txtMemberNamevalue.setText( item.getMemberName());
                txtdesignationvalue.setText(item.getDesignation());


            }

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item, currposition);
            }
        }
    }

    @Override
    public CommitteeDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.lib_committee_list_row, parent, false);

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
        void onItemClick(CommitteeDetailsResponse.Datum item, int currposition);
    }
}