package com.example.dimitriygeorgiev.test;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_response;
    ConnectTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_response = findViewById(R.id.response);
    }

    public void connectToServer(View view) {
       task = new ConnectTask(txt_response);
       task.execute("");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void disconnect(View view) {
        task.killTask();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        task.killTask();
        this.finish();
    }
}
