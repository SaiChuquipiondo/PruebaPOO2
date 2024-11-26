<%@page import="com.beans.Libro"%>
<%@page import="com.beans.Genero"%>
<%@page import="com.beans.Editorial"%>
<%@page import="com.beans.Autor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Editar Libro</title>
<!-- Bootstrap CSS -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<script>
	function validarFormulario() {
		const res = confirm("Esta seguro de guardar");
		if (res) {
			const nombre = document.getElementById("nombre").value.trim();
			const existencia = document.getElementById("existencia").value
					.trim();
			const precio = document.getElementById("precio").value.trim();
			const descripcion = document.getElementById("descripcion").value
					.trim();

			if (nombre === '') {
				alert("ingresar nombre");
				document.getElementById("nombre").focus();
				return false
			}
			if (existencia === '') {
				alert("Ingresar existencia");
				document.getElementById("existencia").focus();
				return false;
			}

			if (precio === '') {
				alert("Ingresar Precio");
				document.getElementById("precio").focus();
				return false
			}

			if (descripcion === '') {
				alert("Ingresar Descripcion");
				document, getElementById("descripcion").focus();
				return false;
			}
			if (Number(existencia) < 0) {
				alert("La existencia no puede ser numero negativo");
				document.getElementById("existencia").focus();
				return false;
			}
			if (Number(precio) < 0) {
				alert("El precio no puede ser negativo");
				document.getElementById("precio").focus();
				return false
			}
			return true;
		} else {
			return false;
		}
	}
</script>


</head>
<body>

	<%
	Libro libro;
	if (request.getAttribute("Libro") == null) {
		libro = new Libro();
	} else {
		libro = (Libro) request.getAttribute("Libro");
	}
	%>
	<%@ include file="/cabecera.jsp"%>

	<%
	if (request.getAttribute("respuesta") != null) {
		boolean res = (boolean) request.getAttribute("respuesta");
		if (res) {
			List<String> Error = (List<String>) request.getAttribute("listaError");

			for (String error : Error) {
	%>
	<span><%=error%></span>
	<br>
	<%
	}
	}
	}
	%>

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow-lg border-0 rounded-4">
					<div class="card-body p-5">
						<h2 class="text-center mb-4 fw-bold text-primary">Editar
							Libro</h2>
						<form role="form" action="<%=url%>LibroControllers" method="post"
							onsubmit="return validarFormulario()">
							<input type="hidden" name="op" value="editar"> <input
								type="hidden" name="id" value="<%=libro.getIdLibro()%>">
							<!-- Nombre del libro -->
							<div class="form-group">
								<label for="nombreLibro">Nombre del Libro</label> <input
									type="text" class="form-control" id="nombre" name="nombre"
									value="<%=libro.getNombre()%>">
							</div>

							<!-- Existencia -->
							<div class="form-group">
								<label for="existencia">Existencia</label> <input type="number"
									class="form-control" id="existencia" name="existencia"
									value="<%=libro.getExistencia()%>">
							</div>

							<!-- Precio -->
							<div class="form-group">
								<label for="precio">Precio</label> <input type="number"
									step="0.01" class="form-control" id="precio" name="precio"
									value="<%=libro.getPrecio()%>">
							</div>
							<div class="form-group">
								<label for="descripcion">Descripcion</label> <input type="text"
									class="form-control" id="descripcion" name="descripcion"
									value="<%=libro.getDescripcion()%>">
							</div>
							<!-- Lista de Autores -->
							<div class="form-group">
								<label for="autor">Autor</label> <select class="form-control"
									name="autor" id="autor">
									<%
									List<Autor> listaAutor = (List<Autor>) request.getAttribute("listaAutor");

									if (listaAutor != null) {
										for (Autor autor : listaAutor) {
											if (autor.getId() == libro.getAutor().getId()) {
									%>
									<option value="<%=autor.getId()%>" selected><%=autor.getId() + ". " + autor.getNombre()%></option>

									<%
									} else {
									%>
									<option value="<%=autor.getId()%>"><%=autor.getId() + ". " + autor.getNombre()%></option>
									<%
									}
									}
									} else {
									%>
									<option>No hay autores disponibles</option>
									<%
									}
									%>
								</select>
							</div>
							<!-- Lista de Editoriales -->
							<div class="form-group">
								<label for="editorial">Editorial</label> <select
									class="form-control" name="editorial" id="editorial">
									<%
									List<Editorial> listaEditorial = (List<Editorial>) request.getAttribute("listaEditorial");

									if (listaEditorial != null) {
										for (Editorial edi : listaEditorial) {
											if (edi.getIdEditorial() == libro.getEditorial().getIdEditorial()) {
									%>
									<option value="<%=edi.getIdEditorial()%>" selected="selected">
										<%=edi.getIdEditorial() + ". " + edi.getNombre()%></option>
									<%
									} else {
									%>

									<option value="<%=edi.getIdEditorial()%>">
										<%=edi.getIdEditorial() + ". " + edi.getNombre()%></option>
									<%
									}
									}
									} else {
									%>
									<option>No hay editoriales disponibles</option>
									<%
									}
									%>
								</select>
							</div>

							<!-- Lista de Géneros -->
							<div class="form-group">
								<label for="genero">Genero</label> <select class="form-control"
									name="genero" id="genero">
									<%
									List<Genero> listaGenero = (List<Genero>) request.getAttribute("listaGenero");

									if (listaGenero != null) {
										for (Genero gene : listaGenero) {
											if (gene.getIdgenero() == libro.getGenero().getIdgenero()) {
									%>
									<option value="<%=gene.getIdgenero()%>" selected="selected"><%=gene.getIdgenero() + ". " + gene.getNombre()%></option>
									<%
									} else {
									%>
									<option value="<%=gene.getIdgenero()%>"><%=gene.getIdgenero() + ". " + gene.getNombre()%></option>
									<%
									}
									}
									} else {
									%>
									<option>No hay Genero disponibles</option>
									<%
									}
									%>
								</select>
							</div>

							<div class="d-grid gap-2 d-md-flex justify-content-md-end">
								<a type="reset" class="btn btn-outline-secondary btn-lg"
									href="<%=url%>LibroControllers?op=listar">Cancelar</a>
								<button type="submit" class="btn btn-primary btn-lg fw-semibold">Registrar</button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="assets/js/bootstrap.min.js"></script>


</body>
</html>