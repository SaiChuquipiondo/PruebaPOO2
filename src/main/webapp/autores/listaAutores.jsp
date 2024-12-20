
<%@page import="com.beans.Autor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="assets/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="assets/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>

<body>

	<%@ include file="/cabecera.jsp" %>

	<div class="container my-5">
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h2 class="fw-bold text-primary">Autores</h2>
			<a type="button" class="btn btn-success btn-lg"
				href="<%=url%>AutorControllers?op=nuevo">Agregar Nuevo
				Autor</a>
		</div>
		<div class="card shadow-lg border-0 rounded-4">
			<div class="card-body p-4">
				<table class="table table-hover">
					<thead class="table-primary">
						<tr>
							<th scope="col">Codigo</th>
							<th scope="col">Nombre</th>
							<th scope="col">Nacionalidad</th>
							<th scope="col">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<Autor> listaAutor = (List<Autor>) request.getAttribute("listaAutores");
						if (listaAutor != null) {
							for (Autor autor : listaAutor) {
						%>
						<tr>
							<td><%= autor.getId() %></td>
							<td><%=autor.getNombre()%></td>
							<td><%=autor.getPais()%></td>							
							<td><a type="button" class="btn btn-warning btn-sm me-1"
								href="<%=url%>AutorControllers?op=obtener&id=<%=autor.getId()%>">Modificar</a>
								<a type="button" class="btn btn-danger btn-sm"
								href="<%=url%>AutorControllers?op=eliminar&id=<%=autor.getId()%>">Eliminar</a>
							</td>
						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td>no hay nada</td>
							<td>no hay nada</td>
							<td>no hay nada</td>
							<td>no hay nada</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script
		src="assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>