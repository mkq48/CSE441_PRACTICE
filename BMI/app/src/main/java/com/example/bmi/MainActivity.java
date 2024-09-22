package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText edtTen, edtCao, edtNang, edtBMI, edtCD;
    Button btnTinh = findViewById(R.id.btnTinh);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        edtTen = findViewById(R.id.edtTen);
        edtCao = findViewById(R.id.edtCao);
        edtNang = findViewById(R.id.edtNang);
        edtBMI = findViewById(R.id.edtBMI);
        edtCD = findViewById(R.id.edtCD);

        

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cd = "";
                double h = Double.parseDouble(edtCao.getText().toString().trim());
                double w = Double.parseDouble(edtNang.getText().toString().trim());
                double bmi = w / Math.pow(h,2);

                if(bmi < 18){
                    cd = "Bạn gầy";
                } else if (bmi <= 24.9) {
                    cd = "Bạn bình thường";
                } else if (bmi <= 29.9) {
                    cd = "Bạn béo phì độ 1";
                } else if (bmi <= 34.9) {
                    cd = "Bạn béo phì độ 2";
                } else {
                    cd = "Bạn béo phì độ 3";
                }

                DecimalFormat df = new DecimalFormat("#.00");

                edtBMI.setText(df.format(bmi));
                edtCD.setText(cd);
            }
        });
    }
}