<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Atualizar Usuario</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	Formulário para alteração de usuarios:
	<br />
	<form action="mvc?logica=AlteraUsuariooLogica" method="POST">
		Id:<input type="text" name="id" value="${usuario.id}" readonly="readonly" /><br /> 
		Nome:<input type="text" name="nome" value="${usuario.nome }" /><br /> 
		E-mail:<input type="text" name="email" value="${usuario.email }" /><br /> 
		Senha:<input type="password" name="senha" value="${usuario.senha }" /><br /> 
		<input type="hidden" name="_method" value="PUT" /> <input type="submit" value="Enviar" />
	</form>
</body>
</html>