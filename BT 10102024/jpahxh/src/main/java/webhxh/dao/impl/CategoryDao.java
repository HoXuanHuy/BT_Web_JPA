package webhxh.dao.impl;

import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import webhxh.config.JPAconfig;
import webhxh.dao.ICategoryDao;
import webhxh.entity.Category;

public class CategoryDao implements ICategoryDao {
	@Override
	public void insert (Category category) {
		EntityManager enma =JPAconfig.getEntityManager();
		EntityTransaction trans= enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
	}
	
	
	@Override
	public void update (Category category) {
		EntityManager enma =JPAconfig.getEntityManager();
		EntityTransaction trans= enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
	}
	
	@Override
	public void delete (int cateid) throws Exception{
		EntityManager enma =JPAconfig.getEntityManager();
		EntityTransaction trans= enma.getTransaction();
		try {
			trans.begin();
			
			Category cate=enma.find(Category.class,cateid);
			if (cate!=null) {
			enma.remove(cate);
			}else {
				throw new  Exception("Khong tim thay");
			}
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
	}
	
	@Override
	public Category findById(int cateid) {
		EntityManager enma =JPAconfig.getEntityManager();
		Category category =enma.find(Category.class,cateid);
		return category;
	}
	
	@Override
	public List<Category> findAll() {
		EntityManager enma =JPAconfig.getEntityManager();
		TypedQuery<Category> query=enma.createNamedQuery("Category.findAll",Category.class);
		return query.getResultList();
	}
	
	@Override
	public Category findByCategoryName(String catname) throws Exception{
		EntityManager enma =JPAconfig.getEntityManager();
		 String jpql = "SELECT c FROM Category c WHERE c.categoryname =:catename";
//		TypedQuery<Category> query=enma.createNamedQuery(jpql,Category.class);
//		query.setParameter("catename", "%"+catname+"%");
//		return query.getResultList();
		
		
		try {

			 TypedQuery<Category> query= enma.createQuery(jpql, Category.class);
			 query.setParameter("catename", catname);
			 Category category= query.getSingleResult();
			 if(category==null) {
			 throw new Exception("Category Name không tồn tại");
			 }
			 return category;
			 } finally {
			 enma.close();
			 } 
		
	}
	
	@Override
	public List<Category> findAll(int page, int pagesize){
		EntityManager enma =JPAconfig.getEntityManager();
		TypedQuery<Category> query=enma.createNamedQuery("Category.findAll",Category.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}
	
	@Override
	public int count() {
		EntityManager enma =JPAconfig.getEntityManager();
		String jpql="SELECT count(c) FROM Category c";
		Query query =enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}
	
	@Override
	public List<Category> searchByName(String catname) {
		EntityManager enma =JPAconfig.getEntityManager();
		 String jpql = "SELECT c FROM Category c WHERE c.categoryname like :catename";
		 TypedQuery<Category> query= enma.createQuery(jpql, Category.class);
		 query.setParameter("catename", "%" + catname + "%");
		 return query.getResultList();


		 }
	
	
	public static void main(String[] args) throws Exception {

//		CategoryDaoImpl cateDao = new CategoryDaoImpl();
		
		CategoryDao cateDao= new CategoryDao();
		Category cate=cateDao.findByCategoryName("huyho212");
		System.out.println(cate);

//		List<Category> list =cateDao.findAll();
//
//		for (Category cate : list) {
//			System.out.println(cate);
//		}

//		UserModel user1 = userDao.findById(1);
//	    if (user1 != null) {
//	        System.out.println(user1);
//	    } else {
//	        System.out.println("User not found!");
//	    }
	}
	

}

