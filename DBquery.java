package StudentManagemetPortal;

public class DBquery {

    public static String insertquery(){
        return "insert into student values(?,?,?,?)";
    }

    public static String viewequery(){
        return "select * from student";
    }

    public static String ViewquerybyId(){
        return "select * from student where id=?";
    }

    public static String updatequery(){
        return "update student set name = ?, department= ?,email =? where id = ?";
    }
    public static String deletequery(){
        return "delete from student where id = ?";
    }
}
