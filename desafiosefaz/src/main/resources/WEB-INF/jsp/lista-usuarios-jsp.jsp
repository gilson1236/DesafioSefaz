<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listando Usuários</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<table border="1">
		<c:forEach var="usuario" items="${usuarios}">
			<tr bgcolor="#${id.count %2 == 0? 'aaee88' : 'ffffff' }">
				<td>${usuario.nome}</td>
				<td><a
					href="mvc?logica=SelecionaUsuarioLogica&id=${usuario.id}">Alterar</a>
				</td>
				<td><a href="mvc?logica=RemoveUsuarioLogica&id=${usuario.id}">Remover</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="mvc?logica=SelecionaUsuarioLogica">Adicionar um novo Usuário</a>
</body>
</html>