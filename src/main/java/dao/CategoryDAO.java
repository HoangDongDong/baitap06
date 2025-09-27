package dao;

import java.util.List;
import models.Category;

public interface CategoryDAO {
	void create(Category category);

	Category findById(Long id);

	List<Category> findAll();

	void update(Category category);

	void delete(Long id);

	List<model.Category> searchByName(String keyword);

	void create(model.Category c);

}
