package com.example.sum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtA, edtB;
    Button btnTong;

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


        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);

        btnTong = findViewById(R.id.btnTong);

        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, MainActivity2.class);
                Bundle bundle1 = new Bundle();


                String inputA = edtA.getText().toString().trim();
                String inputB = edtB.getText().toString().trim();

                int a = Integer.parseInt(inputA);
                int b = Integer.parseInt(inputB);

                bundle1.putInt("soa", a);
                bundle1.putInt("sob", b);

                intent1.putExtra("mybackage", bundle1);
                startActivity(intent1);
            }
        });

    }
}