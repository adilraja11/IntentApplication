package com.example.intentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_NAME = "key_name";
    public static final String KEY_NIM = "key_nim";
    public static final String KEY_PHONE = "key_phone";
    public static final String KEY_EMAIL = "key_email";

    private String phoneNumber, emailAddress, subject;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvNIM = findViewById(R.id.tv_nim);
        TextView tvPhone = findViewById(R.id.tv_phone);
        TextView tvEmail = findViewById(R.id.tv_email);
        Button btnPhoneCall = findViewById(R.id.btn_phone_call);
        Button btnEmailSend = findViewById(R.id.btn_email_send);

        String name = getIntent().getStringExtra(KEY_NAME);
        String nim = getIntent().getStringExtra(KEY_NIM);
        String phone = getIntent().getStringExtra(KEY_PHONE);
        String email = getIntent().getStringExtra(KEY_EMAIL);

        tvName.setText(String.format("Nama: %s", name));
        tvNIM.setText(String.format("NIM: %s", nim));
        tvPhone.setText(String.format("No. HP: %s", phone));
        tvEmail.setText(String.format("Email: %s", email));

        phoneNumber = phone;
        emailAddress = email;
        subject = "Belajar Intent";

        btnPhoneCall.setOnClickListener(this);
        btnEmailSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String[] emailArray = {emailAddress};
        switch (view.getId()) {
            case R.id.btn_phone_call:
                Intent callPhoneIntent = new Intent();
                callPhoneIntent.setAction(Intent.ACTION_DIAL);
                callPhoneIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(callPhoneIntent);
                break;
            case R.id.btn_email_send:
                Intent sendEmailIntent = new Intent();
                sendEmailIntent.setAction(Intent.ACTION_SENDTO);
                sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, emailArray);
                sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                sendEmailIntent.setData(Uri.parse("mailto:"));
                startActivity(sendEmailIntent);
                break;
        }
//        if (view.getId() == R.id.btn_phone_call) {
//            Intent callPhoneIntent = new Intent();
//            callPhoneIntent.setAction(Intent.ACTION_DIAL);
//            callPhoneIntent.setData(Uri.parse("tel:" + phoneNumber));
//            startActivity(callPhoneIntent);
//        }
    }
}