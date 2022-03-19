package com.example.activitylifecyclecodingchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState != null) {

            for (int i = 1; i < 11; i++) {
                String textViewName = "list_item"+i;
                int id = getResources().getIdentifier(textViewName, "id", getPackageName());
                TextView textView = (TextView) findViewById(id);
                textView.setText(savedInstanceState.getString("list_item" + i));
            }
        }

    }

    public void viewBuyableItems(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply =  data.getStringExtra(SecondActivity.EXTRA_REPLY);
                for (int i = 1; i < 11; i++) {
                    String textViewName = "list_item"+i;
                    int id = getResources().getIdentifier(textViewName, "id", getPackageName());
                    Log.d(LOG_TAG, "id value: " + String.valueOf(id));
                    TextView textView = (TextView) findViewById(id);
                    if(textView.getText() == ""){
                        textView.setText(textView.getText()+reply);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        for (int i = 1; i < 11; i++) {
            String textViewName = "list_item"+i;
            int id = getResources().getIdentifier(textViewName, "id", getPackageName());
            TextView textView = (TextView) findViewById(id);
            outState.putString("list_item" + i, textView.getText().toString());
        }
    }
}