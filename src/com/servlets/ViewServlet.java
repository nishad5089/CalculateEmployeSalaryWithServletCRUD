package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmployeeDao;
import com.dao.UserDao;
import com.model.Employee;
import com.model.User;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<a href='index.html'>Add New User</a>");
		out.println("<h2>User List</h2>");
		List<Employee> list= EmployeeDao.getAll();
		out.println("<table border='1'>");
		out.println("<tr><th>Employe Name</th><th>Grade</th><th>Account Number</th><th>Phone</th><th>Address</th><th>Total Salary</th><th colspan='2'>Action</th></tr>");
		for(Employee u : list) {
			out.println("<tr><td>"+u.getName()+"</td><td>"+u.getGrade()+"</td><td>"+u.getBankAccountNumber()+"</td><td>"+u.getMobileNo()+"</td><td>"+u.getAddress()+"</td><td>"+EmployeeDao.calculateSalary(u.getGrade())+"</td><td><a href='EditServlet?id="+u.getId()+"'>Edit</a></td><td><a href='DeleteServlet?id="+u.getId()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
		out.close();
	}
}