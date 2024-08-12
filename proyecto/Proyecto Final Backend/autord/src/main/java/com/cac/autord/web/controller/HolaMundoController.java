package com.cac.autord.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludar")
public class HolaMundoController {

    @GetMapping("/presentar")
    public String Presentar() {
        return "Hola esta es la app de Lectura y Escritura Colaborativa, Creada por Cristian Ariel Costantino";
    }
}
