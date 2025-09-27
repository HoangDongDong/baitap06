<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Video Form</title>
</head>
<body>
	<h2>${video != null ? "Cập nhật Video" : "Thêm Video"}</h2>

	<form action="video" method="post">
		<input type="hidden" name="id"
			value="${video != null ? video.id : ''}" /> Tiêu đề: <input
			type="text" name="title" value="${video != null ? video.title : ''}"
			required /><br /> URL: <input type="text" name="url"
			value="${video != null ? video.url : ''}" required /><br /> Mô tả:
		<textarea name="description">${video != null ? video.description : ''}</textarea>
		<br /> Active: <input type="checkbox" name="active"
			${video != null && video.active ? "checked" : ""} /><br />
		<button type="submit">Lưu</button>
	</form>

	<a href="video">Quay lại</a>
</body>
</html>