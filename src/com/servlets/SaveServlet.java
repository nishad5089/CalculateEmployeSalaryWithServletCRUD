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


@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet{
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// getParameter() -> to retrieve the input values from HTML page
		String username= request.getParameter("username");
		int grade=Integer.parseInt(request.getParameter("grade"));
		String accountNumber= request.getParameter("bankAccountNumber");
		String mobileNo= request.getParameter("mobileNo");
		String address= request.getParameter("address");
		
		Employee e = new Employee();
		e.setName(username);
		e.setGrade(grade);
		e.setBankAccountNumber(accountNumber);
		e.setMobileNo(mobileNo);
		e.setAddress(address);

		int status = EmployeeDao.save(e);
		if(status>0) {
			out.print("<p> Record Saved Successfully</p>");
			request.getRequestDispatcher("index.html").include(request, response);
		}else {
			out.print("Sorry ! Unable to save record");
		}
		out.close();
	}
	
}

