package com.example.th3;



import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Student> students;
    private StudentAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView imgSearch, imgX;
    private TextView txtDS;
    private EditText edtSearch;
    private final int ADD = 1, DELETE = 2, GO_TO_DETAIL = 0, UPDATE = 3;
    private Button btnAdd;


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

        btnAdd = findViewById(R.id.btnAdd);
        txtDS = findViewById(R.id.txtSV);
        edtSearch = findViewById(R.id.edtSearch);
        imgSearch = findViewById(R.id.imgSearch);
        imgX = findViewById(R.id.imgX);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDS.setVisibility(View.GONE);
                edtSearch.setVisibility(View.VISIBLE);
                imgSearch.setVisibility(View.GONE);
                imgX.setVisibility(View.VISIBLE);
            }
        });

        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgX.setVisibility(View.GONE);
                edtSearch.setVisibility(View.GONE);
                txtDS.setVisibility(View.VISIBLE);
                imgSearch.setVisibility(View.VISIBLE);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivityForResult(intent, ADD);
            }
        });

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadJSONData();
    }

    private void loadJSONData() {
        try {
            InputStreamReader reader = new InputStreamReader(getAssets().open("students.json"));
            List<Student> students = new Gson().fromJson(reader, new TypeToken<List<Student>>(){}.getType());

            StudentList.initialize(students);

            StudentAdapter adapter = new StudentAdapter(students);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> search(String keyword) {
        List<Student> searchStudents = new ArrayList<>();
        String key = capitalizeFirstLetterOfEachWord(keyword);
        for (Student s : students) {
            if(s.getFullName().toString().trim().contains(key)){
                searchStudents.add(s);
            }
        }
        return searchStudents;
    }


    public void deleteStudent(int position) {
        if (position >= 0 && position < students.size()) {
            students.remove(position);
            adapter.notifyItemRemoved(position);
            loadJSONData();
            Toast.makeText(this, "Xóa sinh viên thành công!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addStudent(Student s) {
        students.add(s);
        adapter.notifyItemInserted(students.size() - 1);
        adapter.notifyDataSetChanged();
    }


    public void updateStudent(Student student, int position) {
        students.set(position, student);
        adapter.notifyItemChanged(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD && resultCode == RESULT_OK && data != null) {
            Student newStudent = (Student) data.getSerializableExtra("new_student");
            if (newStudent != null) {
                addStudent(newStudent);
                loadJSONData();
                Toast.makeText(this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        } else if (data != null && resultCode == DELETE) {
            int position = data.getIntExtra("position", 0);
            deleteStudent(position);
        } else if (data != null && resultCode == UPDATE) {
            int position = data.getIntExtra("position", 0);
            Student student = (Student) data.getSerializableExtra("student_update");
            updateStudent(student, position);
        }
    }


    public static String capitalizeFirstLetterOfEachWord(String input) {
        String[] words = input.split(" ");
        StringBuilder capitalizedWords = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
                capitalizedWords.append(capitalizedWord).append(" ");
            }
        }

        return capitalizedWords.toString().trim();
    }

}