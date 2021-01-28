package com.example.mutantes.controller;

import com.example.mutantes.entity.Mutante;
import com.example.mutantes.service.MutanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/mutantes")
public class mutanteController {

    private MutanteService mutanteService;

    public mutanteController(MutanteService mutanteService){

        this.mutanteService = mutanteService;
    }

    @PostMapping("/mutant/")
    public ResponseEntity<?> save(@RequestBody Mutante entity){
        try {
            ResponseEntity respuesta = mutanteService.isMutant(entity);
            return respuesta;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error en la funcion save\"}");
        }
    }
}
