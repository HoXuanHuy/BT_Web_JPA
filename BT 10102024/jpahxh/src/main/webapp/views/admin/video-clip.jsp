<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>



<form enctype="multipart/form-data">
	<input type="text" id="videoId" name="videoId" value="${vid.videoId}" hidden="hidden" ><br> 
	<input type="text" id="cateid" name="cateid" value="${cate.categoryid}"hidden="hidden" ><br> 
	
	
	<label for="title">Title Video:</label>
	<label id="title" >${vid.title}</label><br>
	
	<label for="description">Description Video:</label>
	<label id="description" >${vid.description}</label><br>
	
	<label for="poster">Poster:</label><br> 
	<c:if test="${vid.poster.substring(0,5)=='https'}">
				<c:url value="${vid.poster }" var="imgUrl"></c:url>
			</c:if>
			
			<c:if test="${vid.poster.substring(0,5)!='https'}">
				<c:url value="/image?fname=${vid.poster }" var="imgUrl"></c:url>
			</c:if>
	<img id="image" height="150" width="200" src="${imgUrl}"/><br>
	
	<label for="video">Video:</label><br> 
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

</form>