import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Student currentStudent = null;
    private static Admin currentAdmin = null;

    public static void main(String[] args) {
        while (true) {
            int choice = displayMenuAndGetChoice();
            switch (choice) {
                case 1:
                    StudentLogin();
                    break;
                case 2:
                    AdminLogin();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static int displayMenuAndGetChoice() {
        System.out.println("===== Library System ====");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Exit");
        System.out.print("Choose option (1-3): ");
        return scanner.nextInt();
    }

    private static void StudentLogin() {
        if (currentStudent != null) {
            System.out.println("You are already logged in as a student.");
            return;
        }
        System.out.print("Enter your NIM: ");
        String nim = scanner.next();
        if (nim.length() != 15) {
            System.out.println("Invalid NIM length. NIM must be exactly 15 digits.");
            return;
        }
        Student student = Student.findStudentByNIM(nim);
        if (student == null) {
            System.out.println("User not found.");
        } else {
            currentStudent = student;
            System.out.println("Successful login as Student.");
        }
    }

    private static void AdminLogin() {
        if (currentAdmin != null) {
            System.out.println("You are already logged in as an admin.");
            return;
        }
        System.out.print("Enter your username (admin): ");
        String username = scanner.next();
        System.out.print("Enter your password (admin): ");
        String password = scanner.next();
        Admin admin = Admin.findAdminByUsernameAndPassword(username, password);
        if (admin == null) {
            System.out.println("Admin user not found.");
        } else {
            currentAdmin = admin;
            System.out.println("Successful login as Admin.");
        }
    }
}

class Student {
    private String nim;

    private Student(String nim) {
        this.nim = nim;
    }

    public static Student findStudentByNIM(String nim) {
        if (nim.length() != 15) {
            return null;
        } else {
            return new Student(nim);
        }
    }

    public String getNIM() {
        return nim;
    }
}

class Admin {
    private String username;
    private String password;

    private Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Admin findAdminByUsernameAndPassword(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return new Admin(username, password);
        } else {
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
