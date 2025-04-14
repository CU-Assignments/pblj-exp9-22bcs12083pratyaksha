import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StudentController controller = new StudentController();

        while (true) {
            System.out.println("\n1.Add 2.View All 3.View One 4.Update 5.Delete 6.Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("ID, Name, Dept, Marks: ");
                    Student s = new Student(sc.nextInt(), sc.next(), sc.next(), sc.nextDouble());
                    controller.addStudent(s);
                }
                case 2 -> controller.getAllStudents().forEach(st ->
                    System.out.println(st.getStudentId() + " " + st.getName() + " " + st.getDepartment() + " " + st.getMarks()));
                case 3 -> {
                    System.out.print("Enter ID: ");
                    Student st = controller.getStudent(sc.nextInt());
                    if (st != null)
                        System.out.println(st.getName() + " " + st.getDepartment() + " " + st.getMarks());
                    else System.out.println("Not found.");
                }
                case 4 -> {
                    System.out.print("Enter ID & new Marks: ");
                    controller.updateMarks(sc.nextInt(), sc.nextDouble());
                }
                case 5 -> {
                    System.out.print("Enter ID to delete: ");
                    controller.deleteStudent(sc.nextInt());
                }
                case 6 -> System.exit(0);
            }
        }
    }
}
