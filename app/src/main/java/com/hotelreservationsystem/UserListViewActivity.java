package com.hotelreservationsystem;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserListViewActivity extends AppCompatActivity {

    ListView listView;
    UserlistAdapter adapter;
    DabaseHelper dabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlistview_layout);
        listView = findViewById(R.id.listview);

        dabaseHelper = new DabaseHelper(this);
        adapter = new UserlistAdapter(this,dabaseHelper.getUserList());
        listView.setAdapter(adapter);

    }
}
