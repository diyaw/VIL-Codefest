package com.example.otplogin;


import android.content.Intent;
import android.os.Bundle;

import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;





import android.graphics.Color;
import android.os.Bundle;

import android.view.Menu;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnoFragment extends Fragment implements TextToSpeech.OnInitListener {

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
                            case 3 :
                                Intent intent2 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                                intent2.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                                intent2.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                                startActivityForResult(intent2, 10);









                        }


                    }
                });


        SwipeButton swipeButton=(SwipeButton) RootView.findViewById(R.id.swipe_btn);

        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Toast.makeText(getActivity(), "active"+active,
                        Toast.LENGTH_LONG).show();

            }
        });
        return RootView;
    }

    @Override
    public void onInit(int i) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 10:
                    String Found = new UnoFragment().getNumberFromResult(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS));
                    if (Found.equals("Recharge")) {
                        Intent intent = new Intent(getActivity(), RazorPayGateway.class);
                        startActivity(intent);
                        //firstNumTextView.setText(String.valueOf(intFound));
                    } else if (Found.equals("Home")){
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);

                    } else if (Found.equals("Vodafone Stores Near Me")){
                        Intent intent = new Intent(getActivity(),MapsActivity.class);
                        startActivity(intent);

                    }  else if (Found.equals("QuickRecharge")){
                        Intent intent = new Intent(getActivity(),RazorPayGateway.class);
                        intent.putExtra("amountPrevious","399");
                        startActivity(intent);

                    }




                    else {
                        Toast.makeText(getActivity(), "Sorry, I didn't catch that! Please try again", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }else {
            Toast.makeText(getActivity(), "Failed to recognize speech!", Toast.LENGTH_LONG).show();
        }
    }


    private String getNumberFromResult(ArrayList<String> results) {
        for (String str : results) {
            if (!getIntNumberFromText(str).equals("Sorry")) {
                return getIntNumberFromText(str);
            }
        }
        return "No";
    }


    private String getIntNumberFromText(String strNum) {
        switch (strNum) {
            case "recharge":
                return "Recharge";
            case "home":
                return "Home";
            case "Vodafone stores near me":
                return "Vodafone Stores Near Me";
            case "Vodafone stores":
                return "Vodafone Stores Near Me";
            case "vodacoins":
                return "Profile";
            case "balance":
                return "Profile";
            case "data":
                return "Profile";
            case "quick recharge":
                return "QuickRecharge";
        }
        return "Sorry";
    }

}






