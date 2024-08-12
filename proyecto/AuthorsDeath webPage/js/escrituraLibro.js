document.addEventListener("DOMContentLoaded", function () {

    usuarioLogeado = JSON.parse(sessionStorage.getItem('usuarioSesion'));

    // Si no hay credenciales en sessionStorage, verificar en localStorage
    if (!usuarioLogeado) {
        usuarioLogeado = JSON.parse(localStorage.getItem('usuarioPersistente'));
    }

    let contarPaginas = 0;
    // Hacer la solicitud GET a la API de temas
    $.ajax({
        url: 'http://localhost:7777/autorsdeath/api/tematicas',
        type: 'GET',
        dataType: 'json',
        success: function (tematicas) {
            // Limpiar y llenar el elemento select con las opciones de temas
            selectTema = $('#tema');
            selectTema.empty();
            $.each(tematicas, function (index, tema) {
                selectTema.append('<option value="' + tema.idTematica + '">' + tema.tema + '</option>');
            });
            
        },
        error: function (error) {
            console.error('Error al obtener la lista de temas:', error);
        }
    });


    let idLibro = getUrlParameter('idLibro');
    if (idLibro == '') {
        let divBotones = document.getElementById('botones');
        let botonCrear = document.createElement('button');
        botonCrear.id = "crear";
        botonCrear.className = "button";
        botonCrear.textContent = "CrearLibro";
        divBotones.appendChild(botonCrear);

botonCrear.addEventListener('click', function () {

        let titulo = document.getElementById('titulo').value;
        let publicado = document.getElementById('publicado').value === 'true';
        let detallesLibro = document.getElementById('detalles-libro-esc').value;
        let idUsuario = usuarioLogeado.idUsuario;
        let idTematica = document.getElementById('tema').value;
        // Construir el objeto libro
        let libro = {
            "titulo": titulo,
            "publicado": publicado,
            "precio": 5.00, 
            "detalles": detallesLibro
        };

        // Realizar la solicitud POST
        fetch(`http://localhost:7777/autorsdeath/api/libros/crear/${idUsuario}/${idTematica}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(libro)
        })
            .then(response => response.json())
            .then(data => {
                // Procesar la respuesta, si es necesario
                console.log('Libro creado:', data);
                let nuevaIdLibro = data.idLibro;

                alert("¡Tu libro fue Creado! ¡Anímate a Escribir!");
                // Redirigir a la nueva página con la ID del libro
                window.location.href = `escrituraLibro.php?idLibro=${nuevaIdLibro}`;
            })
            .catch(error => {
                console.error('Error al crear el libro:', error);
            });
        }); 

    } else { //esto es en caso que la url tenga valores
        let divBotones = document.getElementById('botones');
        let botonNuevaPag = document.createElement('button');
        botonNuevaPag.id = "nueva-pagina";
        botonNuevaPag.className = "button";
        botonNuevaPag.textContent = "Nueva Pagina";
        divBotones.appendChild(botonNuevaPag);
        let botonGuardar = document.createElement('button');
        botonGuardar.id = "Guardar";
        botonGuardar.className = "button";
        botonGuardar.textContent = "Guardar";
        divBotones.appendChild(botonGuardar);

        fetch("http://localhost:7777/autorsdeath/api/libros/" + idLibro)
            .then(response => response.json())
            .then(libro => {
                
                // Procesar los datos y construir la interfaz
                
                // Rellenar el formulario con los datos del libro
                document.getElementById('id').textContent = "ID: " + libro.idLibro;
                document.getElementById('titulo').value = libro.titulo;

                document.getElementById('detalles-libro-esc').value = libro.detalles;
                document.getElementById('publicado').value = libro.publicado ? "true" : "false";

                // Hacer una solicitud GET para obtener el tema
                return fetch(`http://localhost:7777/autorsdeath/api/tematicas/libro/${idLibro}`);
            })
            .then(response => response.text())
            .then(tema => {
                // Rellenar el campo del tema en el formulario
                let temaEscrito = tema;
                
                console.log("temaescr: " + temaEscrito)
                $('#tema option').filter(function() {
                    return $(this).text() === temaEscrito;
                }).prop('selected', true);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    
            fetch(`http://localhost:7777/autorsdeath/api/paginas/libro/${idLibro}`)
            .then(response => response.json())
            .then(paginas => {
                document.querySelector('.escribirLibro2').innerHTML = '';
                paginas.forEach(pagina => {
                    contarPaginas = contarPaginas +1;
                    let nuevaPagina = document.createElement('div');
                    nuevaPagina.classList.add('pagina');
                    let numPagina = document.createElement('span');
                    let idPagina = pagina.idPagina;
                    let idInput = document.createElement('input');
                    idInput.type = 'hidden';
                    idInput.value = idPagina;
                    numPagina.textContent = 'Pág: '+ pagina.numPagina;

                    let nuevoTextarea = document.createElement('textarea');
                    nuevoTextarea.id = `escriturapagina-libro`;
                    nuevoTextarea.placeholder = 'Escribe aquí...';
                    nuevoTextarea.maxLength = "2500";
                    nuevoTextarea.value = pagina.texto;
                    nuevaPagina.appendChild(nuevoTextarea);
                    nuevaPagina.appendChild(numPagina);
                    nuevaPagina.appendChild(idInput);

                    

                    document.querySelector('.escribirLibro2').appendChild(nuevaPagina);
                });
            })
            .catch(error => {
                console.error('Error al obtener las páginas del libro:', error);
            });


            botonNuevaPag.addEventListener('click', function () {
                contarPaginas = contarPaginas +1;
            // Crear el objeto para la nueva página
            let nuevaPagina = {
                numPagina: contarPaginas,
                texto: ""
            };
            

            // Realizar la solicitud POST para crear la nueva página
            fetch(`http://localhost:7777/autorsdeath/api/paginas/crear/${idLibro}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(nuevaPagina)
            })
                .then(response => response.json())
                .then(data => {
                    // Procesar la respuesta, si es necesario
                    console.log('Nueva página creada:', data);

                    // Agregar la nueva página al DOM
                    let nuevaPaginaDiv = document.createElement('div');
                    nuevaPaginaDiv.classList.add('pagina');

                    let numPaginaSpan = document.createElement('span');
                    numPaginaSpan.textContent = 'Pag: ' + data.numPagina + ' >> ID: ' + data.idPagina;

                    let nuevoTextarea = document.createElement('textarea');
                    nuevoTextarea.id = `escriturapagina-libro`;
                    nuevoTextarea.placeholder = 'Escribe aquí...';
                    nuevoTextarea.value = data.texto;

                    nuevaPaginaDiv.appendChild(nuevoTextarea);
                    nuevaPaginaDiv.appendChild(numPaginaSpan);

                    document.querySelector('.escribirLibro2').appendChild(nuevaPaginaDiv);
                })
                .catch(error => {
                    console.error('Error al crear la nueva página:', error);
                });
                guardarLibro();
            });

            botonGuardar.addEventListener('click', function () {
               guardarLibro();
            });
            
        };
});

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}

function guardarLibro() {
    let idLibro = getUrlParameter('idLibro');
    let titulo = document.getElementById('titulo').value;
                let publicado = document.getElementById('publicado').value === 'true';
                let detallesLibro = document.getElementById('detalles-libro-esc').value;
                let idTematica = document.getElementById('tema').value;
                let idUsuario = usuarioLogeado.idUsuario;
                // Construir el objeto libro
                let libro = {
                    "idLibro": idLibro,
                    "titulo": titulo,
                    "publicado": publicado,
                    "precio": 5.00,
                    "detalles": detallesLibro
                };
            
                // Realizar la solicitud PUT para actualizar el libro
                fetch(`http://localhost:7777/autorsdeath/api/libros/update/${idUsuario}/${idTematica}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(libro)
                })
                .then(response => response.json())
                .then(data => {
                    // Procesar la respuesta del servidor si es necesario
                    console.log('Libro actualizado:', data);
                })
                .catch(error => {
                    console.error('Error al actualizar el libro:', error);
                });
            
                // Obtener datos de las páginas
                let paginasDiv = document.querySelectorAll('.pagina');
                
                paginasDiv.forEach(paginaDiv => {
                    // Obtener el ID de la página desde el DIV
                    let idInput = paginaDiv.querySelector('input[type="hidden"]');
                    let idPagina = idInput.value;

                    // Obtener el número de la página desde el DIV
                    let numPaginaSpan = paginaDiv.querySelector('span');
                    let numPagina = parseInt(numPaginaSpan.textContent.split(' ')[1]); // Esto asume un formato como 'Pag: X'

                    // Obtener el texto de la página desde el DIV
                    let textarea = paginaDiv.querySelector('textarea');
                    let textoPagina = textarea.value;
            
                    let pagina = {
                        "idPagina": idPagina,
                        "numPagina": numPagina,
                        "texto": textoPagina
                    };
            
                    // Realizar la solicitud PUT para actualizar la página
                    fetch(`http://localhost:7777/autorsdeath/api/paginas/update/${idLibro}`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(pagina)
                    })
                    .then(response => response.json())
                    .then(data => {
                        // Procesar la respuesta del servidor si es necesario
                        console.log('Página actualizada:', data);
                    })
                    .catch(error => {
                        console.error('Error al actualizar la página:', error);
                    });
                });
                
                setTimeout(() => {
                    alert("Libro Guardado");
                    window.location.href = `escrituraLibro.php?idLibro=${idLibro}`;
                }, 1000);
}