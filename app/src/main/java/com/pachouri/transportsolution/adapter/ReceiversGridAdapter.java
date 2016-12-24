package com.pachouri.transportsolution.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.model.ReceiverDetailsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit on 12/25/16.
 */

public class ReceiversGridAdapter extends RecyclerView.Adapter<ReceiversGridAdapter.ReceiverViewHolder> {

    private List<ReceiverDetailsModel> receiverDetailsModels;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public ReceiversGridAdapter(Context context, List<ReceiverDetailsModel> receiverDetailsModels) {
        this.mContext = context;
        receiverDetailsModels = new ArrayList<>();
        layoutInflater = LayoutInflater.from(mContext);
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
            final ReceiverDetailsModel receiverDetailsModel = receiverDetailsModels.get(position);
        }
    }

    @Override
    public int getItemCount() {
        return receiverDetailsModels.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {

        public ReceiverViewHolder(View itemView) {
            super(itemView);
        }
    }
}
