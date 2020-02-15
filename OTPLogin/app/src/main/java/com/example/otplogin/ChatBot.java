package com.example.otplogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ChatBot extends AppCompatActivity implements TextToSpeech.OnInitListener{

    Button goBtn;
    TextToSpeech textToSpeech;
    private int RESULT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        textToSpeech = new TextToSpeech(this, this);
        goBtn = findViewById(R.id.goButton);

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                startActivityForResult(intent, 10);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 10:
                    String Found = new ChatBot().getNumberFromResult(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS));
                    if (Found.equals("Recharge")) {
                        Intent intent = new Intent(ChatBot.this, RazorPayGateway.class);
                        startActivity(intent);
                        //firstNumTextView.setText(String.valueOf(intFound));
                    } else if (Found.equals("Home")){
                        Intent intent = new Intent(ChatBot.this,MainActivity.class);
                        startActivity(intent);

                    }

                    else {
                        Toast.makeText(getApplicationContext(), "Sorry, I didn't catch that! Please try again", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }else {
            Toast.makeText(getApplicationContext(), "Failed to recognize speech!", Toast.LENGTH_LONG).show();
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
        }
        return "Sorry";
    }

    @Override
    public void onInit(int i) {

    }
}
