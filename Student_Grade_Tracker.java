package Java_Project;
import java.util.ArrayList;
import java.util.Scanner;

class Student{
    private int id;
    private String name;
    private double marks;
    Student(int id, String name, double marks){
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    int getId(){
        return id;
    }
    String getName(){
        return name;
    }
    double getMarks(){
        return marks;
    }
    void setMarks(double m){
        marks = m;
    }
    char getGrade(){
        if(marks > 90) return 'A';
        else if(marks > 80) return 'B';
        else if(marks > 70) return 'C';
        else if(marks > 60) return 'D';
        else return 'F';
    }

}
public class Student_Grade_Tracker {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("===========================================");
        System.out.println("          STUDENT GRADE TRACKER");
        System.out.println("===========================================");
        System.out.println("1. Add Student");
        System.out.println("2. View All Student");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Statistics Report");
        System.out.println("7. Exit");
        int choice;
        do {
            System.out.print("\nEnter Choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    statisticsReport();
                    break;
                case 7:
                    System.out.println("Thank You!");
                    break;
                default:
                    System.out.println("Invalid Choice!");
                    break;
            }
        }while(choice != 7);
        sc.close();
    }

    private static void addStudent() {
        System.out.print("Enter ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        for(Student s : students){
            if(id == s.getId()){
                System.out.println("Student ID already exists!");
                return;
            }
        }

        System.out.print("Enter Student Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Marks : ");
        double marks = sc.nextDouble();

        if(marks < 0 || marks > 100){
            System.out.println("Invalid Marks!");
            return;
        }

        students.add(new Student(id, name, marks));

        System.out.println("Student Added Successfully!");
    }

    public static void viewStudent() {
        if(students.isEmpty()){
            System.out.println("No Students Record Found!");
            return;
        }
        System.out.printf("%-10s %-20s %-10s %-10s\n","ID","Name","Marks","Grade");
        System.out.println("-------------------------------------------------");

        for(Student s : students){
            System.out.printf("%-10d %-20s %-10.2f %-10s\n",s.getId(),s.getName(),s.getMarks(),s.getGrade());
        }
    }

    public static void searchStudent() {
        System.out.print("Enter Student ID to Search : ");
        int id = sc.nextInt();

        for(Student s : students){
            if(id == s.getId()){
                System.out.println("\nStudent Found");
                System.out.println("ID    : "+ s.getId());
                System.out.println("Name  : "+ s.getName());
                System.out.println("Marks : "+ s.getMarks());
                System.out.println("Grade : "+ s.getGrade());
                return;
            }
        }
        System.out.println("Student Not Found!");
    }

    public static void updateStudent() {
        System.out.print("Enter Student ID to Update : ");
        int id = sc.nextInt();

        for(Student s : students){
            if(id == s.getId()){
                System.out.print("Enter New Marks : ");
                double marks = sc.nextDouble();
                if(marks < 0 || marks > 100){
                    System.out.println("Invalid Marks!");
                    return;
                }
                s.setMarks(marks);
                System.out.println("Student Updated Successfully!");
                return;
            }
        }
        System.out.println("Student Not Found!");
    }

    public static void deleteStudent() {
        System.out.print("Enter Student ID to Delete : ");
        int id = sc.nextInt();

        for(Student s : students){
            if(id == s.getId()){
                students.remove(s);
                System.out.println("Student Deleted Successfully!");
                return;
            }
        }
        System.out.println("Student Not Found!");
    }

    public static void statisticsReport() {
        if(students.isEmpty()){
            System.out.println("No Students Record Found!");
            return;
        }
        double sum = 0;
        Student highest = students.get(0);
        Student lowest = students.get(0);

        for(Student s : students){
            sum += s.getMarks();
            if(s.getMarks() > highest.getMarks()){
                highest = s;
            }
            if(s.getMarks() < highest.getMarks()){
                lowest = s;
            }
            double average = sum/students.size();

            System.out.println("\n========= STATISTICS REPORT =========");
            System.out.println("Total Students : "+ students.size());
            System.out.printf("Average Marks : %.2f\n", average);

            System.out.println("\nTop Student");
            System.out.println("Name  : "+ highest.getName());
            System.out.println("Marks : "+ highest.getMarks());

            System.out.println("\nLowest Scorer");
            System.out.println("Name  : "+ lowest.getName());
            System.out.println("Marks : "+ lowest.getMarks());
        }
    }
}
