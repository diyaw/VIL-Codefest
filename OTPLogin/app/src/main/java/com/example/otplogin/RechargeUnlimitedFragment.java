package com.example.otplogin;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
//import android.support.v7.widget.CardView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class RechargeUnlimitedFragment extends Fragment {
    View view;
    CardView cardview;
    LayoutParams layoutparams;
    LayoutParams layoutparamsLinear;
    Context context;
    LinearLayout mainLinear;

    String hostAddress = "http://10.10.40.58/vil/getRechargeData.php";

    public RechargeUnlimitedFragment() {
    }

    public void addData(String priceValue, String validityValue, String talktimeValue, String dataValue){
        cardview = new CardView(context);
        layoutparams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        cardview.setLayoutParams(layoutparams);
        LinearLayout linear1 = new LinearLayout(context);
        layoutparamsLinear = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
        linear1.setLayoutParams(layoutparamsLinear);
        linear1.setOrientation(LinearLayout.HORIZONTAL);
        linear1.setWeightSum(4);

        LinearLayout linear2 = new LinearLayout(context);
        linear2.setLayoutParams(new LayoutParams(
                0, LayoutParams.MATCH_PARENT
        ));
        linear2.setOrientation(LinearLayout.VERTICAL);
        //linear2.setWeight(1);
        TextView text1 = new TextView(context);
        text1.setText(priceValue);
        text1.setGravity(LinearLayout.TEXT_ALIGNMENT_CENTER);
        text1.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        text1.setTextColor(Color.BLACK);


        LinearLayout linear3 = new LinearLayout(context);
        linear3.setLayoutParams(new LayoutParams(
                0, LayoutParams.MATCH_PARENT
        ));
        linear3.setOrientation(LinearLayout.VERTICAL);
        //linear2.setWeight(1);
        TextView text2 = new TextView(context);
        text2.setText(talktimeValue);
        text2.setGravity(LinearLayout.TEXT_ALIGNMENT_CENTER);
        text2.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        text2.setTextColor(Color.BLACK);


        LinearLayout linear4 = new LinearLayout(context);
        linear4.setLayoutParams(new LayoutParams(
                0, LayoutParams.MATCH_PARENT
        ));
        linear4.setOrientation(LinearLayout.VERTICAL);
        //linear2.setWeight(1);
        TextView text3 = new TextView(context);
        text3.setText(dataValue);
        text3.setGravity(LinearLayout.TEXT_ALIGNMENT_CENTER);
        text3.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        text3.setTextColor(Color.BLACK);


        LinearLayout linear5 = new LinearLayout(context);
        linear5.setLayoutParams(new LayoutParams(
                0, LayoutParams.MATCH_PARENT
        ));
        linear5.setOrientation(LinearLayout.VERTICAL);
        //linear2.setWeight(1);
        TextView text4 = new TextView(context);
        text4.setText(validityValue);
        text4.setGravity(LinearLayout.TEXT_ALIGNMENT_CENTER);
        text4.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        text4.setTextColor(Color.BLACK);

        cardview.addView(text1);
        cardview.addView(text2);
        cardview.addView(text3);
        cardview.addView(text4);

        mainLinear.addView(cardview);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recharge_unlimited_fragment, container, false);
        context = getActivity().getApplicationContext();
        mainLinear = (LinearLayout)view.findViewById(R.id.mainLinear);
        getDetails();
        return view;
    }

    public void getDetails() {
        new GetDetails().execute();
    }


    class GetDetails extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String host = hostAddress + "?rechargeType=2";
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(host));

                HttpResponse response = client.execute(request);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer stringBuffer = new StringBuffer("");
                String line = "";

                while ((line = reader.readLine()) != null) {
                    //Log.e("data",line);
                    stringBuffer.append(line);
                    break;
                }
                reader.close();
                result = stringBuffer.toString();
            } catch (Exception e) {
                return new String("Exception :" + e.getMessage());
            }
            return result;
        }
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonResult = new JSONObject(result);
                int success = jsonResult.getInt("success");

                if(success == 1){
                    //Toast.makeText(getApplicationContext(), "Showing Customer Database.", Toast.LENGTH_SHORT).show();
                    JSONArray customers = jsonResult.getJSONArray("content");
                    for(int i = 0; i<customers.length(); i++){
                        JSONObject data = customers.getJSONObject(i);
                        String rechargeAmount = data.getString("recharge_amount");
                        String rechargeValidity = data.getString("recharge_validity");
                        String talktimeBalance = data.getString("talktime_balance_provided");
                        String dataBalance = data.getString("dala_balance_provided");

                        addData(rechargeAmount,talktimeBalance,dataBalance,rechargeValidity);

                        Log.e("data",rechargeAmount+" ; "+rechargeValidity+" ; "+talktimeBalance+" ; "+dataBalance);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
