package com.example.android.java;

import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.java.utilities.ActivityHelper;
import com.example.android.java.utilities.AsyncFragment;

public class MainActivity extends AppCompatActivity implements AsyncFragment.ParentActivity {

    private static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    private ScrollView mScroll;
    private TextView mLog;
    private AsyncFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Initialize the logging components
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mLog.setText("");

        FragmentManager manager = getFragmentManager();
        mFragment = (AsyncFragment) manager.findFragmentByTag(FRAGMENT_TAG);
        if(mFragment == null){
            mFragment = new AsyncFragment();
            manager.beginTransaction().add(mFragment, FRAGMENT_TAG).commit();
        }

    }

    public void onRunBtnClick(View v) {
        MyTask task = new MyTask();
        task.execute("Chocolate", "Vanilla", "Strawberry");
    }

    public void onClearBtnClick(View v) {
        mLog.setText("");
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    public void displayMessage(String message) {
        mLog.append(message + "\n");
        mScroll.scrollTo(0, mScroll.getBottom());
    }

    @Override
    public void handleTaskUpdate(String message) {
        displayMessage(message);
    }

    class MyTask extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... params) {
            for (String s : params) {
                publishProgress("I got " + s);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            displayMessage(values[0]);
        }
    }
}