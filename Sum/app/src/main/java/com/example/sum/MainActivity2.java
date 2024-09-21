package com.example.sum;

import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    EditText edtKQ;
    Button btnQL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtKQ = findViewById(R.id.edtKQ);
        btnQL = findViewById(R.id.btnQL);

        edtKQ.setEnabled(false);

        Intent myintent = getIntent();
        Bundle bundle2 = myintent.getBundleExtra("mybackage");

        if(bundle2 != null){
            int a = bundle2.getInt("soa");
            int b = bundle2.getInt("sob");
            int kq = a + b;
            edtKQ.setText(String.valueOf(kq));
        }

        btnQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}