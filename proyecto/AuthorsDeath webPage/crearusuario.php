<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Usuario</title>
    <link rel="stylesheet" href="./css/crearusuario.css">
</head>
<body>
    <div id="loginContainer">
        <h1>Crear Nuevo Usuario</h1>
        <form id="crearUsuarioForm">
            <label for="nick">Usuario:</label>
            <input type="text" id="nick" name="nick" required><br>

            <label for="email">Correo Electrónico:</label>
            <input type="text" id="email" name="email" required><br>

            <label for="pass">Contraseña:</label>
            <input type="password" id="password" name="pass" required><br>

            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required><br>

            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" name="apellido" required><br>

            <label for="direccion">Dirección:</label>
            <input type="text" id="direccion" name="direccion" required><br>

            <label for="movil">Número Móvil:</label>
            <input type="number" id="movil" name="movil" required><br>

            <input type="button" value="Crear Usuario" id="crearUsuarioButton">
        </form>

        <div id="resultado"></div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="./js/crearusuario.js"></script>
</body>
</html>
