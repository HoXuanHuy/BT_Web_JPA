package webhxh.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import webhxh.config.JPAconfig;
import webhxh.dao.IVideoDao;
import webhxh.entity.Video;

public class VideoDao implements IVideoDao {
	

    @Override
	public Video addVideo(Video video) {
    	EntityManager enma =JPAconfig.getEntityManager();
    	EntityTransaction trans= enma.getTransaction();
        try {
            trans.begin();

//            video.setCategory(category);
//            category.getVideos().add(video);

            enma.persist(video);
            trans.commit();

            return video;
        } catch (Exception e) {
        	e.printStackTrace();
			trans.rollback();
			throw e;
            }
         finally {
        	 enma.close();
		}
        
    }

    @Override
	public Video findVideoById(int id) {
    	EntityManager enma =JPAconfig.getEntityManager();
    	return enma.find(Video.class, id);
    }

    @Override
	public void updateVideo(Video video) {
    	EntityManager enma =JPAconfig.getEntityManager();
    	EntityTransaction trans= enma.getTransaction();
        try {
            trans.begin();
            enma.merge(video);
            trans.commit();
        } catch (Exception e) {
        	e.printStackTrace();
			trans.rollback();
			throw e;
            }
         finally {
        	 enma.close();
		}
    }

    @Override
	public void deleteVideo(int id) {
    	EntityManager enma =JPAconfig.getEntityManager();
    	EntityTransaction trans= enma.getTransaction();
        try {
            trans.begin();
            Video video = enma.find(Video.class, id);
            if (video != null) {
            	enma.remove(video);
            }
            trans.commit();
        } catch (Exception e) {
        	e.printStackTrace();
			trans.rollback();
			throw e;
            }
         finally {
        	 enma.close();
		}
    }

    @Override
	public List<Video> findVideosByCategoryId(int categoryid) {
    	EntityManager enma =JPAconfig.getEntityManager();
    	String jpql = "SELECT v FROM Video v WHERE v.category.categoryid = :categoryid";
        return enma.createQuery(jpql, Video.class)
                .setParameter("categoryid", categoryid)
                .getResultList();
    }
    
	@Override
	public List<Video> findAll() {
		EntityManager enma =JPAconfig.getEntityManager();
		TypedQuery<Video> query=enma.createNamedQuery("Video.findAll",Video.class);
		return query.getResultList();
	}
	
	@Override
	public List<Video> searchByTitle(String title) {
		EntityManager enma =JPAconfig.getEntityManager();
		 String jpql = "SELECT v FROM Video v WHERE v.title like :title";
		 TypedQuery<Video> query= enma.createQuery(jpql, Video.class);
		 query.setParameter("title", "%" + title + "%");
		 return query.getResultList();


		 }
	
	

	@Override
	public void increaseViews(int videoId) {
		EntityManager enma =JPAconfig.getEntityManager();
    	EntityTransaction trans= enma.getTransaction();
        try {
            trans.begin();
            Video video = enma.find(Video.class, videoId);
            if (video != null) {
                video.setViews(video.getViews() + 1);
                enma.merge(video);
            }
            trans.commit();
        } catch (Exception e) {
        	e.printStackTrace();
			trans.rollback();
			throw e;
            }
         finally {
        	 enma.close();
		}
		
	}

}
