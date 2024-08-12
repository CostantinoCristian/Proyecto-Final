$(document).ready(function() {
    var usuarioSesion = JSON.parse(sessionStorage.getItem('usuarioSesion'));
    var usuarioPersistente = JSON.parse(localStorage.getItem('usuarioPersistente'));
    if (usuarioSesion) {
        redirigirASesion(usuarioSesion);
    } else if (usuarioPersistente) {
        mostrarBienvenida(usuarioPersistente);
    }

    $("#loginButton").click(function() {
        var nick = $("#nick").val();
        var password = $("#password").val();
        var recordarUsuario = $("#recordarUsuario").prop('checked');

        if (nick.trim() !== "" && password.trim() !== "") {
            var url = "http://localhost:7777/autorsdeath/api/usuarios/nick/" + encodeURIComponent(nick) + "/" + encodeURIComponent(password);

            $.ajax({
                url: url,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    if (Object.keys(data).length > 0) {
                        

                        if (!recordarUsuario) {
                            sessionStorage.setItem('usuarioSesion', JSON.stringify(data));
                        }

                      
                        if (recordarUsuario) {
                            localStorage.setItem('usuarioPersistente', JSON.stringify(data));
                        }

                       
                        mostrarBienvenida(data);
                        redirigirASesion(data);
                    } else {
                       
                        $("#result").html("Inicio de sesión fallido.<br> Verifica tu usuario y contraseña.");
                    }
                },
                error: function(xhr, textStatus, errorThrown) {
                    if (xhr.status === 401) {
                       
                        $("#result").html("Inicio de sesión fallido.<br> Verifica tu usuario y contraseña.");
                    } else {
                        console.error('Error:', errorThrown);
                        $("#result").html("Error al realizar el inicio de sesión.");
                    }
                }
            });
        } else {
          
            $("#result").html("Por favor, ingresa tu usuario y contraseña.");
        }
    });

   
    $("#crearUsuarioButton").click(function() {
       
        window.location.href = 'crearusuario.php';
    });

   
    function mostrarBienvenida(usuario) {
        $("#nombreUsuario").text("Bienvenido, " + usuario.nick);
    }

   
    function redirigirASesion(usuario) {
        window.location.href = 'index.php';
    }
});