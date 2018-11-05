package com.example.dimitriygeorgiev.test;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class ConnectTask extends AsyncTask<String, String, TcpClient> {

    @SuppressLint("StaticFieldLeak")
    TextView txt_response;
    TcpClient mTcpClient;

    public ConnectTask(TextView textView) {
        this.txt_response = textView;
    }

    @Override
    protected TcpClient doInBackground(String... message) {

        //we create a TCPClient object
        //here the messageReceived method is implemented
        mTcpClient = new TcpClient(message1 -> publishProgress(message1));
        mTcpClient.run();

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        //response received from server
        Log.d("test", "response " + values[0]);
        //process server response here....
        txt_response.setText(values[0]);
    }

    public void killTask() {
        mTcpClient.stopClient();
    }
}