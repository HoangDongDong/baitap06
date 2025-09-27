<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Quản lý Category</title>
</head>
<body>
	<h2>Danh sách Category</h2>

	<form method="get" action="categories">
		<input type="hidden" name="action" value="search" /> <input
			type="text" name="keyword" placeholder="Tìm category..." />
		<button type="submit">Tìm</button>
	</form>

	<a href="categories?action=createForm">Thêm Category</a>
	<br />
	<br />

	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Icons</th>
			<th>User</th>
			<th>Action</th>
		</tr>
		<c:forEach var="c" items="${categories}">
			<tr>
				<td>${c.id}</td>
				<td>${c.name}</td>
				<td>${c.icons}</td>
				<td>${c.user.username}</td>
				<td><a href="categories?action=editForm&id=${c.id}">Sửa</a> | <a
					href="categories?action=delete&id=${c.id}"
					onclick="return confirm('Xóa category này?')">Xóa</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>