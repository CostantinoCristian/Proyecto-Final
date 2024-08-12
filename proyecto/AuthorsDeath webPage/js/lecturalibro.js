document.addEventListener("DOMContentLoaded", function () {
    // Obtener el ID del libro de la URL
    let params = new URLSearchParams(window.location.search);
    let idLibro = params.get('idLibro');

    if (idLibro) {
        // Hacer la solicitud GET a la API para obtener los detalles del libro
        fetch(`http://localhost:7777/autorsdeath/api/libros/${idLibro}`)
            .then(response => response.json())
            .then(libro => {
                // Mostrar los detalles del libro
                let idLibroElement = document.getElementById('id');
                let tituloElement = document.getElementById('titulo');
                let precioElement = document.getElementById('precio');
                let detallesElement = document.getElementById('detalles');

                idLibroElement.textContent = "Id del libro: " + libro.idLibro;
                tituloElement.textContent = "Titulo: " + libro.titulo;
                precioElement.textContent = "";
                detallesElement.textContent = libro.detalles;

                // Hacer la solicitud GET a la nueva URL para obtener los comentarios del libro
                return fetch(`http://localhost:7777/autorsdeath/api/comentarios/libro/${idLibro}`);
            })
            .then(response => response.json())
            .then(comentarios => {
                // Crear y agregar elementos de comentario
                comentarios.forEach(comentario => {
                    let comentariosElement = document.getElementById('comentarios');
                    let comentarioElement = document.createElement('div');
                    comentarioElement.classList.add('comentario');

                    let fechaPublicacionElement = document.createElement('p');
                    fechaPublicacionElement.classList.add('fechaPublicacion');

                    let fechaPublicacion = new Date(comentario.fechaPublicacion[0], comentario.fechaPublicacion[1] - 1, comentario.fechaPublicacion[2]);
                    fechaPublicacionElement.textContent = `${fechaPublicacion.toLocaleDateString()} - ${comentario.idUsuario.nick}`;

                    let comentarioTextElement = document.createElement('p');
                    comentarioTextElement.textContent = comentario.comentario;

                    comentarioElement.appendChild(fechaPublicacionElement);
                    comentarioElement.appendChild(comentarioTextElement);

                    comentariosElement.appendChild(comentarioElement);
                });

                // Hacer la solicitud GET para obtener la temática del libro
                return fetch(`http://localhost:7777/autorsdeath/api/tematicas/libro/${idLibro}`);
            })
            .then(response => response.text())
            .then(tematica => {
                // Mostrar la temática en el elemento correspondiente
                let tematicaElement = document.getElementById('tematica');
                if (tematicaElement) {
                    tematicaElement.textContent = "Tema: " + tematica;
                } else {
                    console.error('Elemento tematica no encontrado en el DOM.');
                }
            })
            .catch(error => console.error('Error:', error));

        // Agregar evento al botón "Comentar"
        let agregarComentarioButton = document.querySelector('#entrada-comentario #botonComentar');
        agregarComentarioButton.addEventListener('click', function () {
            let respuestaComentario = document.querySelector('#entrada-comentario .respuesta-comentario');
            let nuevoComentario = document.getElementById('nuevo-comentario').value;
            if (nuevoComentario !== '') {
                // Obtener la fecha actual
                let fechaActual = new Date();
                let comentario = {
                    "idLibro": {
                        "idLibro": parseInt(idLibro)
                    },
                    "idUsuario": {
                        "idUsuario": usuarioLogeado.idUsuario
                    },
                    "comentario": nuevoComentario,
                    "fechaPublicacion": [
                        fechaActual.getFullYear(),
                        fechaActual.getMonth() + 1,
                        fechaActual.getDate(),
                        fechaActual.getHours(),
                        fechaActual.getMinutes()
                    ]
                };

                // Realizar la solicitud POST para agregar un comentario
                fetch('http://localhost:7777/autorsdeath/api/comentarios', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(comentario)
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`Error al enviar el comentario. Código ${response.status}`);
                        }
                        return response.json();
                    })
                    .then(responseData => {
                        // Recargar la página después de agregar el comentario
                        location.reload();
                    })
                    .catch(error => console.error('Error al agregar el comentario:', error));
            } else {
                respuestaComentario.innerHTML ="No has escrito un comentario"
            }
        });
    } else {
        console.error('No se ha proporcionado un ID de libro válido.');
    }

    document.getElementById("botonLeer").addEventListener("click", function () {
        window.location.href = `lecturaPagina.php?idLibro=${idLibro}`;
    });
});