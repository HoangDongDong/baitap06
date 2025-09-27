<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<title>User Form</title>
</head>
<body>
	<h2>${user == null ? "Thêm mới User" : "Chỉnh sửa User"}</h2>

	<form method="post" action="users">
		<input type="hidden" name="action"
			value="${user == null ? 'create' : 'update'}" />
		<c:if test="${user != null}">
			<input type="hidden" name="id" value="${user.id}" />
		</c:if>

		Username: <input type="text" name="username"
			value="${user != null ? user.username : ''}" required /><br />
		Password: <input type="password" name="password" value=""
			${user == null ? "required" : ""} /><br /> RoleId: <input
			type="number" name="roleId"
			value="${user != null ? user.roleId : ''}" required /><br />

		<button type="submit">Lưu</button>
		<a href="users?action=list">Hủy</a>
	</form>

</body>
</html>