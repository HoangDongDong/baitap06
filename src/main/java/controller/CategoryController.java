package controller;

import dao.CategoryDAO;
import dao.impl.CategoryDAOImpl;
import model.Category;
import model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/categories")
public class CategoryController extends HttpServlet {
    private CategoryDAO categoryDAO;
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("Bai06DB");
        EntityManager em = emf.createEntityManager();
        categoryDAO = new CategoryDAOImpl(em);   // ✅ khởi tạo đúng DAO
    }

    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":
                    showForm(req, resp, new Category());
                    break;
                case "edit":
                    editCategory(req, resp);
                    break;
                case "delete":
                    deleteCategory(req, resp);
                    break;
                case "search":
                    searchCategory(req, resp);
                    break;
                default:
                    listCategories(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp, Category category) {
		// TODO Auto-generated method stub
		
	}

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            switch (action) {
                case "insert":
                    insertCategory(req, resp);
                    break;
                case "update":
                    updateCategory(req, resp);
                    break;
                default:
                    listCategories(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listCategories(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<models.Category> categories = categoryDAO.findAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/views/category-list.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp, models.Category c)
            throws ServletException, IOException {
        req.setAttribute("category", c);
        req.getRequestDispatcher("/WEB-INF/views/category-form.jsp").forward(req, resp);
    }

    private void insertCategory(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        String icons = req.getParameter("icons");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");

        Category c = new Category();
        c.setName(name);
        c.setIcons(icons);
        c.setUser(user);

        categoryDAO.create(c);
        resp.sendRedirect("categories");
    }

    private void updateCategory(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String icons = req.getParameter("icons");

        models.Category c = categoryDAO.findById(id);
        if (c != null) {
            c.setCateName(name);
            c.setIcons(icons);
            categoryDAO.update(c);
        }
        resp.sendRedirect("categories");
    }

    private void editCategory(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        models.Category c = categoryDAO.findById(id);
        showForm(req, resp, c);
    }

    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        categoryDAO.delete(id);
        resp.sendRedirect("categories");
    }

    private void searchCategory(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Category> categories = categoryDAO.searchByName(keyword);
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/views/category-list.jsp").forward(req, resp);
    }
}
