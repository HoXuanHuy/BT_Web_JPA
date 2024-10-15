package webhxh.service.impl;

import java.util.List;

import webhxh.entity.Video;
import webhxh.service.IVideoService;

public class VideoService implements IVideoService {
	
	

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return vidDao.findAll();
	}

	@Override
	public List<Video> findVideosByCategoryId(int categoryid) {
		// TODO Auto-generated method stub
		return vidDao.findVideosByCategoryId(categoryid);
	}

	@Override
	public void deleteVideo(int id) {
		// TODO Auto-generated method stub
		vidDao.deleteVideo(id);
	}

	@Override
	public void updateVideo(Video video) {
		// TODO Auto-generated method stub
		vidDao.updateVideo(video);
	}

	@Override
	public Video findVideoById(int id) {
		// TODO Auto-generated method stub
		return vidDao.findVideoById(id);
	}

	@Override
	public Video addVideo(Video video) {
		// TODO Auto-generated method stub
		return vidDao.addVideo(video);
	}

	@Override
	public List<Video> searchByTitle(String title) {
		// TODO Auto-generated method stub
		return vidDao.searchByTitle(title);
	}

	@Override
	public void increaseViews(int videoId) {
		vidDao.increaseViews(videoId);
		
	}

}
