package com.example.otplogin;


import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;





import android.graphics.Color;
import android.os.Bundle;

import android.view.Menu;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnoFragment extends Fragment {

    String arrayName[]={
            "1","2","3","4","5"
    };


    public UnoFragment() {
// Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View RootView = inflater.inflate(R.layout.fragment_home, container, false);

        CircleMenu circleMenu=(CircleMenu)RootView.findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#ffcccc"),R.drawable.menu,R.drawable.icon_home)
                .addSubMenu(Color.parseColor("#ff0000"),R.drawable.icon_cancel)
                .addSubMenu(Color.parseColor("#cc0000"),R.drawable.icon_notify)
                .addSubMenu(Color.parseColor("#b20000"),R.drawable.icon_gps)
                .addSubMenu(Color.parseColor("#990000"),R.drawable.icon_setting)
                .addSubMenu(Color.parseColor("#ff0000"),R.drawable.icon_home)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        switch (index){

                            case 1:

                                Toast.makeText(getActivity(), "This is my Toast message!"+arrayName[index],
                                        Toast.LENGTH_LONG).show();
                                Intent intent =  new Intent(getActivity(), USSDLIst.class);
                                startActivity(intent);
                                break;
                            case 2 :
                                Toast.makeText(getActivity(), "This is my Toast message!"+arrayName[index],
                                        Toast.LENGTH_LONG).show();
                                Intent intent1 =  new Intent(getActivity(), MapsActivity.class);
                                startActivity(intent1);
                                break;





                        }


                    }
                });
        return RootView;
    }
}






