<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Genero</title>
    <!-- Bootstrap CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="assets/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<%@ include file="/cabecera.jsp" %>
<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-body p-5">
                    <h2 class="text-center mb-4 fw-bold text-primary">Registro Genero</h2>
                    <form role ="form" action="<%=url%>GeneroControllers" method="post">
                    <input type="hidden" name="op" value="insertar">
                        <div class="mb-3 input-group">
                            <input type="text" class="form-control" name="nombre"
                            placeholder="Nombre completo" required>
                        </div>

                        <div class="mb-3 input-group">
                            <input type="text" class="form-control" name="descripcion"
                            placeholder="Descripcion" required>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <a type="reset" class="btn btn-outline-secondary btn-lg" 
                            href="<%= url%>GeneroControllers?op=listar">Cancelar</a>
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