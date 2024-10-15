package webhxh.dao;

import java.util.List;

import webhxh.entity.Category;

public interface ICategoryDao {

	List<Category> searchByName(String catname);

	int count();

	List<Category> findAll(int page, int pagesize);

	Category findByCategoryName(String catname) throws Exception;

	List<Category> findAll();

	Category findById(int cateid);

	void delete(int cateid) throws Exception;

	void update(Category category);

	void insert(Category category);

}
