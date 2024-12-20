<%@page import="com.beans.Autor"%>
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
	
	
	Autor autor;
	if(request.getAttribute("autor")==null)
		autor = new Autor();
	else{
		autor = (Autor)request.getAttribute("autor");
	}
	
	%>

	<%@ include file="/cabecera.jsp" %>
<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-body p-5">
                    <h2 class="text-center mb-4 fw-bold text-primary">Editar Autor</h2>
                    <form role ="form" action="<%=url%>AutorControllers" method="post">
                    <input type="hidden" name="op" value="editar">
                    <input type="hidden" name="id" value="<%= autor.getId() %>">
                       
                        <div class="mb-3 input-group">
                            <input type="text" class="form-control" name="nombre"
                            value="<%= autor.getNombre() %>">
                        </div>

                       
                        <div class="mb-3 input-group">
                            <input type="text" class="form-control" name="nacionalidad"
                            value="<%= autor.getPais() %>">
                        </div>

                        <!-- Botón de Enviar -->
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <a type="reset" class="btn btn-outline-secondary btn-lg" 
                            href="<%= url%>AutorControllers?op=listar">Cancelar</a>
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