package dao.impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import dao.UserDAO;
import model.User;

public class UserDAOImpl implements UserDAO{
	private EntityManager em;
	public UserDAOImpl(EntityManager em) {
        this.em = em;
    }
	@Override
	public void create(User user) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		return em.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	@Override
	public void update(User user) {
		em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
		
	}

	@Override
	public void delete(Long id) {
		em.getTransaction().begin();
        User u = em.find(User.class, id);
        if (u != null) em.remove(u);
        em.getTransaction().commit();
		
	}

	@Override
	public List<User> searchByUsername(String keyword) {
		TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username LIKE :kw", User.class);
        query.setParameter("kw", "%" + keyword + "%");
        return query.getResultList();
	}

}
