package com.example.otplogin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.otplogin.R;

public class RechargeRecommendedFragment extends Fragment {
    View view;

    TextView price,call,data,validity;



    public RechargeRecommendedFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recharge_recommended_fragment, container, false);

        price = (TextView)view.findViewById(R.id.planView);
        call = (TextView)view.findViewById(R.id.talktimeBalanceView);
        data = (TextView)view.findViewById(R.id.dataBalanceView);
        validity = (TextView)view.findViewById(R.id.validityView);


        return view;
    }
}
