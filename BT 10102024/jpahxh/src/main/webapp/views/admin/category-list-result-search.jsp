<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/admin/category/search" method="post">
  <label for="gsearch">Find Category Name:</label>
  <input type="search" id="search" name="search">
  <input type="submit" value="Find" >
</form>
<a href="<c:url value='/admin/categories'/>">Back to Categories List</a></br>
<label for="categoryname">Result Category Search:</label>
<label id="categoryname" >${catesearch}</label><br>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Image</th>
		<th>Category Id</th>
		<th>Category Name</th>
		<th>Status</th>
		<th>Action</th>
	</tr>

	<c:forEach items="${listcate1}" var="cate1" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			
			<c:if test="${cate1.image.substring(0,5)=='https'}">
				<c:url value="${cate1.image}" var="imgUrl"></c:url>
			</c:if>
			
			<c:if test="${cate1.image.substring(0,5)!='https'}">
				<c:url value="/image?fname=${cate1.image }" var="imgUrl"></c:url>
			</c:if>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${cate1.categoryid}</td>
			<td>${cate1.categoryname}</td>
			<td>
			<c:if test="${cate1.status==1}">
				<span>Hoạt động</span>
				</c:if>
			<c:if test="${cate1.status!=1}">
				<span>Khóa</span>
				</c:if>
			</td>

			<td><a
				href="<c:url value='/admin/category/edit?id=${cate1.categoryid}'/>">Edit</a>
				<a
				href="<c:url value='/admin/category/delete?id=${cate1.categoryid }'/>">Delete</a>
				<a
				href="<c:url value='/admin/video/video_cate?id=${cate1.categoryid }'/>">List Video</a>
			</td>
		</tr>
	</c:forEach>


</table>