package com.hotelreservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    DabaseHelper dabaseHelper;
    LinearLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_layout);

        dabaseHelper = new DabaseHelper(this);
        container = findViewById(R.id.container);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUsers();
    }

    public void displayUsers() {
        container.removeAllViews();
        ArrayList<Userinfo> list = dabaseHelper.getUserList();
        for (Userinfo info : list) {
            TextView textView = new TextView(this);
            textView.setText(info.firstname + " " + info.lastname + "  " + info.email);
            View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
            TextView name = view.findViewById(R.id.name);
            TextView email = view.findViewById(R.id.email);
            ImageView image = view.findViewById(R.id.image);
            if (info.image != null)
                image.setImageBitmap(RegisterActivity.getBitmap(info.image));
            name.setText(info.firstname + " " + info.lastname);
            email.setText(info.email);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserListActivity.this, DetailActivity.class);
                    intent.putExtra("id", info.id);
                    startActivity(intent);
                }
            });
            container.addView(view);

        }
    }
}
