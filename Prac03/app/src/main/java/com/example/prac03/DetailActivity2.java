package com.example.prac03;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity2 extends AppCompatActivity {
    private ImageView imgFlag;
    private TextView txtCountryName, txtCapital, txtPopulation, txtArea, txtDensity, txtWorldShare;
    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        imgFlag = findViewById(R.id.flagImgView);
        txtCountryName = findViewById(R.id.txtCountryName);
        txtCapital = findViewById(R.id.txtCapital);
        txtPopulation = findViewById(R.id.txtPopulation);
        txtArea = findViewById(R.id.txtArea);
        txtDensity = findViewById(R.id.txtDensity);
        txtWorldShare = findViewById(R.id.txtWorldShare);
        btnBack = findViewById(R.id.btnBack);
        Country country = (Country) getIntent().getSerializableExtra("Country");


        if (country != null) {

            ImageView imgFlag = findViewById(R.id.flagImgView);
            TextView txtCountryName = findViewById(R.id.txtCountryName);
            TextView txtCapital= findViewById(R.id.txtCapital);
            TextView txtPopulation = findViewById(R.id.txtPopulation);
            TextView txtArea = findViewById(R.id.txtArea);
            TextView txtDensity = findViewById(R.id.txtDensity);
            TextView txtWorldShare = findViewById(R.id.txtWorldShare);


            imgFlag.setImageResource(country.getFlagResId());
            txtCountryName.setText("Country: " + country.getName());
            txtCapital.setText("Capital:" + country.getCapital());
            txtPopulation.setText("Population: " + country.getPopulation()+" pp");
            txtArea.setText("Area: " + country.getArea() + " km²");
            txtDensity.setText("Density: " + country.getDensity() + " people/km²");
            txtWorldShare.setText("World Share: " + country.getWorldShare() + "%");
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}