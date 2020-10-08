package com.servlets;

import com.dao.EmployeeDao;
import com.dao.UserDao;
import com.model.Employee;
import com.model.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Update User</h3>");
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		Employee u = EmployeeDao.getInfoById(id);
		out.println("<form action='UpdateServlet' method='POST'>");
		out.println("<table>");
		out.println("<tr><td></td><td><input type='hidden' name='id' value='"+u.getId()+"' /></td></tr>");
		out.println("<tr><td>Grade : </td><td><input type='text' name='username' value='"+u.getName()+"' /></td></tr>");
		out.println("<tr><td>Email : </td><td><input type='number' name='grade' value='"+u.getGrade()+"' /></td></tr>");
		out.println("<tr><td>Account Number : </td><td><input type='text' name='bankAccountNumber' value='"+u.getBankAccountNumber()+"' /></td></tr>");
		out.println("<tr><td>Phone : </td><td><input type='text' name='mobileNo' value='"+u.getMobileNo()+"' /></td></tr>");
		out.println("<tr><td>Address : </td><td><input type='text' name='address' value='"+u.getAddress()+"' /></td></tr>");
		out.println("<tr><td colspan='2'><input type='submit'  value='Update' /></td></tr>");
		out.println("</table>");
		out.println("</form>");
	}
}

