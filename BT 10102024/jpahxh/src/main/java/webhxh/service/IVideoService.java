package webhxh.service;

import java.util.List;

import webhxh.dao.IVideoDao;
import webhxh.dao.impl.VideoDao;
import webhxh.entity.Video;

public interface IVideoService {
	List<Video> findAll();

	List<Video> findVideosByCategoryId(int categoryid);

	void deleteVideo(int id);

	void updateVideo(Video video);

	Video findVideoById(int id);

	Video addVideo(Video video);
	
	List<Video> searchByTitle(String title);
	
	void increaseViews(int videoId);

	IVideoDao vidDao=new VideoDao();
}
