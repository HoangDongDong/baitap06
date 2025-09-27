package dao;

import java.util.List;

import model.User;

public interface UserDAO {
	 void create(User user);
	    User findById(Long id);
	    List<User> findAll();
	    void update(User user);
	    void delete(Long id);
	    List<User> searchByUsername(String keyword);
}
