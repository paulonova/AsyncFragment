package com.example.android.java.utilities;

import android.app.Fragment;
import android.content.Context;
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

    public interface ParentActivity{
        void handleTaskUpdate(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mParent = (ParentActivity)context;
        Log.i("AsyncFragment", "attached");
    }
}
