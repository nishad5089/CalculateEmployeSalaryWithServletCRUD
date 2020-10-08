package com.dao;

import com.model.BasicSalary;
import com.model.Employee;
import com.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private static final int TOTAL_GRADE = 6;
    private static final int LOWEST_GRADE = 6;
    private static final double SALARY_DIFFERENCE = 5000;

    // Database connection
    public static Connection getConnection() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/employeedb?useSSL=false&serverTimezone=UTC";
        String user = "nishad";
        String password = "nishad5089";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected...");
        } catch (Exception e) {
            System.out.println(e);
        }

        return con;
    }

    // Data Insert into user table
    public static int save(Employee e) {
        int status = 0;
        try {
            Connection connection = EmployeeDao.getConnection();
            String sql = "INSERT INTO employee (name,grade,bankAccountNumber,mobileNo,address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, e.getName());
            p.setInt(2, e.getGrade());
            p.setString(3, e.getBankAccountNumber());
            p.setString(4, e.getMobileNo());
            p.setString(5, e.getAddress());
            status = p.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;

    }

    public static List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        try {
            Connection conection = EmployeeDao.getConnection();
            String sql = "SELECT * FROM employee";
            PreparedStatement p = conection.prepareStatement(sql);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                Employee u = new Employee();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setGrade(rs.getInt(3));
                u.setBankAccountNumber(rs.getString(4));
                u.setMobileNo(rs.getString(5));
                u.setAddress(rs.getString(6));
                list.add(u);
            }
            conection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static int delete(Employee u) {
        int status = 0;
        try {
            Connection connection = EmployeeDao.getConnection();
            String sql = "DELETE FROM employee WHERE id=?";
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, u.getId());
            status = p.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static Employee getInfoById(int id) {
        Employee u = new Employee();
        int status = 0;
        try {
            Connection connection = EmployeeDao.getConnection();
            String sql = "SELECT * FROM employee WHERE id=?";
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setGrade(rs.getInt(3));
                u.setBankAccountNumber(rs.getString(4));
                u.setMobileNo(rs.getString(5));
                u.setAddress(rs.getString(6));
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return u;
    }
    public static BasicSalary getBasicSalary() {
        BasicSalary b = new BasicSalary();
        int status = 0;
        try {
            Connection connection = EmployeeDao.getConnection();
            String sql = "SELECT * FROM salary WHERE id=?";
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, 1);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                b.setId(rs.getInt(1));
                b.setAmount(rs.getDouble(2));
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    public static int update(Employee u) {
        int status = 0;
        try {
            Connection connection = EmployeeDao.getConnection();
            String sql = "update employee set name=?,grade=?,bankAccountNumber=?,mobileNo=?,address=? where id=?";
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, u.getName());
            p.setInt(2, u.getGrade());
            p.setString(3, u.getBankAccountNumber());
            p.setString(4, u.getMobileNo());
            p.setString(5, u.getAddress());
            p.setInt(6, u.getId());

            status = p.executeUpdate();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static double calculateSalary(int grade) {
        BasicSalary basicSalary = EmployeeDao.getBasicSalary();
        double baseSalary = ((LOWEST_GRADE - grade) * SALARY_DIFFERENCE) + basicSalary.getAmount();
        double allowance = EmployeeDao.calculateAllowance(baseSalary);
        double totalSalary = baseSalary + allowance;
        return totalSalary;
    }
    private static double calculateAllowance(double baseSalary) {
        double medicalAllowance = baseSalary * .15; //15% of base
        double houseRent = baseSalary * .20; //20% of base
        return medicalAllowance + houseRent;
    }


}
