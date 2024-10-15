<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>



<form action="${pageContext.request.contextPath}/admin/video/update" method="post" enctype="multipart/form-data">
	<input type="text" id="videoId" name="videoId" value="${vid.videoId}" hidden="hidden"><br> 
	<input type="text" id="cateid" name="cateid" value="${cate.categoryid}" hidden="hidden"><br> 
	
	
	<label for="title">Title Video:</label><br> 
	<input type="text" id="title" name="title" value="${vid.title}"><br> 
	
	<label for="description">Description Video:</label><br> 
	<input type="text" id="description" name="description" value="${vid.description}"><br> 
	
	<label for="poster">Poster:</label><br> 

<label for="lname">Link poster:</label><br>
<input type="text" id="images" name="images" value="${vid.poster}"><br>	
<c:if test="${vid.poster.substring(0,5)=='https'}">
				<c:url value="${vid.poster }" var="imgUrl"></c:url>
			</c:if>
			
			<c:if test="${vid.poster.substring(0,5)!='https'}">
				<c:url value="/image?fname=${vid.poster }" var="imgUrl"></c:url>
			</c:if>
	<label for="lname">Or Upload File:</label><br>		
	<input type="file" onchange="chooseFile(this)" id="image"name="image" ><br>
	
	<img id="image" height="150" width="200" src="${imgUrl}"/><br>		
	
		
	<label for="video">Video:</label><br> 
	<label for="lname">Link Video:</label>
	<input type="text" id="videos" name="videos"><br>
	
	<label for="image">Or Upload Video:</label><br> 
	<input type="file" name="video" accept="video/*" ><br>
	
	<c:if test="${vid.videoclip.substring(0,5)=='https'}">
				<iframe width="640" height="360" src=${ srcvideo } ></iframe>
			</c:if>
			
			<c:if test="${vid.videoclip.substring(0,5)!='https'}">
				<c:url value="/video?fname=${vid.videoclip }" var="vidUrl"></c:url>
					<td>
   						<video width="640" height="360" controls>
        					<source src="${vidUrl}" type="video/mp4">
    					</video>
					</td>
			</c:if>

			

	
	<p>Active</p>
  <input type="radio" id="ston" name="active" value=1 ${vid.active==1?'checked':''} >
  <label for="html">Đang hoạt động</label><br>
  <input type="radio" id="stoff" name="active" value=0 ${vid.active!=1?'checked':''}>
  <label for="css">Khóa</label><br>

<input type="submit" value="Update">
	
	
</form>