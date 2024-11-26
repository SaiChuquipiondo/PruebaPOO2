<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>INICIO</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<% String url="http://localhost:8080/Prueba18/"; %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="<%= url %>">Inicio</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="<%= url %>AutorControllers?op=listar">Autores</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<%= url %>EditorialControllers?op=listar">Editorial</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<%= url %>GeneroControllers?op=listar">Genero</a>
        </li>
          <li class="nav-item">
          <a class="nav-link" href="<%= url %>LibroControllers?op=listar">Libro</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<script src="assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>


