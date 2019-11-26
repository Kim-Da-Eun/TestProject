package findwatson.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import findwatson.admin.dao.AdminDAO;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI().substring(request.getContextPath().length());
		AdminDAO dao = AdminDAO.getInstance();
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html; charset=UTF-8");
		try {
			if(cmd.contentEquals("관리자 로그인")) {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				boolean result = dao.adminLogin(id, pw);
				
				if(result) {
					request.getSession().setAttribute("id", id);
					request.setAttribute("result", result);
					request.getRequestDispatcher("관리자 로그인 후 페이지").forward(request, response);
				}
				else {
					response.sendRedirect("관리자 로그인 실패 페이지");
				}
			}
			else if(cmd.contentEquals("관리자 비밀번호 변경")) {
				
			}
			else if(cmd.contentEquals("회원목록정보")) {
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
