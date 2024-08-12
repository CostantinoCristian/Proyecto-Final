<?php
include 'head.php';
?>

<div id="escritorio">
    <section class="escribirLibro">
        <div>
            <label id="id"></label> 
            <h2>TÍTULO: <input type="text" id="titulo" name="titulo"></h2>
            <br>
            
            <label> Tema:
                <select id="tema" name="tema">
                </select> 
            </Label>
            <br>
            <div> 
                <br>
                <label> Descripción: </label>
                <textarea id="detalles-libro-esc" placeholder="Escribe aquí..."></textarea>
                <br>
            </div>
            <br>
            <label> ¿Lo quieres Publicar? 
                <select id="publicado" name="publicado">
                    <option value="true">Si</option>
                    <option value="false">No</option>
                </select>
            </Label>
            <div class="botones" id="botones">
            </div>
        </div>
    </section>
    <section class="escribirLibro2">
        
    </section>
</div>

<script src="./js/escrituraLibro.js"></script>
<?php
include 'footer.php';
?>