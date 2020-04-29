package jp.co.sysral.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sysral.bean.Attend;
import jp.co.sysral.dao.AttendDAO;

@WebServlet(urlPatterns={"/attenddetails"})
public class AttendDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		int empId = (Integer)session.getAttribute("empId");
		
		AttendDAO attendDAO = new AttendDAO();
		
		try {
			System.out.println("一覧取得開始");
			List<Attend> attendList = attendDAO.allSearch(empId);
			System.out.println("一覧取得終了");
			
			request.setAttribute("attendList", attendList);
			request.getRequestDispatcher("/attenddetails.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
}
