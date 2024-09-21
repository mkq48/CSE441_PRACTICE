package com.example.temperature;

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

    EditText edtF, edtC;
    Button btnFC, btnCF, btnClear;
    DecimalFormat df;

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

        edtC = findViewById(R.id.edtC);
        edtF = findViewById(R.id.edtF);
        btnCF = findViewById(R.id.btnCF);
        btnFC = findViewById(R.id.btnFC);
        btnClear = findViewById(R.id.btnClear);
        df = new DecimalFormat("#.00");

        btnFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float f = Float.parseFloat(edtF.getText().toString().trim());
                float c = (f - 32)*((float) 5/9);
                edtC.setText( "" + df.format(c));
            }
        });



        btnCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float c = Float.parseFloat(edtC.getText().toString().trim());
                float f = c *((float) 9/5) + 32;
                edtF.setText("" + df.format(f));
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtC.setText("");
                edtF.setText("");

            }
        });

    }





}