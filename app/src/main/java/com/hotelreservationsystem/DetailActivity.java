package com.hotelreservationsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    String id;

    DabaseHelper dabaseHelper;
    TextView email, name, lastname, password;
    ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        id = getIntent().getStringExtra("id");
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        image = findViewById(R.id.image);
        dabaseHelper = new DabaseHelper(this);


        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteAlertDialog();
            }
        });
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, RegisterActivity.class);
                intent.putExtra("id", Integer.parseInt(id));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Userinfo info = dabaseHelper.getUserInfo(id);
        name.setText(info.firstname + " " + info.lastname);
        email.setText(info.email);
        password.setText(info.password);
        if (info.image != null)
            image.setImageBitmap(RegisterActivity.getBitmap(info.image));
    }

    public void showDeleteAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete User");
        builder.setMessage("Are You Sure?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dabaseHelper.deleteUser(id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();

    }
}
