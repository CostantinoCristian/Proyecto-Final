$(document).ready(function() {
    // Verificar si hay credenciales almacenadas en sessionStorage
    usuarioLogeado = JSON.parse(sessionStorage.getItem('usuarioSesion'));

    // Si no hay credenciales en sessionStorage, verificar en localStorage
    if (!usuarioLogeado) {
        usuarioLogeado = JSON.parse(localStorage.getItem('usuarioPersistente'));
    }

    // Verificar si el usuario está logeado
    if (usuarioLogeado) {
        // Mostrar el nombre del usuario en el elemento <h1>
        $("#nombreUsuario").text(usuarioLogeado.nick +', te damos la Bienvenida a Author`s Death');

        // Evento para el botón de cerrar sesión
        $("#cerrarSesionButton").click(function() {
            // Eliminar datos del usuario de sessionStorage y localStorage
            sessionStorage.removeItem('usuarioSesion');
            localStorage.removeItem('usuarioPersistente');
            // Redirigir a la página de inicio de sesión
            window.location.href = 'login.php';
        });
    } else {
        // Usuario no logeado, redirigir a la página de inicio de sesión
        window.location.href = 'login.php';
    }
});