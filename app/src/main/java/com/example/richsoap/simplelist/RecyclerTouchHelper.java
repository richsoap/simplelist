package com.example.richsoap.simplelist;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by richsoap on 18-3-15.
 */

public class RecyclerTouchHelper extends ItemTouchHelper {
    public RecyclerTouchHelper(RecyclerTouchHelperCallback callback) {
        super(callback);
    }
}
