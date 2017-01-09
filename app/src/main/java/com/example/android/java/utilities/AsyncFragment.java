package com.example.android.java.utilities;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Paulo Vila Nova on 2017-01-09.
 */

public class AsyncFragment extends Fragment {

    private ParentActivity mParent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public interface ParentActivity {
        void handleTaskUpdate(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mParent = (ParentActivity)context;
        Log.i("AsyncFragment", "attached");
    }

    public void runAsyncTask(String ... params) {
        MyTask task = new MyTask();
        task.execute(params);
    }

    class MyTask extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... params) {
            for (String s : params) {
                publishProgress("I got " + s);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mParent.handleTaskUpdate(values[0]);
        }

    }



}
