package jp.co.sysral.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sysral.bean.Employee;
import jp.co.sysral.dao.EmployeeDAO;

@WebServlet(urlPatterns={"/login"})
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		EmployeeDAO empDao = new EmployeeDAO();
		
		try {
			// 従業員情報を検索
			Employee emp = empDao.search(mail, pass);
			
			if (Objects.isNull(emp.getEmpPass())) {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("empId", emp.getEmpId());
				request.setAttribute("attendFlag", emp.isAttendFlag());
				request.getRequestDispatcher("/attend.jsp").forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}
