usuario = JSON.parse(sessionStorage.getItem('usuarioSesion'));

    // Si no hay credenciales en sessionStorage, verificar en localStorage
    if (!usuario) {
        usuario = JSON.parse(localStorage.getItem('usuarioPersistente'));
    }


document.addEventListener("DOMContentLoaded", function () {
    let librosContainer = document.getElementById('libros-container');

    if (!librosContainer) {
        console.error('Error: No se encontró el contenedor de libros');
        return;
    }

    let url = new URL("http://localhost:7777/autorsdeath/api/libros/usuario/" + usuario.idUsuario);

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error en la solicitud: ${response.status} - ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            // Verificar que los datos sean un array o un objeto único
            const libros = Array.isArray(data) ? data : [data];

            const crearLibroDiv = document.createElement('div');
            crearLibroDiv.classList.add('libro');
            crearLibroDiv.addEventListener('click', function () {
                window.location.href = `escrituraLibro.php`;
             });
             const imagennuevo = document.createElement('img');
             imagennuevo.src= "./img/nuevo.png";
             const nuevoLibro = document.createElement('h2');
             nuevoLibro.textContent = "Nuevo";

             crearLibroDiv.append(imagennuevo, nuevoLibro);
             crearLibroDiv.className= "nuevo-Libro";
             librosContainer.appendChild(crearLibroDiv);

            libros.forEach(libro => {
                const libroDiv = document.createElement('div');
                libroDiv.classList.add('libro');

                const tituloElement = document.createElement('h2');
                tituloElement.textContent = libro.titulo;

                //const precioElement = document.createElement('p');
                //precioElement.textContent = `Precio: $${libro.precio.toFixed(2)}`;

                const idLibroElement = document.createElement('p');
                idLibroElement.textContent = `ID: ${libro.idLibro}`;

                libroDiv.addEventListener('click', function () {
                   window.location.href = `escrituraLibro.php?idLibro=${libro.idLibro}`;
                });

                libroDiv.appendChild(tituloElement);
                //libroDiv.appendChild(precioElement);
                libroDiv.appendChild(idLibroElement);

                librosContainer.appendChild(libroDiv);
            });
        })
        .catch(error => {
            console.error('Error en la solicitud:', error.message);
        });
});