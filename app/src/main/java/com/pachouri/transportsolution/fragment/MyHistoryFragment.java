package com.pachouri.transportsolution.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pachouri.transportsolution.R;
import com.pachouri.transportsolution.activity.HomeActivity;
import com.pachouri.transportsolution.activity.SplashActivity;
import com.pachouri.transportsolution.adapter.HistoryAdapter;
import com.pachouri.transportsolution.interfaces.FragmentCommunicator;
import com.pachouri.transportsolution.models.HistoryModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ankit on 12/25/16.
 */

public class MyHistoryFragment extends Fragment {

    @Bind(R.id.recyclerViewHistory)
    protected RecyclerView recyclerViewHistory;

    private Context attachContext;
    private List<HistoryModel> historyModelList = new ArrayList<>();

    public MyHistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_my_history_fragment, container, false);
        ButterKnife.bind(this, view);
        setAdapter();
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
        historyModelList = SplashActivity.getHistory();
        if (attachContext != null) {
            if (historyModelList != null) {
                if (historyModelList.size() > 0) {
                    HistoryAdapter historyAdapter = new HistoryAdapter(attachContext,
                            historyModelList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(attachContext);
                    recyclerViewHistory.setLayoutManager(linearLayoutManager);
                    recyclerViewHistory.setAdapter(historyAdapter);
                }
            }
        }
    }

    private void setToolbarTitle() {
        ((HomeActivity) getActivity()).setMainToolbarTitle(getString(R.string.bottom_menu_my_history));
        if (getActivity() instanceof FragmentCommunicator) {
            ((FragmentCommunicator) getActivity()).fragmentAttached(FragmentCommunicator.BottomBarTab
                    .HISTORY);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle();
    }
}
