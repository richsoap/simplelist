package com.example.richsoap.simplelist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.richsoap.backgroundData.DataManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    public MainRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar
        adapter = new MainRecyclerAdapter(DataManager.getUUIDList());
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.main_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //recyclerview
        RecyclerTouchHelperCallback.OnItemTouchCallbackListener onItemTouchCallbackListener = new RecyclerTouchHelperCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwipe(int adapterPosition, int direction) {
                if(direction == ItemTouchHelper.LEFT) {
                    adapter.onItemDelete(adapterPosition);
                }
                else {
                    adapter.onItemAdd(DataManager.getUUIDList());
                }
            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                adapter.onItemMove(srcPosition, targetPosition);
                return true;
            }
        };
        RecyclerTouchHelperCallback helperCallback = new RecyclerTouchHelperCallback(onItemTouchCallbackListener);
        helperCallback.setItemViewSwipeEnabled(true);
        helperCallback.setLongPressDragEnabled(true);
        RecyclerTouchHelper helper = new RecyclerTouchHelper(helperCallback);
        helper.attachToRecyclerView(recyclerView);
        //touchhelper
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
