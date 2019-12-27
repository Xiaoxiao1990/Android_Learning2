package com.lxins.asynctaskdemo;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by pc on 2019/12/27.
 */

public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {
    private TextView textView;
    private ProgressBar progressBar;

    public MyAsyncTask(TextView textView, ProgressBar progressBar) {
        super();
        this.progressBar = progressBar;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        DelayOperator delayOperator = new DelayOperator();
        int i;

        for (i = 0; i < 100; i++) {
            delayOperator.delay();
            publishProgress(i);
        }

        return i + integers[0].intValue() + "";
    }

    @Override
    protected void onPreExecute() {
        textView.setText("Start execute async thread");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];

        progressBar.setProgress(value);
    }
}
