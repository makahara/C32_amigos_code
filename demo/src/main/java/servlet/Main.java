package servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.GetDiaryListLogic;
import model.Mutter;
import model.PostDiaryLogic;
import model.User;

@WebServlet("/Main")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 20 * 1024 * 1024)
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GetDiaryListLogic getDiaryListLogic = new GetDiaryListLogic();
		List<Mutter> diaryList = getDiaryListLogic.execute();
		request.setAttribute("mutterList", diaryList); // リクエスト属性名はそのままに

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("index.jsp");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String text = request.getParameter("text");

		if (title != null && title.length() != 0 && text != null && text.length() != 0) {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			Part imagePart = request.getPart("image");
			byte[] imageData = null;
			if (imagePart != null && imagePart.getSize() > 0) {
				try (InputStream is = imagePart.getInputStream();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
					byte[] data = new byte[1024];
					int nRead;
					while ((nRead = is.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					imageData = buffer.toByteArray();
				}
			}

			Part iconPart = request.getPart("iconImage");
			byte[] iconImageData = null;
			if (iconPart != null && iconPart.getSize() > 0) {
				try (InputStream is = iconPart.getInputStream();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
					byte[] data = new byte[1024];
					int nRead;
					while ((nRead = is.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					iconImageData = buffer.toByteArray();
				}
			}

			Mutter diary = new Mutter(loginUser.getName(), title, text, imageData, iconImageData);
			PostDiaryLogic postDiaryLogic = new PostDiaryLogic();
			postDiaryLogic.execute(diary);
			response.sendRedirect("Main"); // 投稿成功後に一覧画面へリダイレクト
			return;
		} else {
			request.setAttribute("errorMsg", "タイトルと本文は必須入力です");
		}

		GetDiaryListLogic getDiaryListLogic = new GetDiaryListLogic();
		List<Mutter> diaryList = getDiaryListLogic.execute();
		request.setAttribute("mutterList", diaryList); // リクエスト属性名はそのままに

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}