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
import dhe.digital.library.haryana.models.StaffDetailsResponse;

public class StaffDetailAdapter extends RecyclerView.Adapter<StaffDetailAdapter.ViewHolder> {

    ArrayList<StaffDetailsResponse.Datum> mValues = new ArrayList<StaffDetailsResponse.Datum>();

    Context mContext;
    protected ItemListener mListener;
    public int currposition;

    public StaffDetailAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int currposition;
        public TextView txtStaffNamevalue, txtdesignationvalue, textEmploymentvalue, txtsrno;
        public ImageView imageView;
        public LinearLayout ll;
        StaffDetailsResponse.Datum item;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);

            txtsrno = (TextView) v.findViewById(R.id.txtsrno);
            txtStaffNamevalue = (TextView) v.findViewById(R.id.txtStaffNamevalue);
            txtdesignationvalue = (TextView) v.findViewById(R.id.txtdesignationvalue);
            textEmploymentvalue = (TextView) v.findViewById(R.id.textEmploymentvalue);

            ll = v.findViewById(R.id.ll);

        }

        public void setData(StaffDetailsResponse.Datum item, int currposition) {
            this.item = item;
            this.currposition = currposition;

            txtsrno.setText(String.valueOf(currposition + 1)+".");
            txtStaffNamevalue.setText(item.getStaffName());
            txtdesignationvalue.setText(item.getDesignation());

            textEmploymentvalue.setText(item.getEmploymentType());
           /* if (item.getStaffId() == 1) {
                textEmploymentvalue.setText("Contractual");
            } else if (item.getStaffId() == 2) {
                textEmploymentvalue.setText("Regular");

            } else {
            }*/


        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item, currposition);
            }
        }
    }

    @Override
    public StaffDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.lib_staff_list_row, parent, false);

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
        void onItemClick(StaffDetailsResponse.Datum item, int currposition);
    }
}