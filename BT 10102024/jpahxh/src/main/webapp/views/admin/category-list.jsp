<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<form action="${pageContext.request.contextPath}/admin/category/search" method="post">
  <label for="gsearch">Find Category Name:</label>
  <input type="search" id="search" name="search">
  <input type="submit" value="Find" >
</form>



<a href="${pageContext.request.contextPath}/admin/category/add">Add Category</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>

		<th>Image</th>
		<th>Category Id</th>
		<th>Category Name</th>
		<th>Status</th>
		<th>Action</th>
	</tr>

	<c:forEach items="${listcate}" var="cate" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			
			<c:if test="${cate.image.substring(0,5)=='https'}">
				<c:url value="${cate.image}" var="imgUrl"></c:url>
			</c:if>
			
			<c:if test="${cate.image.substring(0,5)!='https'}">
				<c:url value="/image?fname=${cate.image }" var="imgUrl"></c:url>
			</c:if>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${cate.categoryid}</td>
			<td>${cate.categoryname}</td>
			<td>
			<c:if test="${cate.status==1}">
				<span>Hoạt động</span>
				</c:if>
			<c:if test="${cate.status!=1}">
				<span>Khóa</span>
				</c:if>
			</td>

			<td><a
				href="<c:url value='/admin/category/edit?id=${cate.categoryid}'/>">Edit</a>
				<a
				href="<c:url value='/admin/category/delete?id=${cate.categoryid }'/>">Delete</a>
				<a
				href="<c:url value='/admin/video/video_cate?id=${cate.categoryid }'/>">List Video</a>
			</td>
		</tr>
	</c:forEach>


</table>