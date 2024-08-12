$(document).ready(function () {
    $("#crearUsuarioButton").click(function () {
        var nick = $("#nick").val();
        var email = $("#email").val();
        var password = $("#password").val();
        var nombre = $("#nombre").val();
        var apellido = $("#apellido").val();
        var direccion = $("#direccion").val();
        var movil = $("#movil").val();


        // Verificar que todos los campos estén llenos
        if (nick === "" || email === "" || password === "" || nombre === "" || apellido === "" || direccion === "" || movil === "") {
            $("#resultado").text("Todos los campos son obligatorios. Por favor, rellene todos los campos.");
            return;
        } else {
            // Verificar el nick
            $.get(`http://localhost:7777/autorsdeath/api/usuarios/nick/${nick}`, function (data) {
                // Si el nick existe
                $("#resultado").text("Ese nombre de Usuario ya esta siendo utilizado");
                //si el nick no existe
            }).fail(function () {
                // Verificar el email
                $.get(`http://localhost:7777/autorsdeath/api/usuarios/email/${email}`, function (data) {
                    // si el email existe
                    $("#resultado").text("Ese Email ya esta siendo utilizado");
                    //si el email ni el nick existen
                }).fail(function () {
                    //Crear el Usuario
                    var nuevoUsuario = {
                        "nick": nick,
                        "email": email,
                        "pass": password,
                        "nombre": nombre,
                        "apellido": apellido,
                        "direccion": direccion,
                        "movil": parseInt(movil)
                    };
                    //enviar el post
                    $.ajax({
                        type: "POST",
                        url: "http://localhost:7777/autorsdeath/api/usuarios",
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        data: JSON.stringify(nuevoUsuario),
                        success: function () {
                            // si el usuario se crea
                            $("#resultado").text("El usuario ha sido creado");

                            // limpia los campos del formulario
                            $("#nick").val("");
                            $("#email").val("");
                            $("#password").val("");
                            $("#nombre").val("");
                            $("#apellido").val("");
                            $("#direccion").val("");
                            $("#movil").val("");
                        },
                        error: function () {
                            $("#resultado").text("Error al crear usuario");
                        }
                    });
                });
            });
        }
    });
});