package servlet;

import java.io.IOException;

import dao.DiariesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("type");
		DiariesDAO dao = new DiariesDAO();
		byte[] imageData = null;

		if ("icon".equals(type)) {
			imageData = dao.getIconImageById(id);
		} else { // "main" または type が指定されていない場合
			imageData = dao.getImageById(id);
		}

		if (imageData != null) {
			response.setContentType("image/jpeg"); // 必要に応じて Content-Type を変更
			response.getOutputStream().write(imageData);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}