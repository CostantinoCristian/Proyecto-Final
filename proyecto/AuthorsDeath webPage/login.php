<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" href="./css/login.css">
</head>
<body>
    <div id="loginContainer">
        <div id="fotoCuadrada">
            <!-- Agrega la ruta correcta a tu imagen -->
            <img id="logo" src="./img/logo.jpg" alt="Logo">
        </div>
        <h1>Author's Death</h1>
        <form id="loginForm">
            <label for="nick">Usuario:</label>
            <input type="text" id="nick" name="nick" required><br><br>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required><br><br>
            <label for="recordarUsuario">Recordar Usuario</label>
            <input type="checkbox" id="recordarUsuario">
            <br><br>
            <input type="button" value="Acceder" id="loginButton" class="boton">
            <br><br>
            <!-- Nuevo botón para crear usuario -->
            <input type="button" value="Crear Usuario" id="crearUsuarioButton" class="boton">
        </form>

        <div id="result"></div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="./js/login.js"></script>
</body>
</html>
