document.addEventListener("DOMContentLoaded", function () {
    // Obtener la ID del libro de la URL
    const params = new URLSearchParams(window.location.search);
    const idLibro = params.get('idLibro');

    if (idLibro) {
        // Hacer la solicitud GET a la API para obtener las páginas del libro
        fetch(`http://localhost:7777/autorsdeath/api/paginas/libro/${idLibro}`)
            .then(response => response.json())
            .then(paginas => {
                let paginasContainer = document.getElementById('paginas');

                // Crear y agregar divs para cada página del libro
                paginas.forEach(pagina => {
                    let paginaDiv = document.createElement('div');
                    paginaDiv.classList.add('pagina');

                    
                    let contenidoPaginaElement = document.createElement('p');
                    contenidoPaginaElement.textContent = pagina.texto;

                    
                    paginaDiv.appendChild(contenidoPaginaElement);

                  
                    let numeroPaginaElement = document.createElement('span');
                    numeroPaginaElement.classList.add('numero-pagina');
                    numeroPaginaElement.textContent = pagina.numPagina;

                    
                    paginaDiv.appendChild(numeroPaginaElement);

                    
                    paginasContainer.appendChild(paginaDiv);
                });
            })
            .catch(error => console.error('Error:', error));
    } else {
        console.error('No se ha proporcionado un ID de libro válido.');
    }
});