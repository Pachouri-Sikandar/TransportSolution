package com.pachouri.transportsolution.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.models.HistoryModel;
import com.pachouri.transportsolution.util.CircularTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ankit on 12/25/16.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private static final CircularTransformation USER_ROUNDED_TRANSFORMATION = new CircularTransformation(200, 2);

    private List<HistoryModel> historyModelList;
    private LayoutInflater layoutInflater;
    private Context context;

    public HistoryAdapter(Context context, List<HistoryModel> historyModelList) {
        this.context = context;
        this.historyModelList = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
        this.historyModelList.addAll(historyModelList);
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_item_row_history, parent, false);
        HistoryViewHolder holder = new HistoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder viewHolder, int position) {
        if (historyModelList.size() > 0) {
            final HistoryModel historyModel = historyModelList.get(position);
            if (historyModel.getImageUrl() != null) {
                Picasso.with(context).load(historyModel.getImageUrl()).error(R.drawable.ic_menu_profile).
                        transform(USER_ROUNDED_TRANSFORMATION).placeholder(R.drawable
                        .ic_menu_profile).into(viewHolder.imageViewUser);
            }
            viewHolder.textViewName.setText(historyModel.getFirstName() + " " + historyModel.getLastName());
            viewHolder.textViewEmail.setText(historyModel.getEmail());
            viewHolder.textViewContactNumber.setText(historyModel.getMobileNumber());
            viewHolder.textViewItemName.setText("Product: " + historyModel.getItem());
            viewHolder.textViewAddress.setText("Address: " + historyModel.getDeliveredAddress());
            viewHolder.textViewDeliveryTime.setText("Delivered on: " + historyModel.getDeliveredTime
                    ());

            String charges = "Delivery charge: " + context.getResources().getString(R.string.txt_rs_symbol,
                    String.valueOf(historyModel.getDeliveryCharges()).trim());
            viewHolder.textViewCharges.setText(charges);

            if (historyModel.getMobileNumber().isEmpty() || historyModel.getMobileNumber() ==
                    null) {
                viewHolder.textViewContactNumber.setVisibility(View.GONE);
            } else {
                viewHolder.textViewContactNumber.setVisibility(View.VISIBLE);
                viewHolder.textViewContactNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openCallDialer(historyModel.getMobileNumber());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return historyModelList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageViewUser)
        protected ImageView imageViewUser;
        @Bind(R.id.textViewUserName)
        protected TextView textViewName;
        @Bind(R.id.textViewEmail)
        protected TextView textViewEmail;
        @Bind(R.id.textViewContactNumber)
        protected TextView textViewContactNumber;
        @Bind(R.id.textViewItemName)
        protected TextView textViewItemName;
        @Bind(R.id.textViewAddress)
        protected TextView textViewAddress;
        @Bind(R.id.textViewDeliveryTime)
        protected TextView textViewDeliveryTime;
        @Bind(R.id.textViewCharges)
        protected TextView textViewCharges;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void openCallDialer(String contactNumber) {
        if (context != null) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactNumber));
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(callIntent);
        }
    }
}
