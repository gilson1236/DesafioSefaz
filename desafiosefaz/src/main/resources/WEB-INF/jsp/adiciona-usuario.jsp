<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adicionar Contato</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
</script>

</head>
<body>

<script>
function duplicarCampos(){
	var clone = document.getElementById('telefone').cloneNode(true);
	var destino = document.getElementById('telefoneAdicionado');
	destino.appendChild (clone);
	
	var camposClonados = clone.getElementsByTagName('input');
	
	for(i=0; i<camposClonados.length;i++){
		camposClonados[i].value = '';
	}
}
</script>
	Formulário para Adicionar um Novo Contato
	<form action="mvc?logica=AdicionaUsuarioLogica" method="POST">
		Nome: <input type="text" name="nome" /><br /> E-Mail: <input
			type="text" name="email" /><br /> Senha: <input type="password"
			name="senha" /><br /> Telefone:
		<div id="telefone">
			<input type="tel" class="campo" id="telefone1" name="telefone 1"
				placeholder="99-99999-9999" pattern="[0-9]{2}-[0-9]{5}-[0-9]{4}" />
			<input type="radio" id="telFixo" name="telFixo" value="telFixo">
			<label for="telFixo">Telefone Fixo</label> <input type="radio"
				id="telCel" name="telCel" value="telCel"> <label
				for="telCel">Telefone Celular</label><br /> <img
				src="../image/add.gif" style="cursor: pointer;"
				onClick="duplicarCampos();" > <br /> <br />
		</div>

		<div id="telefoneAdicionado"></div>
		<br />
		<br />
		<br /> <input type="submit" value="Gravar" />
	</form>
</body>
</html>