<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<form action="${pageContext.request.contextPath}/admin/video/search" method="post">
  <label for="gsearch">Find Video Title:</label>
  <input type="search" id="search" name="search">
  <input type="submit" value="Find" >
</form>

<a href="<c:url value='/admin/categories'/>">Back to Categories List</a></br>
<label for="title">Result Video Search:</label>
<label id="title" >${titlesearch}</label><br>


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

	<c:forEach items="${listvid2}" var="vid2" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			
			<c:if test="${vid2.poster.substring(0,5)=='https'}">
				<c:url value="${vid2.poster}" var="imgUrl"></c:url>
			</c:if>
			
			<c:if test="${vid2.poster.substring(0,5)!='https'}">
				<c:url value="/image?fname=${vid2.poster }" var="imgUrl"></c:url>
			</c:if>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${vid2.category.categoryid}</td>
			<td>${vid2.videoId}</td>
			<td>${vid2.title}</td>
			<td>${vid2.description}</td>
			<td>${vid2.views}</td>
			<td>
			<c:if test="${vid2.active==1}">
				<span>Hoạt động</span>
				</c:if>
			<c:if test="${vid2.active!=1}">
				<span>Khóa</span>
				</c:if>
			</td>
			<td><a
				href="<c:url value='/admin/video/watchvideo?id=${vid2.videoId}'/>">Watch</a>
				<a
				href="<c:url value='/admin/video/edit?id=${vid2.videoId}'/>">Edit</a>
				<a
				href="<c:url value='/admin/video/delete?id=${vid2.videoId}'/>">Delete</a>
			</td>

		</tr>
	</c:forEach>


</table>