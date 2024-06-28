package bai_tap_ve_nha.view;


import bai_tap_ve_nha.model.Student;
import bai_tap_ve_nha.service.StudentManager;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewStudent {
    public static void printAllStudentBegin(File file, StudentManager studentManager){
        FileReader fileReader = null;
        try {
        fileReader = new FileReader(file);}
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());;
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while (true) {
            try {
                if (!((line = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] dataStudent = line.split(", ");
            int idStudent = Integer.parseInt(dataStudent[0]);
            String fName = dataStudent[1];
            String lName = dataStudent[2];
            int ageStudent = Integer.parseInt(dataStudent[3]);
            Student student = new Student(idStudent, fName, lName, ageStudent);
            studentManager.addStudent(student);
            System.out.println(student);
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dislayMenu() {
        System.out.println("Menu Student Manager ");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Print All Students");
        System.out.println("5. Import students from file: ");
        System.out.println("6. Export students into file: ");
        System.out.println("0. Exit");
        System.out.println("Enter your choice");
    }

    public static void addStudentInStudentManager(File file, StudentManager studentManager, Scanner scanner) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            System.out.println("Enter First Name Student: ");
            String firstName = scanner.next();
            System.out.println("Enter Last Name Student: ");
            String lastName = scanner.next();
            System.out.println("Enter Age Student: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            int id = studentManager.idLast() + 1;
            String data = id + ", " + firstName + ", " + lastName + ", " + age;
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void updateStudentInStudentManager(Scanner scanner, File file) throws IOException {
        System.out.println("Enter Id Update");
        int idUpdate = scanner.nextInt();
        System.out.println("Enter First Name Student: ");
        String firstNameUpdate = scanner.next();
        System.out.println("Enter Last Name Student: ");
        String lastNameUpdate = scanner.next();
        System.out.println("Enter Age Student: ");
        int ageUpdate = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Student> studentsUpdate = new ArrayList<>();
        BufferedReader readerUpdate = new BufferedReader(new FileReader(file));
        String lineUpdate = "";
        while ((lineUpdate = readerUpdate.readLine()) != null) {
            String[] dataUpdate = lineUpdate.split(", ");
            if (Integer.parseInt(dataUpdate[0]) == idUpdate) {
                studentsUpdate.add(new Student(idUpdate, firstNameUpdate, lastNameUpdate, ageUpdate));
            } else {
                studentsUpdate.add(new Student(Integer.parseInt(dataUpdate[0]), dataUpdate[1], dataUpdate[2], Integer.parseInt(dataUpdate[3])));
            }
        }
        readerUpdate.close();
        BufferedWriter writerUpdate = new BufferedWriter(new FileWriter(file));
        for (Student student : studentsUpdate) {
            writerUpdate.write(student.getId() + ", " + student.getFirstName() + ", " + student.getLastName() + ", " + student.getAge() + "\n");
        }
        writerUpdate.close();
    }

    public static void removeStudentInStudentManager(Scanner scanner, File file) throws IOException {
        System.out.println("Enter Id Remove");
        int idRemove = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Student> studentsRemove = new ArrayList<>();
        BufferedReader readerRemove = new BufferedReader(new FileReader(file));
        String lineRemove = "";
        while ((lineRemove = readerRemove.readLine()) != null) {
            String[] dataRemove = lineRemove.split(", ");
            if (Integer.parseInt(dataRemove[0]) != idRemove) {
                studentsRemove.add(new Student(Integer.parseInt(dataRemove[0]), dataRemove[1], dataRemove[2], Integer.parseInt(dataRemove[3])));

            }
        }
        readerRemove.close();
        BufferedWriter writerRemove = new BufferedWriter(new FileWriter(file));
        for (Student student : studentsRemove) {
            writerRemove.write(student.getId() + ", " + student.getFirstName() + ", " + student.getLastName() + ", " + student.getAge() + "\n");
        }
        writerRemove.close();
    }

    public static void diplayAllStudentInStudentManager(File file, StudentManager studentManager) throws IOException {
        FileReader fileReader1 = new FileReader(file);
        BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
        String line1 = "";
        while ((line1 = bufferedReader1.readLine()) != null) {
            String[] dataStudent = line1.split(", ");
            int idStudent = Integer.parseInt(dataStudent[0]);
            String fName = dataStudent[1];
            String lName = dataStudent[2];
            int ageStudent = Integer.parseInt(dataStudent[3]);
            Student student = new Student(idStudent, fName, lName, ageStudent);
            studentManager.addStudent(student);
            System.out.println(student);
        }
        bufferedReader1.close();
    }
    public static void exportFile(Scanner scanner, File file) {
        System.out.println("Enter Export students into file: ");
        String fileExport = scanner.nextLine();
        try{

            File paste = new File(fileExport);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(paste);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line;
            while ((line = bufferedReader.readLine())!=null){
                bufferedWriter.write(line+"\n");

            }
            bufferedWriter.close();
            bufferedReader.close();}
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
