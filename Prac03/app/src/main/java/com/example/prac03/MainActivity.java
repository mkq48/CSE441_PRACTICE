package com.example.prac03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prac03.DetailActivity2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CountryAdapter.OnCountryClickListener {
    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private List<Country> countryList;

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

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        countryList = getCountryData();

        Collections.sort(countryList, (c1, c2) -> Long.compare(c2.getPopulation(), c1.getPopulation()));

        adapter = new CountryAdapter(this, countryList, this);
        recyclerView.setAdapter(adapter);
    }

    private List<Country> getCountryData() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Vietnam", "Hanoi", R.drawable.vietnam_flag, 97338579, 331212.0, 292.0, 1.25));
        countries.add(new Country("United States", "Washington, D.C.", R.drawable.usa_flag, 331893745, 9833517.0, 36.0, 4.25));
        countries.add(new Country("China", "Beijing", R.drawable.china_flag, 1444216107, 9596961.0, 150.0, 18.47));
        countries.add(new Country("India", "New Delhi", R.drawable.india_flag, 1393409038, 3287263.0, 424.0, 17.7));
        countries.add(new Country("Russia", "Moscow", R.drawable.russia_flag, 145912025, 17098246.0, 9.0, 1.87));
        countries.add(new Country("Brazil", "Brasilia", R.drawable.brazil_flag, 213993437, 8515767.0, 25.0, 2.73));
        countries.add(new Country("Germany", "Berlin", R.drawable.germany_flag, 83240525, 357114.0, 233.0, 1.07));
        countries.add(new Country("France", "Paris", R.drawable.france_flag, 65426179, 643801.0, 102.0, 0.84));
        countries.add(new Country("Japan", "Tokyo", R.drawable.japan_flag, 125960000, 377975.0, 334.0, 1.61));
        countries.add(new Country("United Kingdom", "London", R.drawable.uk_flag, 68207114, 242495.0, 281.0, 0.87));
        countries.add(new Country("Canada", "Ottawa", R.drawable.canada_flag, 38005238, 9984670.0, 4.0, 0.48));
        countries.add(new Country("Australia", "Canberra", R.drawable.australia_flag, 25687041, 7692024.0, 3.0, 0.33));
        countries.add(new Country("South Korea", "Seoul", R.drawable.south_korea_flag, 51780579, 100210.0, 517.0, 0.66));
        countries.add(new Country("Italy", "Rome", R.drawable.italy_flag, 60244639, 301340.0, 200.0, 0.78));
        countries.add(new Country("Mexico", "Mexico City", R.drawable.mexico_flag, 126014024, 1964375.0, 64.0, 1.6));

        return countries;
    }

    @Override
    public void onCountryClick(Country country) {
        Toast.makeText(this, "Bạn đã click vào: " + country.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetailActivity2.class);
        intent.putExtra("Country", country);
        startActivity(intent);
    }
}
