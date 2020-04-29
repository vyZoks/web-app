package jp.co.sysral.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sysral.dao.AttendDAO;

@WebServlet(urlPatterns={"/attenddeleteconfirmation"})
public class AttendDelete extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		int attendId = Integer.parseInt(request.getParameter("attendid"));
		
		AttendDAO attDao = new AttendDAO();
		
		try {
			System.out.println("出退勤情報削除");
			attDao.delete(attendId);
			
			request.getRequestDispatcher("/attenddetails").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}
}
