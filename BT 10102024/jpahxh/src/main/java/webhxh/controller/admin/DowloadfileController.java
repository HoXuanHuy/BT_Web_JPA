package webhxh.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webhxh.utils.Constant;

@WebServlet(urlPatterns = {"/image","/video"})
public class DowloadfileController extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String url=req.getRequestURI();
	String fileName = req.getParameter("fname");
	File file = new File(Constant.UPLOAD_DIRECTORY + "/" + fileName);
	
	if (url.contains("/image"))
	resp.setContentType("image/jpeg");
	else if (url.contains("/video"))
		resp.setContentType("video/mp4");
	if (file.exists()) {
		IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
	}
	}

}
