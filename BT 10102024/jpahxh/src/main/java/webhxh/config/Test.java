package webhxh.config;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import webhxh.entity.Category;

public class Test {
	
	public static void main(String[] args) {
		EntityManager enma =JPAconfig.getEntityManager();
		EntityTransaction trans= enma.getTransaction();
		
		Category cate=new Category();
		cate.setCategoryname("Iphone");
		cate.setImage("abc.jpg");
		cate.setStatus(1);
		
		
		try {
			trans.begin();
			//goi phuong thuc insert, update, delete
//			enma.persist(cate);
			
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
	}

}
