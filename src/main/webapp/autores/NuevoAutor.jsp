<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ingreso de Autores</title>
</head>
<body>
	<%
	String url = "http://localhost:8080/Prueba18/";
	%>
	<h3>Nuevo Autor</h3>
	<form role="form" action="<%=url%>AutorControllers" method="POST">
		<input type="hidden" name="op" value="insertar" /> <label for="nombre">
			Nombre del Autor</label> <br> <input type="text" name="nombre" id="nombre" /> <br>
			
			<label
			for="nacionalidad"> Nacionalidad del Autor</label> <br> <input type="text"
			name="nacionalidad" id="nacionalidad" /><br> <input type="submit"
			value="Guardar" name="Guardar">  <a
			href="<%=url%>AutorControllers?op=listar">Retorno</a>
	</form>

</body>
</html>