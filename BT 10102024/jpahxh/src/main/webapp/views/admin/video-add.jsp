<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<form action="${pageContext.request.contextPath}/admin/video/insert" method="post"enctype="multipart/form-data">
	<input type="text" id="categoryid" name="categoryid" value="${cateid}" hidden="hidden" ><br> 
	<label for="title">Title Video:</label>
	<input type="text"	id="title" name="title" ><br>
	
	<label for="categoryname">Description:</label><br> 
	<input type="text"	id="description" name="description" ><br> 
	
	<label for="image">Poster:</label><br> 
	<label for="lname">Link Poster:</label>
	<input type="text" id="images" name="images"><br>
	
	<label for="image">Or Upload Poster:</label><br> 
	<input type="file" onchange="chooseFile(this)" id="image"name="image" ><br>
	<img  height="150" width="200" src=""id="image"/><br>
	
	<label for="lname">Video:</label><br>
	<label for="lname">Link Video:</label>
	<input type="text" id="videos" name="videos"><br>
	
	<label for="image">Or Upload Video:</label><br> 
	<input type="file" name="video" accept="video/*" ><br>
	
	
	<p>Active</p>
  <input type="radio" id="ston" name="active" value=1>
  <label for="html">Hoạt động</label><br>
  <input type="radio" id="stoff" name="active" value=0>
  <label for="css">Khóa</label><br>
	<br> <input type="submit" value="Add">
	</form>
