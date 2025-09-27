package dao.impl;

import java.util.List;

import dao.CategoryDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Category;

public class CategoryDAOImpl implements CategoryDAO {
	private EntityManager em;

	public CategoryDAOImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public void create(Category category) {
		em.getTransaction().begin();
		em.persist(category);
		em.getTransaction().commit();

	}

	@Override
	public Category findById(Long id) {
		return em.find(Category.class, id);
	}

	@Override
	public List<Category> findAll() {
		return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
	}

	@Override
	public void update(Category category) {
		em.getTransaction().begin();
		em.merge(category);
		em.getTransaction().commit();

	}

	@Override
	public void delete(Long id) {
		em.getTransaction().begin();
		Category c = em.find(Category.class, id);
		if (c != null)
			em.remove(c);
		em.getTransaction().commit();

	}

	@Override
	public List<model.Category> searchByName(String keyword) {
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.name LIKE :kw", Category.class);
		query.setParameter("kw", "%" + keyword + "%");
		return null;
	}

	@Override
	public void create(model.Category c) {
		// TODO Auto-generated method stub
		
	}

}
