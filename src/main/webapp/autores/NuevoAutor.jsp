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
			Nombre del Autor</label> <br> 
			<div class="input-class">
			<input type="text" class="form-control" name="nombre" id="nombre" placeholder="Ingrese su nombre" />
			<span class="input-class-addon"><span class="glyphicon-asterisk"></span></span> 
			</div>
			<br>
			<div class="form-group">
			<label
			for="nacionalidad"> Nacionalidad del Autor</label>
			 <br> 
			 <div class="input-group">
			 <input type="text" class="form-control"
			name="nacionalidad" id="nacionalidad" placeholder="Ingrese su nacionalidad" />
			<span class="input-group-addon"><span
					class="glyphicon glyphicon-asterisk"></span></span>
 
			</div>
			</div>
			<input type="submit"
			value="Guardar" name="Guardar">  <a
			href="<%=url%>AutorControllers?op=listar">Retorno</a>
	</form>

</body>
</html>