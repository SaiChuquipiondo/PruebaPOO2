<%@page import="com.beans.Autor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	String url = "http://localhost:8080/Prueba18/";
	
	Autor autor;
	if(request.getAttribute("autor")==null)
		autor = new Autor();
	else{
		autor = (Autor)request.getAttribute("autor");
	}
	
	%>
	<h1><em><strong>EDITAR AUTOR</strong></em></h1>
	<form role="form" action="<%=url%>AutorControllers" method="POST">
	
		<input type="hidden" name="op" value="editar" /> 
		<input type="hidden" name="id" value="<%= autor.getId() %>" /> 
		<label for="nombre">
			Nombre del Autor</label> <br> 
			<input type="text" name="nombre" id="nombre" value="<%= autor.getNombre() %>" /> <br>
		<label for="nacionalidad">
				 Nacionalidad del Autor</label> <br>
		 <input type="text" name="nacionalidad" id="nacionalidad" value = "<%=autor.getPais() %>"/><br> 
		 
		 <input type="submit" class="btn btn-info"
			value="Editar" name="Guardar">  <a class="btn btn-danger"
			href="<%=url%>AutorControllers?op=listar">CANCELAR</a>
	</form>

</body>
</html>