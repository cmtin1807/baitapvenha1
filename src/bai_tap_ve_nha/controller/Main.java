package bai_tap_ve_nha.controller;


import bai_tap_ve_nha.service.StudentManager;
import bai_tap_ve_nha.view.ViewStudent;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static bai_tap_ve_nha.view.IViewStudent.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        String path = "D:\\codegym\\baitapvenha\\src\\bai_tap_ve_nha\\data\\data.txt";
        while (true) {
            File file = new File(path);
            ViewStudent.printAllStudentBegin(file, studentManager);
            boolean menu = true;
            while (menu) {
                ViewStudent.displayMenu();
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case ADD_STUDENT:
                        ViewStudent.addStudentInStudentManager(file, studentManager, scanner);
                        break;
                    case UPDATE_STUDENT:
                        ViewStudent.updateStudentInStudentManager(scanner, file);
                        break;
                    case DELETE_STUDENT:
                        ViewStudent.removeStudentInStudentManager(scanner, file);
                        break;
                    case DISPLAY_STUDENT:
                        ViewStudent.displayAllStudentInStudentManager(file, studentManager);
                        break;
                    case IMPORT_FILE:
                        menu = false;
                        break;
                    case EXPORT_FILE:
                        ViewStudent.exportFile(scanner, file);
                        break;

                    case EXIT:
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            }
            if (!menu){
                System.out.println("Enter Import students from file: ");
                path = scanner.nextLine();}
            }
        }
}



