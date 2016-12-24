package com.pachouri.transportsolution.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.activity.HomeActivity;
import com.pachouri.transportsolution.adapter.ReceiversGridAdapter;
import com.pachouri.transportsolution.interfaces.FragmentCommunicator;
import com.pachouri.transportsolution.models.ReceiverDetailsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ankit on 12/25/16.
 */

public class ReceiverFragment extends Fragment {

    @Bind(R.id.recyclerViewReceiver)
    protected RecyclerView recyclerViewReceiver;

    private Context attachContext;
    private List<ReceiverDetailsModel> receiverDetailsList = new ArrayList<>();

    public ReceiverFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_receiver_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachContext = context;
    }

    @Override
    public void onDestroyView() {
        attachContext = null;
        super.onDestroyView();
    }

    private void setAdapter() {
        if (attachContext != null) {
            ReceiversGridAdapter receiversGridAdapter = new ReceiversGridAdapter(attachContext, receiverDetailsList);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(attachContext, 2);
            recyclerViewReceiver.setLayoutManager(gridLayoutManager);
            recyclerViewReceiver.setAdapter(receiversGridAdapter);
        }
    }

    private void setToolbarTitle() {
        ((HomeActivity) getActivity()).setMainToolbarTitle(getString(R.string.bottom_menu_receivers));
        if (getActivity() instanceof FragmentCommunicator) {
            ((FragmentCommunicator) getActivity()).fragmentAttached(FragmentCommunicator.BottomBarTab
                    .RECEIVER);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle();
    }
}
