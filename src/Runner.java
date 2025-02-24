import java.util.List;
import java.util.Scanner;

import model.teacher.Teacher;
import model.university.University;
import utils.Initializer;

public class Runner {
    public static void main(String[] args) {
        University university = Initializer.initializeUniversity();

        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------------------------");
        System.out.println("Welcome to The " + university.getName());
        System.out.println("--------------------------------------------");

        do {
            printMenu();

            System.out.print("Enter option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    listTeachers(university);
                    break;
                case 2:
                    listCourses(university);
                    break;
                case 3:
                    addStudent(university);
                    break;
                case 4:
                    addCourse(university);
                    break;
                case 5:
                    studentDetails(university);
                    break;
                case 6:
                    System.out.println("---------------------");
                    System.out.println("Quitting... Thank you");
                    System.out.println("---------------------");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("--------------");
                    System.out.println("Invalid option");
                    System.out.println("--------------");
                    break;
            }

        } while (true);
    }

    private static void listTeachers(University university) {
        List<Teacher> teachers = university.getTeachers();

        System.out.println("-------- TEACHERS --------");
        int counter = 1;
        for (Teacher t : teachers) {
            System.out.println(counter + ". " + t.getName() + ": $" + t.calculateSalary());
        }
        System.out.println("--------------------------");
        return;
    }

    private static void listCourses(University university) {

    }

    private static void addStudent(University university) {

    }

    private static void addCourse(University university) {

    }

    private static void studentDetails(University university) {

    }

    private static void printMenu() {
        System.out.println("-----------------");
        System.out.println("Menu:");
        System.out.println("1. See available teachers");
        System.out.println("2. See all offered courses");
        System.out.println("3. Enroll new student");
        System.out.println("4. Create a new course");
        System.out.println("5. See student information");
        System.out.println("6. Exit");
    }
}
