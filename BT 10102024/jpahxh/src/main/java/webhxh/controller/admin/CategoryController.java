package webhxh.controller.admin;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import webhxh.entity.Category;
import webhxh.service.ICategoryService;
import webhxh.service.impl.CategoryService;

import static webhxh.utils.Constant.UPLOAD_DIRECTORY;

@WebServlet(name = "MultiPartServlet",urlPatterns = {"/admin/categories","/admin/category/edit","/admin/category/update",
		"/admin/category/add","/admin/category/insert","/admin/category/delete","/admin/category/search"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public ICategoryService cateService= new CategoryService();
	
	
	
	 public static void deleteFile(String filePath) throws IOException {
		 Path path = Paths.get(filePath);
		 Files.delete(path);
		 }
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String url=req.getRequestURI();
		
		if (url.contains("/categories")) {
			List<Category> list =cateService.findAll();
			
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/category/edit")) {
			
			int id= Integer.parseInt(req.getParameter("id"));
			Category category= cateService.findById(id);
			req.setAttribute("cate", category);
			
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/category/add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
		
		else if (url.contains("/admin/category/delete")) {
			
			int id= Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String url=req.getRequestURI();
		
		if (url.contains("/admin/category/update")) {
			
			
			int categoryid= Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			int status= Integer.parseInt(req.getParameter("status"));
			String images = req.getParameter("images");
			
			
			Category category =new Category();
			category.setCategoryid(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(status);
			
			
			//luu hinh cu
			Category cateold= cateService.findById(categoryid);
			String fileold =cateold.getImage();
			//xu ly image
			
			String fname="";
			String uploadPath= UPLOAD_DIRECTORY;
			File uploadDir= new File(uploadPath);
			if (uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part =req.getPart("image");
				if (part.getSize()>0) {
					

					String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();
					//đổi tên file
					int index=filename.lastIndexOf(".");
					String ext=filename.substring(index+1);
					fname=System.currentTimeMillis()+"."+ext;
					//upload file
					part.write(uploadPath+"/"+fname);
					//ghi ten file vao data
					category.setImage(fname);
					
					 if (!category.getImage().substring(0, 5).equals("https") ) {
					 deleteFile(uploadPath+ "\\" + fileold);
					 }
				}else if (images != null) {
					 category.setImage(images);
					 }
				else {
					category.setImage(fileold);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			cateService.update(category);
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
			
		}
		else if (url.contains("/admin/category/insert")) {
			String categoryname=req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss=Integer.parseInt(status);
			//String image ="https://cdn-media.sforum.vn/storage/app/media/wp-content/uploads/2023/02/phim-vo-thuat.jpg";
			String images = req.getParameter("images");
			
			Category category=new Category();
			category.setCategoryname(categoryname);
			category.setStatus(statuss);
			
			
			String fname="";
			String uploadPath= UPLOAD_DIRECTORY;
			File uploadDir= new File(uploadPath);
			if (uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part =req.getPart("image");
				if (part.getSize()>0) {
					String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();
					//đổi tên file
					int index=filename.lastIndexOf(".");
					String ext=filename.substring(index+1);
					fname=System.currentTimeMillis()+"."+ext;
					//upload file
					part.write(uploadPath+"/"+fname);
					//ghi ten file vao data
					category.setImage(fname);
				}else if (images != null) {
					 category.setImage(images);}
				else {
					category.setImage("back.jpg");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
		}
		else if (url.contains("/admin/category/search")) {
			String catename= req.getParameter("search");
		
			List<Category> list =cateService.searchByName(catename);
			if (!list.isEmpty()) {
				req.setAttribute("catesearch", catename);
			}
			else req.setAttribute("catesearch", "No results matched");
			req.setAttribute("listcate1", list);
			req.getRequestDispatcher("/views/admin/category-list-result-search.jsp").forward(req, resp);
		}
	}

}
