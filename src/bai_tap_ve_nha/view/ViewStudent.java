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
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void displayMenu() {
        System.out.println("Menu Student Manager ");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Print All Students");
        System.out.println("5. Import students from file");
        System.out.println("6. Export students into file");
        System.out.println("0. Exit");
        System.out.println("Enter your choice:");
    }

    public static void addStudentInStudentManager(File file, StudentManager studentManager, Scanner scanner) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            System.out.print("Enter First Name Student: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter Last Name Student: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter Age Student: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            int id = studentManager.idLast() + 1;
            String data = "\n" + id + ", " + firstName + ", " + lastName + ", " + age;
            bufferedWriter.write(data);
            bufferedWriter.close();


            ArrayList<Student> students = readStudentsFromFile(file);
            studentManager.clear();
            students.forEach(studentManager::addStudent);

            System.out.println("Student added successfully.");
        } catch (InputMismatchException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void updateStudentInStudentManager(Scanner scanner, File file) {
        try {
            System.out.print("Enter Id Update: ");
            int idUpdate = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter First Name Student: ");
            String firstNameUpdate = scanner.nextLine();
            System.out.print("Enter Last Name Student: ");
            String lastNameUpdate = scanner.nextLine();
            System.out.print("Enter Age Student: ");
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
        } catch (InputMismatchException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void removeStudentInStudentManager(Scanner scanner, File file) {
        try {
            System.out.print("Enter Id Remove: ");
            int idRemove = scanner.nextInt();
            scanner.nextLine();
            ArrayList<Student> studentsRemove = readStudentsFromFile(file);
            studentsRemove.removeIf(student -> student.getId() == idRemove);
            writeStudentsToFile(studentsRemove, file);
        } catch (InputMismatchException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void displayAllStudentInStudentManager(File file, StudentManager studentManager) {
        try {
            ArrayList<Student> students = readStudentsFromFile(file);
            studentManager.clear();
            for (Student student : students) {
                studentManager.addStudent(student);
            }
            for (Student student : students) {
                System.out.println(student);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void exportFile(Scanner scanner, File file) {
        try {
            System.out.print("Enter Export students into file: ");
            String fileExport = scanner.nextLine();
            File exportFile = new File(fileExport);
            ArrayList<Student> students = readStudentsFromFile(file);
            writeStudentsToFile(students, exportFile);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
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
