// This Java program simulates a student course registration system, 
// allowing students to register for and drop courses. The system 
// stores course information, student information, and provides 
// functionalities to display available courses, register students 
// for courses, and drop courses.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        initializeCourses();
    }

    private void initializeCourses() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science", 30, "MWF 9:00-10:00"));
        courses.add(new Course("MATH101", "Calculus I", "Introduction to calculus", 25, "TTh 10:00-11:30"));
        courses.add(new Course("PHYS101", "Physics I", "Fundamentals of physics", 20, "MWF 11:00-12:00"));
    }

    public void registerStudent(Student student) {
        students.add(student);
    }

    public void displayCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. Register Student");
            System.out.println("2. Display Courses");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    Student student = new Student(studentID, name);
                    system.registerStudent(student);
                    System.out.println("Student registered successfully.");
                    break;
                case 2:
                    system.displayCourses();
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    student = system.findStudentByID(studentID);
                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    Course course = system.findCourseByCode(courseCode);
                    if (course == null) {
                        System.out.println("Course not found.");
                        break;
                    }
                    if (student.registerCourse(course)) {
                        System.out.println("Course registered successfully.");
                    } else {
                        System.out.println("Failed to register for course. It may be full.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    student = system.findStudentByID(studentID);
                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    course = system.findCourseByCode(courseCode);
                    if (course == null) {
                        System.out.println("Course not found.");
                        break;
                    }
                    if (student.dropCourse(course)) {
                        System.out.println("Course dropped successfully.");
                    } else {
                        System.out.println("Failed to drop course. You may not be registered for this course.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
