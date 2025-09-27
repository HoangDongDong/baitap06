<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Category Form</title>
</head>
<body>
	<h2>${category == null ? "Thêm mới Category" : "Chỉnh sửa Category"}</h2>

	<form method="post" action="categories">
		<input type="hidden" name="action"
			value="${category == null ? 'create' : 'update'}" />
		<c:if test="${category != null}">
			<input type="hidden" name="id" value="${category.id}" />
		</c:if>

		Name: <input type="text" name="name"
			value="${category != null ? category.name : ''}" required /><br />
		Icons: <input type="text" name="icons"
			value="${category != null ? category.icons : ''}" /><br />

		<!-- Chọn user cho category -->
		User: <select name="userId" required>
			<c:forEach var="u" items="${users}">
				<option value="${u.id}"
					${category != null && category.user.id == u.id ? "selected" : ""}>
					${u.username}</option>
			</c:forEach>
		</select><br />

		<button type="submit">Lưu</button>
		<a href="categories?action=list">Hủy</a>
	</form>

</body>
</html>