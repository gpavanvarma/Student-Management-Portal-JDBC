package StudentManagemetPortal;

import java.sql.*;

public class DBservice {

    //student insert
    public void insertstudent(Student stInsert) throws Exception {
        Connection connection=null;
        try
        {
            connection = DBconnection.getDs().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(DBquery.insertquery());
            ) {
                preparedStatement.setString(1, stInsert.id());
                preparedStatement.setString(2, stInsert.name());
                preparedStatement.setString(3, stInsert.department());
                preparedStatement.setString(4, stInsert.email());
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    connection.commit();
                    System.out.println("Record inserted successfully");
                } else {
                    System.out.println("Record insert failed");
                }
            } // nested try-catch close
        } catch (Exception e) {
            try {
                connection.rollback();
                System.out.println("rolling backed");
            }catch (Exception ee){ee.printStackTrace();}
        }/// end main try-catch block
        finally {
            try{connection.close();}catch(Exception e){e.printStackTrace();}
        }
    }/// end of insert method


    public void viewstudent() throws Exception {
        Connection connection=null;
        try
        {
            connection = DBconnection.getDs().getConnection();
            connection.setAutoCommit(false);
            try(Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(DBquery.viewequery());){
                while (resultSet.next()) {
                   Student student = new Student(
                       resultSet.getString("id"),
                       resultSet.getString("name"),
                       resultSet.getString("department"),
                       resultSet.getString("email")
                   );
                    System.out.println();
                   displayst(student);
                }//end of while loop
                connection.commit();
            }
        }
        catch (Exception ee){
            try{connection.rollback();}catch(Exception eee){eee.printStackTrace();}
            ee.printStackTrace();
        }
        finally {
            try{connection.close();}catch(Exception e){e.printStackTrace();}
            System.out.println();
        }
    }///end of viewstudent
    private void displayst(Student stDisplay) throws Exception {
        System.out.println("Student ID: " + stDisplay.id());
        System.out.println("Student Name: " + stDisplay.name());
        System.out.println("Student Department: " + stDisplay.department());
        System.out.println("Student Email: " + stDisplay.email());
    }

    /// start of view student deatiles by ID
    public boolean viewStdentbyid(Student t) throws Exception {
        Connection connection=null;
        boolean isfound=false;
        try
        {
            connection = DBconnection.getDs().getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DBquery.ViewquerybyId())){
                preparedStatement.setString(1,t.id());
                ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()) {
                        isfound=true;
                        Student student = new Student(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("department"),
                            resultSet.getString("email")
                        );
                        System.out.println();
                        displayst(student);
                    }
                    else{
                        System.out.println("record not found by id");
                    }
                System.out.println();
            }
        }catch (Exception ee){
            try{connection.rollback();}catch(Exception eee){eee.printStackTrace();}
            ee.printStackTrace();
        }
        finally {
            try{connection.close();}catch(Exception e){e.printStackTrace();}
        }
        return isfound;
        /// end of view student deatiles by ID
    }


    /// start of update methos
    public void updateStudent(Student stUpdate) throws Exception {
        Connection connection=null;
        try
        {
            connection = DBconnection.getDs().getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DBquery.updatequery());){
                preparedStatement.setString(1, stUpdate.name());
                preparedStatement.setString(2, stUpdate.department());
                preparedStatement.setString(3, stUpdate.email());
                preparedStatement.setString(4, stUpdate.id());
                preparedStatement.executeUpdate();

                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    connection.commit();
                    System.out.println("Record Update successfully");
                }else{
                    System.out.println("Record update failed");
                }
            }
        }catch (Exception ee){
            try{connection.rollback();}catch(Exception eee){eee.printStackTrace();}
            ee.printStackTrace();
        }
        finally {
            try{connection.close();}catch(Exception e){e.printStackTrace();}
        }
    }/// /// start of update method


    /// start of deletest method
    public void deleteStudent(Student t) throws Exception {
        Connection connection=null;
        boolean isfound=false;
        try
        {
            connection = DBconnection.getDs().getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DBquery.deletequery())){
                preparedStatement.setString(1, t.id());
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                connection.commit();
                System.out.println("Record delete successfully");
            }else{
                System.out.println("Record delete failed");
            }
            }/// end of try-resource
    }catch(Exception ee){
            try{connection.rollback();}catch(Exception eee){eee.printStackTrace();}
        }
        finally {
            try{connection.close();}catch(Exception e){e.printStackTrace();}
            System.out.println();
        }
        }

}//end of cl
