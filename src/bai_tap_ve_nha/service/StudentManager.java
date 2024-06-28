package bai_tap_ve_nha.service;



import bai_tap_ve_nha.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManager{
    private List<Student> studentManager;

    public StudentManager() {
        studentManager = new ArrayList();
    }

    public void addStudent(Student student) {
        studentManager.add(student);
    }


    public boolean isEmpty(){
        return studentManager.isEmpty();
    }
    public int idLast() {
        if (isEmpty()) {
            return 0;
        }
            return studentManager.get(studentManager.size() - 1).getId();

    }

    public Student getStudent(int id) {
        return studentManager.get(id);
    }

    public void displayAllStudents() {
        for (Student student : studentManager) {
            System.out.println(student);
        }
    }
    public Student displayLastStudent() throws Exception {
        if (isEmpty()) {
            throw new Exception("Data is Empty");
        }
        else {
            return studentManager.get(studentManager.size() - 1);
        }
    }

    public void clear() {
        studentManager.clear();
    }
}
