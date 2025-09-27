package dao.impl;

import dao.VideoDAO;
import model.Video;

import jakarta.persistence.*;
import java.util.List;

public class VideoDAOImpl implements VideoDAO {
	private EntityManager em;

	public VideoDAOImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public void insert(Video video) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(video);
		tx.commit();

	}

	@Override
	public void update(Video video) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(video);
		tx.commit();

	}

	@Override
	public void delete(Long id) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Video v = em.find(Video.class, id);
		if (v != null) {
			em.remove(v);
		}
		tx.commit();
	}

	@Override
	public Video findById(Long id) {
		return em.find(Video.class, id);
	}

	@Override
	public List<Video> findAll() {
		return em.createQuery("SELECT v FROM Video v", Video.class).getResultList();

	}

	@Override
	public List<Video> searchByTitle(String keyword) {
		return em.createQuery("SELECT v FROM Video v WHERE v.title LIKE :kw", Video.class)
				.setParameter("kw", "%" + keyword + "%").getResultList();
	}
}
