<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<form action="${pageContext.request.contextPath}/admin/category/insert" method="post"enctype="multipart/form-data">
	<label for="categoryname">Category Name:</label><br> 
	<input type="text"	id="categoryname" name="categoryname" ><br> 
	
	<label for="lname">Link images:</label><br>
	<input type="text" id="images" name="images"><br>
	<label for="upload">Or Upload File:</label><br>
	<input type="file" onchange="chooseFile(this)" id="image"name="image" ><br>
	<img  height="150" width="200" src=""id="image"/>
	
	
	<p>Status</p>
  <input type="radio" id="ston" name="status" value=1 } >
  <label for="html">Hoạt động</label><br>
  <input type="radio" id="stoff" name="status" value=0 }>
  <label for="css">Khóa</label><br>
	<br> <input type="submit" value="Add">
	</form>
