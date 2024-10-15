<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<form action="${pageContext.request.contextPath}/admin/video/search" method="post">
  <label for="gsearch">Find Video Title:</label>
  <input type="search" id="search" name="search">
  <input type="submit" value="Find" >
</form>

<a href="<c:url value='/admin/categories'/>">Back to Categories List</a></br>

<table border="1" width="100%">
	<tr>
		<th>STT</th>

		<th>Poster</th>
		<th>Category Id</th>
		<th>Video Id</th>
		<th>Title</th>
		<th>Description</th>
		<th>Views</th>
		<th>Active</th>
		<th>Action</th>
	</tr>

	<c:forEach items="${listvid}" var="vid" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			
			<c:if test="${vid.poster.substring(0,5)=='https'}">
				<c:url value="${vid.poster}" var="imgUrl"></c:url>
			</c:if>
			
			<c:if test="${vid.poster.substring(0,5)!='https'}">
				<c:url value="/image?fname=${vid.poster }" var="imgUrl"></c:url>
			</c:if>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${vid.category.categoryid}</td>
			<td>${vid.videoId}</td>
			<td>${vid.title}</td>
			<td>${vid.description}</td>
			<td>${vid.views}</td>
			<td>
			<c:if test="${vid.active==1}">
				<span>Hoạt động</span>
				</c:if>
			<c:if test="${vid.active!=1}">
				<span>Khóa</span>
				</c:if>
			</td>
			<td><a
				href="<c:url value='/admin/video/watchvideo?id=${vid.videoId}'/>">Watch</a>
				<a
				href="<c:url value='/admin/video/edit?id=${vid.videoId}'/>">Edit</a>
				<a
				href="<c:url value='/admin/video/delete?id=${vid.videoId}'/>">Delete</a>
			</td>

		</tr>
	</c:forEach>


</table>