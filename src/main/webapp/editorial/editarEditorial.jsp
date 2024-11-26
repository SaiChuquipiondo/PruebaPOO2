<%@page import="com.beans.Editorial"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<script src="assets/js/bootstrap.min.js"></script>
</head>
<body>
	<%

	
	Editorial edi;
	if(request.getAttribute("editorial")==null){
		edi = new Editorial();
	}else{
		edi = (Editorial) request.getAttribute("editorial");
	}
	%>
	<%@ include file="/cabecera.jsp" %>
<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-body p-5">
                    <h2 class="text-center mb-4 fw-bold text-primary">Editar Editorial</h2>
                    <form role ="form" action="<%=url%>EditorialControllers" method="post">
                    <input type="hidden" name="op" value="editar">
                    <input type="hidden" name="id" value="<%= edi.getIdEditorial() %>">
                       
                        <div class="mb-3 input-group">
                            <input type="text" class="form-control" name="nombre"
                            value="<%= edi.getNombre() %>">
                        </div>

                       
                        <div class="mb-3 input-group">
                            <input type="text" class="form-control" name="contacto"
                            value="<%= edi.getContacto() %>">
                        </div>
                        <div class="mb-3 input-group">
                            <input type="text" class="form-control" name="telefono"
                            value="<%= edi.getTelefono() %>">
                        </div>

                        <!-- Botón de Enviar -->
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <a type="reset" class="btn btn-outline-secondary btn-lg" 
                            href="<%= url%>EditorialControllers?op=listar">Cancelar</a>
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