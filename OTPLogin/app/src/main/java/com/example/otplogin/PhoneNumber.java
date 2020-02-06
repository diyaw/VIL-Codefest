package com.example.otplogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneNumber extends Activity {

    EditText phoneNumber;
    Button buttonCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.phone_number_activity);

        //Enabling button if Phone Number is entered
        phoneNumber = findViewById(R.id.etPhoneNumber);
        buttonCode = (Button) findViewById(R.id.btnSendConfirmationCode);
        phoneNumber.addTextChangedListener(phoneNumberWatcher);

        // Switch to next activity
        buttonCode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(PhoneNumber.this,
                        OTPVerify.class);
                startActivity(myIntent);
            }
        });
    }

    private TextWatcher phoneNumberWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String phoneDetails = phoneNumber.getText().toString();
            buttonCode.setEnabled(!phoneDetails.isEmpty() && phoneDetails.length() == 10);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
