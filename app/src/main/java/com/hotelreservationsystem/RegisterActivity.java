package com.hotelreservationsystem;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class RegisterActivity extends AppCompatActivity {

    EditText firstname, lastname, email, password;

    SharedPreferences sharedPreferences;
    DabaseHelper dabaseHelper;
    ImageView image;


    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        id = getIntent().getIntExtra("id", 0);
        dabaseHelper = new DabaseHelper(this);
        sharedPreferences = getSharedPreferences("Userinfo", 0);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        if (id > 0) {
            Userinfo info = dabaseHelper.getUserInfo(id + "");
            firstname.setText(info.firstname);
            lastname.setText(info.lastname);
            email.setText(info.email);
            password.setText(info.password);

        }

        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstnameValue = firstname.getText().toString();
                String lastnameValue = lastname.getText().toString();
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                if (emailVaidation(email)
                        && emptyFieldValidation(password)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("firstname", firstnameValue);
                    editor.putString("lastname", lastnameValue);
                    editor.putString("email", emailValue);
                    editor.putString("password", passwordValue);
                    editor.apply();

                    ContentValues values = new ContentValues();
                    values.put("firstname", firstnameValue);
                    values.put("lastname", lastnameValue);
                    values.put("email", emailValue);
                    values.put("password", passwordValue);
                    values.put("username", firstnameValue);
                    if (bitmap != null)
                        values.put("image", getByteArray(bitmap));
                    if (id == 0) {
                        dabaseHelper.insertUser(values);

                        Toast.makeText(RegisterActivity.this, "info saved", Toast.LENGTH_SHORT).show();
                    } else {
                        dabaseHelper.updateUser(String.valueOf(id), values);
                        finish();
                    }
                }
            }
        });
        image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });


    }

    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);
        }
    }

    public boolean emptyFieldValidation(EditText view) {
        String value = view.getText().toString();
        if (value.length() > 0) {
            return true;
        }
        view.setError("Enter value");
        return false;

    }

    public boolean emailVaidation(EditText view) {
        String value = view.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            return true;
        }
        view.setError("Invalid email address");
        return false;
    }

    public static byte[] getByteArray(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }

    public static Bitmap getBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
