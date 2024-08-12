<?php
include 'head.php';
?>

<!-- Contenido de lecturalibro.php -->
    <div class="centrar">
        <div class= "centrado">
            <strong><h3 id= "id"></h3></strong>
            <strong><h1 id= "titulo"></h1></strong>
            <h3 id= "tematica"></h3>
            <p id= "precio"></p>
            <br>
            <section><h4>Detalles: </h4><p id= "detalles"></p><br><br></section>
            <div class=botones>
                <button class="button" id="botonLeer">Leer Libro</button>
                <!-- <button class="button" id="botonComprar">Comprar</button> Para poder implementar compras en el futuro-->
            </div>
        </div>
        
        <div class="cuadro-comentarios">
            <h1>Comentarios:</h1>
            <section id="comentarios"></section>
            <div id="entrada-comentario">
                <input type="text" id="nuevo-comentario" placeholder="Escribe tu comentario...">
                <button class="button" id="botonComentar">Comentar</button>
                <h3 class="respuesta-comentario"></h3>
            </div>

        </div>
    </div>

    <script src="./js/lecturalibro.js"></script>
<?php
include 'footer.php';
?>
