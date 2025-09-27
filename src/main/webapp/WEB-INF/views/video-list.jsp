<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Video Management</title>
</head>
<body>
	<h2>Danh sách Video</h2>

	<form action="video" method="get">
		<input type="hidden" name="action" value="search" /> <input
			type="text" name="keyword" placeholder="Tìm theo tiêu đề" />
		<button type="submit">Tìm kiếm</button>
	</form>

	<a href="video?action=new">+ Thêm mới</a>
	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th>ID</th>
			<th>Tiêu đề</th>
			<th>URL</th>
			<th>Mô tả</th>
			<th>Active</th>
			<th>Hành động</th>
		</tr>
		<c:forEach var="v" items="${list}">
			<tr>
				<td>${v.id}</td>
				<td>${v.title}</td>
				<td>${v.url}</td>
				<td>${v.description}</td>
				<td>${v.active}</td>
				<td><a href="video?action=edit&id=${v.id}">Sửa</a> | <a
					href="video?action=delete&id=${v.id}"
					onclick="return confirm('Xóa?')">Xóa</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>