<%@page import="com.beans.Editorial"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tabla Editorial</title>
<!-- Bootstrap CSS -->
<link 
	href="assets/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<%@ include file="/cabecera.jsp" %>
	<div class="container my-5">
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h2 class="fw-bold text-primary">Editorial</h2>
			<a type="button" class="btn btn-success btn-lg"
				href="<%=url%>EditorialControllers?op=nuevo">Agregar Nuevo
				Editorial</a>
		</div>
		<div class="card shadow-lg border-0 rounded-4">
			<div class="card-body p-4">
				<table class="table table-hover">
					<thead class="table-primary">
						<tr>
							<th scope="col">Codigo</th>
							<th scope="col">Nombre</th>
							<th scope="col">Contacto</th>
							<th scope="col">Teléfono</th>
							<th scope="col">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<Editorial> listaEditorial = (List<Editorial>) request.getAttribute("listaEditorial");
						if (listaEditorial != null) {
							for (Editorial edi : listaEditorial) {
						%>
						<tr>
							<td><%= edi.getIdEditorial() %></td>
							<td><%=edi.getNombre()%></td>
							<td><%=edi.getContacto()%></td>
							<td><%=edi.getTelefono()%></td>
							<td><a type="button" class="btn btn-warning btn-sm me-1"
								href="<%=url%>EditorialControllers?op=obtener&id=<%=edi.getIdEditorial()%>">Modificar</a>
								<a type="button" class="btn btn-danger btn-sm"
								href="<%=url%>EditorialControllers?op=eliminar&id=<%=edi.getIdEditorial()%>">Eliminar</a>
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
