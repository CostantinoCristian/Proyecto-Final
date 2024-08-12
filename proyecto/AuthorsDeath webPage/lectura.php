<?php
include 'head.php';
?>
<div id="libreria">
    <div id="filtros">
        <form id="filtroTematicaForm">
            <label for="tema">Tema:</label>
            <select id="tema" name="tema">
                <option value="OtroTema">OtroTema</option>
            </select>
            <button class="button" type="submit">Filtrar por Tema</button>
        </form>

        <form id="filtroTituloForm">
            <label for="titulo"></label>
            <input type="text" id="titulo" name="titulo">
            <button class="button" type="submit">Buscar por Título</button>
        </form>
    </div>
    <div id="libros-container">
    </div>
    <ul id="paginador">
    <ul>
</div>

    <script src="./js/lectura.js"></script>
<?php
include 'footer.php';
?>