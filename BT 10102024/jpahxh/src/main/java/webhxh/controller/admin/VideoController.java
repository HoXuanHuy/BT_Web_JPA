package webhxh.controller.admin;


import static webhxh.utils.Constant.UPLOAD_DIRECTORY;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import webhxh.entity.Category;
import webhxh.entity.Video;
import webhxh.service.ICategoryService;
import webhxh.service.IVideoService;
import webhxh.service.impl.CategoryService;
import webhxh.service.impl.VideoService;


@WebServlet(urlPatterns = {"/admin/videos","/admin/video/video_cate","/admin/video/delete","/admin/video/add",
		"/admin/video/insert","/admin/video/edit","/admin/video/update","/admin/video/search","/admin/video/watchvideo"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 50)
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	public IVideoService vidService= new VideoService();
	public ICategoryService cateService= new CategoryService();
	
	 public static void deleteFile(String filePath) throws IOException {
		 Path path = Paths.get(filePath);
		 Files.delete(path);
		 }
	 
	 public static String getYouTubeVideoID(String url) {
	        // Biểu thức chính quy cho URL YouTube
	        String youtubeUrlRegex = 
	            "(?:https?:\\/\\/)?(?:www\\.)?(?:youtube\\.com\\/.*[?&]v=|youtu\\.be\\/)([a-zA-Z0-9_-]{11})";
	        
	        Pattern pattern = Pattern.compile(youtubeUrlRegex);
	        Matcher matcher = pattern.matcher(url);

	        if (matcher.find()) {
	            return matcher.group(1);  // Trả về ID video (11 ký tự)
	        } else {
	            return null;  // Không tìm thấy video ID
	        }
	    }
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String url=req.getRequestURI();
		
		if (url.contains("/videos")) {
			List<Video> list =vidService.findAll();
			
			req.setAttribute("listvid", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/video/video_cate")) {
			int id= Integer.parseInt(req.getParameter("id"));
			List<Video> list =vidService.findVideosByCategoryId(id);
			req.setAttribute("listvid1", list);
			req.setAttribute("cateid", id);
			req.getRequestDispatcher("/views/admin/video-list-cate.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/video/delete")) {
			
			int id= Integer.parseInt(req.getParameter("id"));
			try {
				vidService.deleteVideo(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath()+"/admin/videos");
			
		}
		else if (url.contains("/admin/video/add")) {
			int cateid= Integer.parseInt(req.getParameter("cateid"));
			req.setAttribute("cateid", cateid);
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/video/edit")) {
			
			int id= Integer.parseInt(req.getParameter("id"));
			Video vid=vidService.findVideoById(id);
			String videoID = getYouTubeVideoID(vid.getVideoclip());
			String srcvideo="https://www.youtube.com/embed/"+videoID;
			req.setAttribute("vid", vid);
			req.setAttribute("srcvideo", srcvideo);
			req.setAttribute("cate", vid.getCategory());
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/video/watchvideo")) {
			
			int id= Integer.parseInt(req.getParameter("id"));
			Video vid=vidService.findVideoById(id);
			vidService.increaseViews(id);
			String videoID = getYouTubeVideoID(vid.getVideoclip());
			String srcvideo="https://www.youtube.com/embed/"+videoID;
			req.setAttribute("vid", vid);
			req.setAttribute("srcvideo", srcvideo);
			req.setAttribute("cate", vid.getCategory());
			req.getRequestDispatcher("/views/admin/video-clip.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String url=req.getRequestURI();
		
		if (url.contains("/admin/video/insert")) {
			String title=req.getParameter("title");
			String description=req.getParameter("description");
			int cateid=Integer.parseInt(req.getParameter("categoryid"));
			String active = req.getParameter("active");
			int actives=Integer.parseInt(active);
			String posters = req.getParameter("images");
			String videos = req.getParameter("videos");
			Video vid=new Video();
			vid.setTitle(title);
			vid.setDescription(description);
			vid.setActive(actives);
			Category cate=new Category();
			cate=cateService.findById(cateid);
			vid.setCategory(cate);
			

			
			String fname="";
			String vname="";
			String uploadPath= UPLOAD_DIRECTORY;
			File uploadDir= new File(uploadPath);
			if (uploadDir.exists()) {
				uploadDir.mkdir();
			}
			///////
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
					vid.setPoster(fname);
				}else if (posters != null) {
					 vid.setPoster(posters);}
				else {
					vid.setPoster("back.jpg");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			////////////
			try {
				Part part =req.getPart("video");
				if (part.getSize()>0) {
					String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();
					//đổi tên file
					int index=filename.lastIndexOf(".");
					String ext=filename.substring(index+1);
					vname=System.currentTimeMillis()+"."+ext;
					//upload file
					part.write(uploadPath+"/"+vname);
					//ghi ten file vao data
					vid.setVideoclip(vname);
				}else {
					vid.setVideoclip(videos);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//////////////////
			
			
			vidService.addVideo(vid);
			resp.sendRedirect(req.getContextPath()+"/admin/videos");
		}
		else if (url.contains("/admin/video/update")) {
			
			String title=req.getParameter("title");
			String description=req.getParameter("description");
			int cateid=Integer.parseInt(req.getParameter("cateid"));
			int videoid=Integer.parseInt(req.getParameter("videoId"));
			String active = req.getParameter("active");
			int actives=Integer.parseInt(active);
			String posters = req.getParameter("images");
			
			Video vid=new Video();
			vid.setTitle(title);
			vid.setDescription(description);
			vid.setActive(actives);
			vid.setVideoId(videoid);
			Category cate=new Category();
			cate=cateService.findById(cateid);
			vid.setCategory(cate);
			
			
			//luu hinh cuz
			Video videoold= vidService.findVideoById(videoid);
			String fileold =videoold.getPoster();
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
					vid.setPoster(fname);
					
					 if (!vid.getPoster().substring(0, 5).equals("https") ) {
					 deleteFile(uploadPath+ "\\" + fileold);
					 }
				}else if (posters != null) {
					 vid.setPoster(posters);
					 }
				else {
					vid.setPoster(fileold);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			vidService.updateVideo(vid);
			resp.sendRedirect(req.getContextPath()+"/admin/videos");
			
		}
		else if (url.contains("/admin/video/search")) {
			String vidtitle= req.getParameter("search");
		
			List<Video> list =vidService.searchByTitle(vidtitle);
			if (!list.isEmpty()) {
				req.setAttribute("titlesearch", vidtitle);
			}
			else req.setAttribute("titlesearch", "No results matched");
			req.setAttribute("listvid2", list);
			req.getRequestDispatcher("/views/admin/video-list-result-search.jsp").forward(req, resp);
		}
		
	}
	
	


}

