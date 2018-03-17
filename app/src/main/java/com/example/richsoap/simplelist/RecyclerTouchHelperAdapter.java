package com.example.richsoap.simplelist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richsoap on 18-3-17.
 */

public interface RecyclerTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void onItemDelete(int location);
    void onItemAdd(List list);
}
