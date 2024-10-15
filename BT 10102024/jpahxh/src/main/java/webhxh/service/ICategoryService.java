package webhxh.service;

import java.util.List;

import webhxh.dao.ICategoryDao;
import webhxh.dao.impl.CategoryDao;
import webhxh.entity.Category;

public interface ICategoryService {

	List<Category> searchByName(String keyword);

	void insert(Category category);

	void update(Category category);

	void delete(int cateid) throws Exception;

	Category findById(int cateid);

	List<Category> findAll();

	Category findByCategoryname(String name);

	List<Category> findAll(int page, int pagesize);

	int count();

	ICategoryDao cateDao = new CategoryDao();


}
