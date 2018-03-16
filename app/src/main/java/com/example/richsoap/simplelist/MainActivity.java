package com.example.richsoap.simplelist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.richsoap.backgroundData.DataManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private List<UUID> mUUIDList = new ArrayList<>();
    MainRecyclerAdapter adapter;

    private MainRecyclerTouchHelperCallback.OnItemTouchCallbackListener onItemTouchCallbackListener = new MainRecyclerTouchHelperCallback.OnItemTouchCallbackListener() {
        @Override
        public void onSwipe(int adapterPosition, int direction) {
            if(!mUUIDList.isEmpty()) {
                if(direction == ItemTouchHelper.LEFT) {
                    mUUIDList.addAll(DataManager.getUUIDList());
                    adapter.notifyItemRangeInserted(mUUIDList.size() - 4,3);
                }
                else if(direction == ItemTouchHelper.RIGHT) {
                    mUUIDList.remove(0);
                    adapter.notifyItemRemoved(0);
                }
            }
        }

        @Override
        public boolean onMove(int srcPosition, int targetPosition) {
            if(!mUUIDList.isEmpty()) {
                Collections.swap(mUUIDList,srcPosition,targetPosition);
                adapter.notifyItemMoved(srcPosition,targetPosition);
                return true;
            }
            else {
                return false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar
        mUUIDList = DataManager.getUUIDList();
        adapter = new MainRecyclerAdapter(mUUIDList);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.main_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        MainRecyclerTouchHelperCallback helperCallback = new MainRecyclerTouchHelperCallback(onItemTouchCallbackListener);
        helperCallback.setDragEnable(true);
        helperCallback.setSwipeEnable(true);
        MainRecyclerTouchHelper helper = new MainRecyclerTouchHelper(helperCallback);
        helper.attachToRecyclerView(recyclerView);
        //RecyclerView
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //FloatButton
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
