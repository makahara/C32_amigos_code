package servlet;

import java.io.IOException;

import dao.DiariesDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Mutter;
import model.User;

@WebServlet("/EditFormServlet")
public class EditFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("index.jsp"); // ログインしていない場合はトップページへリダイレクト
			return;
		}

		String idStr = request.getParameter("id");
		if (idStr != null && !idStr.isEmpty()) {
			try {
				int id = Integer.parseInt(idStr);
				DiariesDAO dao = new DiariesDAO();
				Mutter diary = dao.findById(id);
				request.setAttribute("diary", diary);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/diaryEdit.jsp");
				dispatcher.forward(request, response);
			} catch (NumberFormatException e) {
				// IDが不正な形式の場合のエラー処理 (例: 詳細画面へリダイレクト)
				String referer = request.getHeader("referer");
				if (referer != null && referer.contains("DiaryDetailServlet")) {
					response.sendRedirect(referer);
				} else {
					response.sendRedirect("Main");
				}
			}
		} else {
			// IDが送信されなかった場合のエラー処理 (例: 詳細画面へリダイレクト)
			String referer = request.getHeader("referer");
			if (referer != null && referer.contains("DiaryDetailServlet")) {
				response.sendRedirect(referer);
			} else {
				response.sendRedirect("Main");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}