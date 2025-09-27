package controller;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class UserController extends HttpServlet {
	private UserDAO userDAO;
	private EntityManagerFactory emf;

	@Override
	public void init() {
		emf = Persistence.createEntityManagerFactory("Bai06DB");
		EntityManager em = emf.createEntityManager();
		userDAO = new UserDAOImpl(em);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null)
			action = "list";

		switch (action) {
		case "list":
			List<User> users = userDAO.findAll();
			req.setAttribute("users", users);
			req.getRequestDispatcher("/WEB-INF/views/admin/user-list.jsp").forward(req, resp);
			break;
		case "search":
			String keyword = req.getParameter("keyword");
			List<User> result = userDAO.searchByUsername(keyword);
			req.setAttribute("users", result);
			req.getRequestDispatcher("/WEB-INF/views/admin/user-list.jsp").forward(req, resp);
			break;
		// thêm case create/update/delete...
		}
	}
}
