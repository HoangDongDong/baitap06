package controller;

import dao.VideoDAO;
import dao.impl.VideoDAOImpl;
import model.Video;

import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/video")
public class VideoController extends HttpServlet {
	private VideoDAO videoDAO;

	@Override
	public void init() throws ServletException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bai06DB");
		EntityManager em = emf.createEntityManager();
		videoDAO = new VideoDAOImpl(em);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null)
			action = "list";

		switch (action) {
		case "new":
			req.getRequestDispatcher("/WEB-INF/views/video-form.jsp").forward(req, resp);
			break;
		case "edit":
			Long id = Long.parseLong(req.getParameter("id"));
			Video v = videoDAO.findById(id);
			req.setAttribute("video", v);
			req.getRequestDispatcher("/WEB-INF/views/video-form.jsp").forward(req, resp);
			break;
		case "delete":
			id = Long.parseLong(req.getParameter("id"));
			videoDAO.delete(id);
			resp.sendRedirect("video");
			break;
		case "search":
			String keyword = req.getParameter("keyword");
			List<Video> searchList = videoDAO.searchByTitle(keyword);
			req.setAttribute("list", searchList);
			req.getRequestDispatcher("/WEB-INF/views/video-list.jsp").forward(req, resp);
			break;
		default:
			List<Video> list = videoDAO.findAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/views/video-list.jsp").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		String title = req.getParameter("title");
		String url = req.getParameter("url");
		String description = req.getParameter("description");
		boolean active = req.getParameter("active") != null;

		Video v = new Video(title, url, description, active);

		if (idStr == null || idStr.isEmpty()) {
			videoDAO.insert(v);
		} else {
			v.setId(Long.parseLong(idStr));
			videoDAO.update(v);
		}

		resp.sendRedirect("video");
	}
}
