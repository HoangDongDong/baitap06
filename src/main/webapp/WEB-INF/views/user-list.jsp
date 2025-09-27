<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Quản lý User</title>
</head>
<body>
	<h2>Danh sách User</h2>

	<form method="get" action="users">
		<input type="hidden" name="action" value="search" /> <input
			type="text" name="keyword" placeholder="Tìm username..." />
		<button type="submit">Tìm</button>
	</form>

	<a href="users?action=createForm">Thêm User</a>
	<br />
	<br />

	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th>ID</th>
			<th>Username</th>
			<th>Role</th>
			<th>Action</th>
		</tr>
		<c:forEach var="u" items="${users}">
			<tr>
				<td>${u.id}</td>
				<td>${u.username}</td>
				<td>${u.roleId}</td>
				<td><a href="users?action=editForm&id=${u.id}">Sửa</a> | <a
					href="users?action=delete&id=${u.id}"
					onclick="return confirm('Xóa user này?')">Xóa</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>