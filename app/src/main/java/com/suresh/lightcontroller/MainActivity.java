package com.suresh.lightcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    EditText text_data;
    Button btn_Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_data = findViewById(R.id.text_data);
        btn_Send = findViewById(R.id.btn_send);

        ImageView lightIcon = (ImageView) findViewById(R.id.light_icon);
        lightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO - add action
                try {
                    new MQTTClient().publishToMQTT();
                } catch (Exception ex) {
                    Log.e(TAG, ex.getMessage());
                }
            }
        });

        btn_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    new MQTTClient().publishToMQTT(text_data.getText().toString());
                }catch (Exception ex){
                    Log.e(TAG,ex.getMessage());
                }
            }
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
