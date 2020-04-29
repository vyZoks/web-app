package jp.co.sysral.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sysral.bean.Attend;
import jp.co.sysral.dao.AttendDAO;

@WebServlet(urlPatterns={"/attendupdateconfirmation"})
public class AttendUpdateConfirmation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		int attendId = Integer.parseInt(request.getParameter("attendid"));
		
		AttendDAO attendDAO = new AttendDAO();
		
		try {
			Attend attend = attendDAO.search(attendId);
			
			request.setAttribute("attend", attend);
			request.getRequestDispatcher("/attendupdate.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}
