package com.example.otplogin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class OTPVerify extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //EditText et;
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.verification_code_activity);
        //et = findViewById(R.id.etDigit1);
        //et.setSelection(0);
    }
}
