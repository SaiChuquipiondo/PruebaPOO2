<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ingreso de Autores</title>
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<script src="assets/js/bootstrap.min.js"></script>

<script>
	function validarFormulario() {
		const respuesta = confirm('¿Estas seguro  de guardar?')
		if(respuesta){
		const nombre = document.getElementById('nombre').value.trim();
		const nacionalidad = document.getElementById('nacionalidad').value.trim();
		
		if(nombre===''){
			alert('Ingrese el nombre del autor');
			document.getElementById('nombre').focus();
			return false
		}else if(nombre.length!=8){
			alert('Ingrese 8 caracteres')
			document.getElementById('nombre').focus();
			return false
		}else if(!/[0-9]/.test(nombre)){
			alert('No cumple con los patrones')
			document.getElementById('nombre').focus();
			return false
		}
		
		if(nacionalidad===''){
			alert('Ingrese la nacionalidad del autor');
			document.getElementById('nacionalidad').focus();
			return false
		}
		
	
		return true;
		}else{
		return false
		}
	}
</script>
</head>
<body>



	<%@ include file="/cabecera.jsp"%>

	<%
	if (request.getAttribute("respuesta") != null) {
		boolean res = (boolean) request.getAttribute("respuesta");
		if (res) {
			List<String> listaError = (List<String>) request.getAttribute("listaError");
			for (String error : listaError) {
	%>
	<span><%=error%></span>
	<br>
	<%
	}
	}
	}
	%>


	<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow-lg border-0 rounded-4">
					<div class="card-body p-5">
						<h2 class="text-center mb-4 fw-bold text-primary">Registro
							autor</h2>
						<form role="form" action="<%=url%>AutorControllers" method="post"
						onsubmit="return validarFormulario()">
							<input type="hidden" name="op" value="insertar">

							<div class="mb-3 input-group">
								<input type="text" class="form-control" name="nombre" id="nombre"
									placeholder="Nombre completo">
							</div>


							<div class="mb-3 input-group">
								<input type="text" class="form-control" name="nacionalidad" id="nacionalidad"
									placeholder="Ingrese su nacionalidad">
							</div>

							<!-- Botón de Enviar -->
							<div class="d-grid gap-2 d-md-flex justify-content-md-end">
								<a type="reset" class="btn btn-outline-secondary btn-lg"
									href="<%=url%>AutorControllers?op=listar">Cancelar</a>
								<button type="submit" class="btn btn-primary btn-lg fw-semibold">Registrar</button>
							</div>


						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>