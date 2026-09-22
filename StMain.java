package StudentManagemetPortal;

import java.util.Scanner;

public class StMain {
    public static void main(String[] args) throws Exception {
        DBservice ds=new DBservice();


        //
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME TO STUDENT MANAGEMENT PORTAL");
        System.out.println("=====================================");
        boolean isrunning=true;
        while (isrunning) {
            //start pogram
            System.out.println("1.insert student details");
            System.out.println("2.update student  details");
            System.out.println("3.delete student details");
            System.out.println("4.view student  details");
            System.out.println("5.view student details by student id");
            System.out.println("6.exit");
            System.out.println("--------------------------------------------------");
            int choice =Integer.parseInt(sc.nextLine());
            String result=switch (choice) {
                case 1 -> {
                    System.out.println("Enter student ID,name,department,email");
                    ds.insertstudent(new Student(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine()));
                    yield "";
                }
                case 2 ->{
                    System.out.println("Enter student ID");
                    String id=sc.nextLine();
                    boolean isFound=ds.viewStdentbyid(new Student(id, null, null, null));
                    if(isFound){
                        System.out.println("Student name,department,email");
                       ds.updateStudent(new Student(id, sc.nextLine(), sc.nextLine(), sc.nextLine()));
                    }else{
                        System.out.println("Student not found");
                    }
                    yield "";
                }
                case 3 -> {
                    System.out.println("Enter student ID");
                    ds.deleteStudent(new Student(sc.nextLine(), null, null, null));
                    yield "";
                }
                case 4 -> {
                    ds.viewstudent();
                    yield "";
                }
                case 5 ->{
                    System.out.println("Enter student ID");
                    ds.viewStdentbyid(new Student(sc.nextLine(), null, null, null));
                    yield "";
                }
                case 6 ->{
                  isrunning=false;
                    System.out.println("Thank you for using the Student Management Portal");
                  yield "";
                }
                default -> "Invalid choice";
            };


        }
    }
}
