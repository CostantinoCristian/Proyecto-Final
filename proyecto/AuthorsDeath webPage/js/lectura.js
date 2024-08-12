document.addEventListener("DOMContentLoaded", function () {
    // Hacer la solicitud GET a la API de temas
    $.ajax({
        url: 'http://localhost:7777/autorsdeath/api/tematicas',
        type: 'GET',
        dataType: 'json',
        success: function (tematicas) {
            // Limpiar y llenar el elemento select con las opciones de temas
            var selectTema = $('#tema');
            selectTema.empty();
            $.each(tematicas, function (index, tema) {
                selectTema.append('<option value="' + tema.idTematica + '">' + tema.tema + '</option>');
            });
        },
        error: function (error) {
            console.error('Error al obtener la lista de temas:', error);
        }
    });

    // Función para obtener parámetros de la URL
    function getUrlParameter(name) {
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
        var results = regex.exec(location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    }

    // Hacer la solicitud GET según los parámetros de la URL
    let url = '';
    let temaParam = getUrlParameter('tema');
    let tituloParam = getUrlParameter('titulo');
    let p = getUrlParameter('pagina');

    if (temaParam !== '') {
        // Si hay un parámetro 'tema', hacer la solicitud a la API de temas
        url = 'http://localhost:7777/autorsdeath/api/tematicas/' + temaParam;
    } else if (tituloParam !== '') {
        // Si hay un parámetro 'titulo', hacer la solicitud a la API de libros por título
        url = 'http://localhost:7777/autorsdeath/api/libros/titulo/' + tituloParam;
    } else {
        if (p !== '') {
            url = 'http://localhost:7777/autorsdeath/api/libros/paginado?pagina='+p+'&elementos=28';
        }else {
        // Si no hay parámetros, hacer la solicitud a la API de libros paginado
        url = 'http://localhost:7777/autorsdeath/api/libros/paginado?pagina=0&elementos=28';
        }
    }

    // Realizar la solicitud correspondiente
    fetch(url)
        .then(response => response.json())
        .then(data => {
            // Procesar los datos y construir la interfaz
            const librosContainer = document.getElementById('libros-container');
            var libros;

            if (temaParam !== '') {
                // Si el parámetro es 'tema', usar la propiedad librosTematica del JSON
                libros = Array.isArray(data.librosTematica) ? data.librosTematica : [data.librosTematica];
            } else if (tituloParam !== '') {
                // Si el parámetro es 'titulo', usar la respuesta directa
                libros = Array.isArray(data) ? data : [data];
            } else {
                // Si no es ni 'tema' ni 'titulo', usar el contenido del JSON
                libros = Array.isArray(data.content) ? data.content : [data.content];

            }

            libros.forEach(libro => {
                const libroDiv = document.createElement('div');
                libroDiv.classList.add('libro');

                const tituloElement = document.createElement('h2');
                tituloElement.textContent = libro.titulo;

               // const precioElement = document.createElement('p');
               // precioElement.textContent = `Precio: $${libro.precio}`;

                const idLibroElement = document.createElement('p');
                idLibroElement.textContent = `ID: ${libro.idLibro}`;

                // Agregar un evento de clic al contenedor de libros
                libroDiv.addEventListener('click', function () {
                    // Redirigir a lecturalibro.php con el ID del libro como parámetro
                    window.location.href = `lecturalibro.php?idLibro=${libro.idLibro}`;
                });

                libroDiv.appendChild(tituloElement);
                //libroDiv.appendChild(precioElement);
                libroDiv.appendChild(idLibroElement);

                librosContainer.appendChild(libroDiv);
            });


            // Construir el paginador
            const paginador = document.getElementById('paginador');
            const paginaActual = data.pageable.pageNumber;
            const totalPages = data.totalPages;

            // Construir enlaces para la página anterior y siguiente si es necesario
            if (paginaActual > 0) {
                const pagIzq = document.createElement('li');
                pagIzq.classList.add('pagIzq');
                const enlaceAnterior = document.createElement('a');
                enlaceAnterior.href = `lectura.php?pagina=${paginaActual - 1}`;
                enlaceAnterior.textContent = '<< Anterior';
                pagIzq.appendChild(enlaceAnterior);
                paginador.appendChild(pagIzq);
            }

            if (!data.last) {
                const pagDer = document.createElement('li');
                pagDer.classList.add('pagDer');
                const enlaceSiguiente = document.createElement('a');
                enlaceSiguiente.href = `lectura.php?pagina=${paginaActual + 1}`;
                enlaceSiguiente.textContent = 'Siguiente >>';
                pagDer.appendChild(enlaceSiguiente);
                paginador.appendChild(pagDer);
            }
        })
        .catch(error => console.error('Error:', error));
});