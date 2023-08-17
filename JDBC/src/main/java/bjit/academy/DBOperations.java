package bjit.academy;

import java.sql.*;

public class DBOperations {
    Connection conn;

    public void doConnectDB() {
        String connectionStr = "jdbc:mysql://localhost:3306/" + Utils.DB_NAME;
        String userName = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(connectionStr, userName, password);
            System.out.println("DB Connection is seccussful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchData() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + Utils.TABLE_NAME);
            System.out.println("\nWhole Database:");
            while (rs.next()) {
                int id = rs.getInt(Utils.COLUMN_EMPID);
                String name = rs.getString(Utils.COLUMN_EMPNAME);
                int age = rs.getInt(Utils.COLUMN_AGE);
                String salary = rs.getString(Utils.COLUMN_EMPSAL);
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Salary: " + salary);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void filterByID() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + Utils.COLUMN_EMPNAME +
                    " FROM " + Utils.TABLE_NAME + " WHERE " + Utils.COLUMN_EMPID + "=1759");
            System.out.println("\nName of the employee with ID 1759: ");
            while (rs.next()) {
                String name = rs.getString(Utils.COLUMN_EMPNAME);
                System.out.println(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void filterByAgeAndSalary() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + Utils.TABLE_NAME + " WHERE " +
                    Utils.COLUMN_AGE + ">25 AND " + Utils.COLUMN_EMPSAL + "<=80000");
            System.out.println("\nEmployees with age more than 25 and salary not more than 80000: ");
            while (rs.next()) {
                int id = rs.getInt(Utils.COLUMN_EMPID);
                String name = rs.getString(Utils.COLUMN_EMPNAME);
                int age = rs.getInt(Utils.COLUMN_AGE);
                String salary = rs.getString(Utils.COLUMN_EMPSAL);
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Salary: " + salary);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void averageSalary() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT AVG(" + Utils.COLUMN_EMPSAL + ") AS avg_sal FROM " +
                    Utils.TABLE_NAME);
            System.out.println("\nAverage Salary:");
            while (rs.next()) {
                double avg = rs.getDouble("avg_sal");
                System.out.println(avg);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void joinAndFilterByDepartment() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + Utils.TABLE_NAME +
                    " INNER JOIN "+ Tab2.TABLE_NAME + " ON " + Utils.TABLE_NAME + "." + Utils.COLUMN_EMPID +
                    " = " + Tab2.TABLE_NAME + "." + Tab2.COLUMN_EMPID +
                    " WHERE " + Tab2.TABLE_NAME + "." + Tab2.COLUMN_DEPT + "= 'Software'");
            System.out.println("\nEmployees of IT Department: ");

            while (rs.next()) {
                int id = rs.getInt(Utils.COLUMN_EMPID);
                String name = rs.getString(Utils.COLUMN_EMPNAME);
                int age = rs.getInt(Utils.COLUMN_AGE);
                String salary = rs.getString(Utils.COLUMN_EMPSAL);
                String department = rs.getString(Tab2.COLUMN_DEPT);

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age +
                        ", Salary: " + salary + ", Department: " + department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}