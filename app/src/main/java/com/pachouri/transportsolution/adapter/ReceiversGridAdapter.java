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
import com.pachouri.transportsolution.models.ReceiverDetailsModel;
import com.pachouri.transportsolution.util.CircularTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ankit on 12/25/16.
 */

public class ReceiversGridAdapter extends RecyclerView.Adapter<ReceiversGridAdapter.ReceiverViewHolder> {

    private static final CircularTransformation USER_ROUNDED_TRANSFORMATION = new CircularTransformation(200, 2);

    private List<ReceiverDetailsModel> receiverDetailsModels;
    private LayoutInflater layoutInflater;
    private Context context;

    public ReceiversGridAdapter(Context context, List<ReceiverDetailsModel> receiverDetailsModels) {
        this.context = context;
        this.receiverDetailsModels = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
        this.receiverDetailsModels.addAll(receiverDetailsModels);
    }

    @Override
    public ReceiverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_item_row_receiver, parent, false);
        ReceiverViewHolder holder = new ReceiverViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ReceiverViewHolder viewHolder, int position) {
        if (receiverDetailsModels.size() > 0) {
            final ReceiverDetailsModel receiverModel = receiverDetailsModels.get(position);
            if (receiverModel.getImageUrl() != null) {
                Picasso.with(context).load(receiverModel.getImageUrl()).error(R.drawable.ic_menu_profile).
                        transform(USER_ROUNDED_TRANSFORMATION).placeholder(R.drawable
                        .ic_menu_profile).into(viewHolder.imageViewUser);
            }
            viewHolder.textViewName.setText(receiverModel.getFirstName() + " " + receiverModel.getLastName());
            viewHolder.textViewFrom.setText(receiverModel.getPlaceFrom() + " to " + receiverModel
                    .getPlaceTo());
            viewHolder.textViewTo.setText(receiverModel.getPlaceTo());
            viewHolder.textViewTime.setText(receiverModel.getLeavingTime());
            viewHolder.textViewCharges.setText(context.getResources().getString(R.string.txt_rs_symbol,
                    String.valueOf(receiverModel.getDeliveryCharges()).trim()) + " Min.");

            if (receiverModel.getMobileNumber().isEmpty() || receiverModel.getMobileNumber() ==
                    null) {
                viewHolder.imageViewContactNumber.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.imageViewContactNumber.setVisibility(View.VISIBLE);
                viewHolder.imageViewContactNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openCallDialer(receiverModel.getMobileNumber());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return receiverDetailsModels.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageViewReceiver)
        protected ImageView imageViewUser;
        @Bind(R.id.textViewReceiverName)
        protected TextView textViewName;
        @Bind(R.id.textViewFrom)
        protected TextView textViewFrom;
        @Bind(R.id.textViewTo)
        protected TextView textViewTo;
        @Bind(R.id.textViewTime)
        protected TextView textViewTime;
        @Bind(R.id.imageViewContactNumber)
        protected ImageView imageViewContactNumber;
        @Bind(R.id.textViewCharges)
        protected TextView textViewCharges;

        public ReceiverViewHolder(View itemView) {
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
