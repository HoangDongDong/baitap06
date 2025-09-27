package dao;

import java.util.List;

import model.Video;

public interface VideoDAO {
	 void insert(Video video);
	    void update(Video video);
	    void delete(Long id);
	    Video findById(Long id);
	    List<Video> findAll();
	    List<Video> searchByTitle(String keyword);
}
