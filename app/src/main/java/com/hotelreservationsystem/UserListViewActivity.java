package com.hotelreservationsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserListViewActivity extends AppCompatActivity {

    ListView listView;
    GridView gridView;

    UserlistAdapter adapter;
    UserGridAdapter gridAdapter;
    DabaseHelper dabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlistview_layout);
        listView = findViewById(R.id.listview);
        gridView = findViewById(R.id.gridview);
//        listView.setVisibility(ListView.VISIBLE);

        dabaseHelper = new DabaseHelper(this);
        adapter = new UserlistAdapter(this, dabaseHelper.getUserList());
        gridAdapter = new UserGridAdapter(this,dabaseHelper.getUserList());
        listView.setAdapter(adapter);
        gridView.setAdapter(gridAdapter);
        Button change = findViewById(R.id.change);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listView.getVisibility()==View.VISIBLE){
                    listView.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);
                }else{
                    listView.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.GONE);
                }
            }
        });

    }
}
