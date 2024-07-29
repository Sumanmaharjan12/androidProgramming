package com.hotelreservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_page);
        Log.i("Lifecycle", "Oncreate");


        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("Userinfo",0)
                        .edit()
                        .putBoolean("rememberme",false)
                        .commit();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Lifecycle", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle", "onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu1){
            startActivity(new Intent(this,UserListActivity.class));
            Toast.makeText(this, "Menu1", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.menu2){
            startActivity(new Intent(this,UserListViewActivity.class));
            Toast.makeText(this, "Menu2", Toast.LENGTH_SHORT).show();
}else if(id == R.id.menu3){
            startActivity(new Intent(this,RegisterActivity.class));
            Toast.makeText(this, "Menu2", Toast.LENGTH_SHORT).show();

        }else if(id==R.id.submenu1){
            Toast.makeText(this, "submenu1", Toast.LENGTH_SHORT).show();

        }else if(id==R.id.logout){

            getSharedPreferences("Userinfo",0)
                    .edit()
                    .putBoolean("rememberme",false)
                    .commit();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
