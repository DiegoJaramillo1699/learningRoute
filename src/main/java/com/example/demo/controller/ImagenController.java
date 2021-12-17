package com.example.demo.controller;

import com.example.demo.model.entities.Imagen;
import com.example.demo.model.exceptions.ImagenIncompletaException;
import com.example.demo.model.exceptions.ImagenNoEncontrada;
import com.example.demo.services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping(path = "/imagen")
public class ImagenController {

    @Autowired
    ImagenService imagenService;

    @PostMapping
    public ResponseEntity<Imagen> guardarImagen(@RequestParam String id, @RequestParam(value="image",required=false) MultipartFile file) throws ImagenIncompletaException {

        return new ResponseEntity<>(this.imagenService.save(id, file), HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<String> borrarImagen(@RequestParam String id) throws ImagenNoEncontrada {

        this.imagenService.borrarImagen(id);
        return new ResponseEntity<>("Imagen borrada con éxito", HttpStatus.OK);

    }

}
