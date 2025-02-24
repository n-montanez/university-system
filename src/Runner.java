import java.util.Scanner;
import model.university.University;
import utils.Initializer;
import utils.InputValidator;
import utils.UniversityManager;

public class Runner {
    public static void main(String[] args) {
        University university = Initializer.initializeUniversity();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------------------------");
        System.out.println("Welcome to The " + university.getName());
        System.out.println("--------------------------------------------");

        while (true) {
            printMenu();

            System.out.print("Enter option: ");
            int option = InputValidator.getValidIntegerInput(scanner);

            switch (option) {
                case 1:
                    UniversityManager.listTeachers(university);
                    break;
                case 2:
                    UniversityManager.courseDetails(university, scanner);
                    break;
                case 3:
                    UniversityManager.addStudent(university, scanner);
                    break;
                case 4:
                    UniversityManager.addCourse(university, scanner);
                    break;
                case 5:
                    UniversityManager.studentDetails(university, scanner);
                    break;
                case 6:
                    System.out.println("---------------------");
                    System.out.println("Quitting... Thank you");
                    System.out.println("---------------------");
                    scanner.close();
                    return;
                default:
                    System.out.println("--------------");
                    System.out.println("Invalid option");
                    System.out.println("--------------");
                    break;
            }
        }
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