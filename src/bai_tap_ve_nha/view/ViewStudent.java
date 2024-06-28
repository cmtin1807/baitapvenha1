package bai_tap_ve_nha.view;

import bai_tap_ve_nha.model.Student;
import bai_tap_ve_nha.service.StudentManager;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewStudent {

    public static void printAllStudentBegin(File file, StudentManager studentManager) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] dataStudent = line.split(", ");
                int idStudent = Integer.parseInt(dataStudent[0]);
                String fName = dataStudent[1];
                String lName = dataStudent[2];
                int ageStudent = Integer.parseInt(dataStudent[3]);
                Student student = new Student(idStudent, fName, lName, ageStudent);
                studentManager.addStudent(student);
                System.out.println(student);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data: " + e.getMessage());
        }
    }

    public static void dislayMenu() {
        System.out.println("Menu Student Manager ");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Print All Students");
        System.out.println("5. Import students from file");
        System.out.println("6. Export students into file");
        System.out.println("0. Exit");
        System.out.println("Enter your choice");
    }

    public static void addStudentInStudentManager(File file, StudentManager studentManager, Scanner scanner) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            System.out.println("Enter First Name Student: ");
            String firstName = scanner.next();
            System.out.println("Enter Last Name Student: ");
            String lastName = scanner.next();
            System.out.println("Enter Age Student: ");
            int age = scanner.nextInt();
            scanner.nextLine();


            int id = studentManager.idLast() + 1;
            String data = "\n" + id + ", " + firstName + ", " + lastName + ", " + age;
            bufferedWriter.write(data);


            ArrayList<Student> students = readStudentsFromFile(file);
            studentManager.clear();
            for (Student student : students) {
                studentManager.addStudent(student);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void updateStudentInStudentManager(Scanner scanner, File file) {
        try {
            System.out.println("Enter Id Update");
            int idUpdate = scanner.nextInt();
            System.out.println("Enter First Name Student: ");
            String firstNameUpdate = scanner.next();
            System.out.println("Enter Last Name Student: ");
            String lastNameUpdate = scanner.next();
            System.out.println("Enter Age Student: ");
            int ageUpdate = scanner.nextInt();
            scanner.nextLine();

            ArrayList<Student> studentsUpdate = readStudentsFromFile(file);
            for (Student student : studentsUpdate) {
                if (student.getId() == idUpdate) {
                    student.setFirstName(firstNameUpdate);
                    student.setLastName(lastNameUpdate);
                    student.setAge(ageUpdate);
                    break;
                }
            }
            writeStudentsToFile(studentsUpdate, file);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public static void removeStudentInStudentManager(Scanner scanner, File file) {
        try {
            System.out.println("Enter Id Remove");
            int idRemove = scanner.nextInt();
            scanner.nextLine();

            ArrayList<Student> studentsRemove = readStudentsFromFile(file);
            studentsRemove.removeIf(student -> student.getId() == idRemove);
            writeStudentsToFile(studentsRemove, file);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error removing student: " + e.getMessage());
        }
    }

    public static void diplayAllStudentInStudentManager(File file, StudentManager studentManager) {
        try {
            ArrayList<Student> students = readStudentsFromFile(file);
            for (Student student : students) {
                studentManager.addStudent(student);
                System.out.println(student);
            }
        } catch (IOException e) {
            System.out.println("Error reading students: " + e.getMessage());
        }
    }

    public static void exportFile(Scanner scanner, File file) {
        try {
            System.out.println("Enter Export students into file: ");
            String fileExport = scanner.nextLine();
            File exportFile = new File(fileExport);
            ArrayList<Student> students = readStudentsFromFile(file);
            writeStudentsToFile(students, exportFile);
        } catch (IOException e) {
            System.out.println("Error exporting file: " + e.getMessage());
        }
    }

    private static ArrayList<Student> readStudentsFromFile(File file) throws IOException {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] dataStudent = line.split(", ");
                int idStudent = Integer.parseInt(dataStudent[0]);
                String fName = dataStudent[1];
                String lName = dataStudent[2];
                int ageStudent = Integer.parseInt(dataStudent[3]);
                Student student = new Student(idStudent, fName, lName, ageStudent);
                students.add(student);
            }
        }
        return students;
    }

    private static void writeStudentsToFile(ArrayList<Student> students, File file) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Student student : students) {
                bufferedWriter.write(student.getId() + ", " + student.getFirstName() + ", " + student.getLastName() + ", " + student.getAge() + "\n");
            }
        }
    }
}
