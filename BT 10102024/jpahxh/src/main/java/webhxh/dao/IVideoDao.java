package webhxh.dao;

import java.util.List;

import webhxh.entity.Video;

public interface IVideoDao {

	List<Video> findAll();

	List<Video> findVideosByCategoryId(int categoryid);

	void deleteVideo(int id);

	void updateVideo(Video video);

	Video findVideoById(int id);

	Video addVideo(Video video);

	List<Video> searchByTitle(String title);
	
	void increaseViews(int videoId);
	

}
