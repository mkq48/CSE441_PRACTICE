package com.example.th3;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private static List<Student> students = new ArrayList<>();

    public static List<Student> getInstance() {
        return students;
    }

    // Phương thức khởi tạo dữ liệu
    public static void initialize(List<Student> studentList) {
        students = studentList;
    }

    public static void remove(Student student) {
        students.remove(student);
    }
}

