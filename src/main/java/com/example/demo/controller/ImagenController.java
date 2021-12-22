package com.example.demo.controller;

import com.example.demo.model.entities.Imagen;
import com.example.demo.model.exceptions.ImagenIncompletaException;
import com.example.demo.model.exceptions.ImagenNoEncontrada;
import com.example.demo.services.ImagenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController()
@RequestMapping(path = "/imagen")
public class ImagenController {

    @Autowired
    ImagenService imagenService;


    @Operation(summary = "Crea una imagen en la base de datos asociada a un cliente.", description = "Se debe especificar el id del cliente y el archivo con la imagen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen creada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Imagen.class))}),
            @ApiResponse(responseCode = "400", description = "No se pudo crear la imagen o los datos proporcionados no son adecuados.",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<Imagen> guardarImagen(@RequestParam String id, @RequestParam(value = "image", required = false) MultipartFile file) throws ImagenIncompletaException {

        return new ResponseEntity<>(this.imagenService.save(id, file), HttpStatus.CREATED);

    }

    @Operation(summary = "Actualiza una imagen en la base de datos asociada a un cliente.", description = "Se debe especificar el id del cliente y el archivo con la imagen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen actualizada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Imagen.class))}),
            @ApiResponse(responseCode = "400", description = "No se pudo actualizar la imagen o los datos proporcionados no son adecuados.",
                    content = @Content)})
    @PutMapping
    public ResponseEntity<Object> actualizarImagen(@RequestParam String id, @RequestParam(value = "image", required = false) MultipartFile file) throws ImagenIncompletaException, IOException {

        return new ResponseEntity<Object>("Imagen actualizada con éxtio.", HttpStatus.CREATED);

    }

    @Operation(summary = "Elimina una imagen de la base de datos.", description = "Se debe especificar el id del cliente al que corresponde la imagen.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen eliminada.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "No se encontró la imagen a borrar o no se especificó el id.",
                    content = @Content)})
    @DeleteMapping
    public ResponseEntity<String> borrarImagen(@RequestParam String id) throws ImagenNoEncontrada {

        this.imagenService.borrarImagen(id);
        return new ResponseEntity<>("Imagen borrada con éxito", HttpStatus.OK);

    }

}
