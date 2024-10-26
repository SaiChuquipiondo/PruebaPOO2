
<%@page import="com.beans.Autor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% String url = "http://localhost:8080/Prueba18/";%> 
	<a type = "button" href = "<%= url %>AutorControllers?op=nuevo">Nuevo Autor</a>
<table id = "tabla" border="1">
	<thead>
		<tr>
			<th>Codigo del autor</th>
			<th>nombre del autor</th>
			<th>nacionalidad</th>
			<th>operaciones</th>
		</tr>
	</thead>
	
	<tbody>
	<%
	List<Autor> listaAutores =(List<Autor>) request.getAttribute("listaAutores");
	if(listaAutores != null){
		for(Autor autor :listaAutores){
		%>	
		<tr>
				<td><%= autor.getId() %></td>
				<td><%= autor.getNombre() %></td>
				<td><%= autor.getPais() %></td>
				<td>
				<a href = "<%= url %>AutorControllers?op=obtner&id=<%=autor.getId()%>">Modificar</a>	
				</td>		
		</tr>
	<%
		}
	}else{
	%>
		<tr>
			<td>no hay datos</td>
			<td>no hay datos</td>
			<td>no hay datos</td>
			<td></td>
		</tr>
	<%
	}
	%>
		

	
	</tbody>
</table>
</body>
</html>