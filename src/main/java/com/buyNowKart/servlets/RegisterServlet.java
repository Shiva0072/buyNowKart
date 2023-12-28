package com.buyNowKart.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.buyNowKart.entities.User;
import com.buyNowKart.helper.FactoryProvider;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
    public RegisterServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void  processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		try(PrintWriter out = resp.getWriter()){
			String userName = req.getParameter("user_name");
			String userEmail = req.getParameter("user_emil");
			String userMobile = req.getParameter("user_mobile");
			String userPassword = req.getParameter("user_password");
			String userAddress = req.getParameter("user_address");
			
//			out.println(userName+" , "+userEmail+" , "+userMobile+" , "+userPassword+" , "+userAddress);
			
			if(userName == null) {
				out.println("Empty not saving anything");
				return;
			}
			
			User newUser = new User(userName, userEmail, userPassword, userAddress, userMobile, "default_user_photo.jpg","normal_user");
		    Session hibernateSession = FactoryProvider.getSessionFactory().openSession();
		    Transaction txn = hibernateSession.beginTransaction();
		    Long userId = (long) hibernateSession.save(newUser);
		    txn.commit();
		    hibernateSession.close();
		    
		    HttpSession session = req.getSession();
		    session.setAttribute("message", "User registered successfully with userId : "+userId);
		    
		    
		    resp.sendRedirect("register.jsp");
		}
		catch(Exception e) {
			HttpSession session = req.getSession();
		    session.setAttribute("message",e.getMessage());
			e.printStackTrace();
		}

	}

}
