package com.example.th3;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Detail extends AppCompatActivity {

    private ImageView imgBack, imgEdit, imgDel, imgGender;
    private TextView txtSV, txtId, txtName, txtDoB, txtAddress, txtGender, txtEmail, txtMajor, txtGpa, txtYear;
    private Student student;

    private int position;
    private final int GO_TO_EDIT = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        imgGender = findViewById(R.id.imgGender);
        imgBack = findViewById(R.id.imgBack);
        imgEdit = findViewById(R.id.imgEdit);
        imgDel = findViewById(R.id.imgDel);
        txtSV = findViewById(R.id.txtSV);
        txtId = findViewById(R.id.txt_id);
        txtName = findViewById(R.id.txt_name);
        txtDoB = findViewById(R.id.txt_birth_date);
        txtAddress = findViewById(R.id.txt_address);
        txtGender = findViewById(R.id.txt_gender);
        txtEmail = findViewById(R.id.txt_email);
        txtMajor = findViewById(R.id.txt_major);
        txtGpa = findViewById(R.id.txt_gpa);
        txtYear = findViewById(R.id.txt_year);



        String studentId = getIntent().getStringExtra("student_id");

        student = findStudentById(studentId);

        if (student != null) {
            loadData();
        } else {
            Toast.makeText(this, "Sinh viên không tồn tại", Toast.LENGTH_SHORT).show();
            finish();
        }


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Detail.this, MainActivity.class);
                startActivity(i);
            }
        });


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editStudent();
            }
        });

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDeleteStudent();
            }
        });


    }

    private void confirmDeleteStudent() {
        new AlertDialog.Builder(this)
                .setTitle("Xóa sinh viên")
                .setMessage("Bạn có chắc muốn xóa sinh viên này?")
                .setPositiveButton("OK", (dialog, which) -> {
                    deleteStudent(student.getId());
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void deleteStudent(String studentId) {
        StudentList.getInstance().removeIf(s -> s.getId().equals(studentId)); // Xóa sinh viên theo ID
        Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(Detail.this, MainActivity.class);
        setResult(RESULT_OK, i);
        finish();
    }

    private Student findStudentById(String studentId) {
        for (Student s : StudentList.getInstance()) {
            if (s.getId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }


    private void editStudent(){
        Intent i = new Intent(Detail.this, Edit.class);
        i.putExtra("student_data", String.valueOf(student));
        i.putExtra("position",position);
        startActivityForResult(i,GO_TO_EDIT);
    }


//    private void deleteStudent(Student student) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Xóa sinh viên")
//                .setMessage("Bạn có chắc muốn xóa sinh viên này?")
//                .setPositiveButton("OK", (dialog, which) -> {
//                    StudentList.getInstance().remove(student);
//                    Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
//                    finish();
//                })
//                .setNegativeButton("CANCEL", null)
//                .show();
//    }

    private void loadData() {
        if (student.getGender().equals("Nam")) {
            imgGender.setImageResource(R.drawable.male);
        } else {
            imgGender.setImageResource(R.drawable.female);
        }
        txtSV.setText(student.getId());
        txtId.setText("Mã SV: " + student.getId());
        txtName.setText("Họ và tên: " + student.getFullName());
        txtDoB.setText("Ngày sinh: " + student.getBirthDate());
        txtAddress.setText("Địa chỉ: " + student.getAddress());
        txtGender.setText("Giới tính: " + student.getGender());
        txtEmail.setText("Email: " + student.getEmail());
        txtMajor.setText("Chuyên ngành: " + student.getMajor());
        txtGpa.setText("Điểm TB tích lũy: " + student.getGpa());
        txtYear.setText("SV năm thứ: " + student.getYear());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            student = (Student) data.getSerializableExtra("student_update");
            loadData();
            Intent i = new Intent(Detail.this, MainActivity.class);
            i.putExtra("student_update", String.valueOf(student));
            setResult(RESULT_OK, i);
        }
    }
}