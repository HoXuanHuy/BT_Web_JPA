package webhxh.service.impl;

import java.util.List;

import webhxh.entity.Category;
import webhxh.service.ICategoryService;

public class CategoryService implements ICategoryService {

	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		return cateDao.findAll(page, pagesize);
	}

	
	 @Override
	public Category findByCategoryname(String name) {
		 try {
		 return cateDao.findByCategoryName(name);
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		 return null;
		 }

	 
	 
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return cateDao.findAll();
	}

	@Override
	public Category findById(int cateid) {
		// TODO Auto-generated method stub
		return cateDao.findById(cateid);
	}

	@Override
	public void delete(int cateid) throws Exception{

		cateDao.delete(cateid);
		
		
	}

	@Override
	public void update(Category category) {
		cateDao.update(category);
		
	}

	@Override
	public void insert(Category category) {
		Category cate = this.findByCategoryname(category.getCategoryname());
		 if(cate==null) {
		 cateDao.insert(category);
		 }
		
	}
	
	 @Override
	public List<Category> searchByName(String keyword) {
		 return cateDao.searchByName(keyword);
		 }

	
}
