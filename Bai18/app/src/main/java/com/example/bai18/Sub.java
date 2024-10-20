package com.example.bai18;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Sub extends AppCompatActivity {
    TextView txtMaSo, txtBaiHat, txtLoiBaiHat, txtTacGia;
    ImageButton btnThich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        txtMaSo= findViewById(R.id.tvMaSo);
        txtBaiHat= findViewById(R.id.tvTenBaiHat);
        txtLoiBaiHat= findViewById(R.id.tvLoiBaiHat);
        txtTacGia= findViewById(R.id.tvTacGia);
        btnThich = findViewById(R.id.btnThich);

        android.content.Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String maso = bundle.getString("maso");
    }
}