package com.example.edson.conversordemoeda;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class SplashActivity extends Activity {

    //url para obtencao do valor da moeda para conversao
    public static final String URL_CODES = "http://openexchangerates.org/api/currencies.json";
    public static final String KEY_ARRAYLIST = "key_arraylist";
    //ArrayList de moedas que serao pesquisadas e passadas para o MainActivity
    private ArrayList<String> mCurrencies;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        new FetchCodesTask().execute(URL_CODES);
    }

    private class FetchCodesTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... params) {
            return new JSONParser().getJSONFromUrl(params[0]);
        }
        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                if (jsonObject == null) {
                    throw new JSONException("dados não disponíveis.");
                }
                Iterator iterator = jsonObject.keys();
                String key = "";
                mCurrencies = new ArrayList<String>();
                while (iterator.hasNext()) {
                    key = (String)iterator.next();
                    mCurrencies.add(key + " | " + jsonObject.getString(key));
                }
                Intent mainIntent = new Intent(SplashActivity.this, ConversorDeMoeda.class);
                mainIntent.putExtra(KEY_ARRAYLIST, mCurrencies);
                startActivity(mainIntent);
                finish();
            } catch (JSONException e) {
                Toast.makeText(
                        SplashActivity.this,
                        "Foi lançado um JSON exception: " + e.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                e.printStackTrace();
                finish();
            }
        }
    }

}
