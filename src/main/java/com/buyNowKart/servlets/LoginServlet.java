package com.buyNowKart.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.buyNowKart.dao.UserDao;
import com.buyNowKart.entities.User;
import com.buyNowKart.helper.FactoryProvider;
import com.buyNowKart.resources.Constants;
import com.buyNowKart.resources.UrlConstants;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter  out = response.getWriter()){
			
			String email = request.getParameter("user_email");
			String password = request.getParameter("user_password");
			
			
			UserDao userDao = new UserDao(FactoryProvider.getSessionFactory());
			User user = userDao.getUserByEmailAndPassword(email, password);
			
			System.out.println(user);
			
			if(user == null) {
				HttpSession session = request.getSession();
				session.setAttribute("message", "Invalid user");
				response.sendRedirect("login.jsp");
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute(Constants.current_user, user);
				
				if(user.getUserType().equals(Constants.normal_user)) {
					response.sendRedirect(UrlConstants.HOME_NORMAL);
				}
				else if(user.getUserType().equals(Constants.admin_user)) {
					response.sendRedirect(UrlConstants.HOME_ADMIN);
				}
				else {
					out.println("Cannot identify the user");
				}
			}
			
		}
		catch (Exception e) {
			System.out.println("Error in loginServlet : "+e.getMessage());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
