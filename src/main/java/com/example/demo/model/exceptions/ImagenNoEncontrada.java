package com.example.demo.model.exceptions;

import com.example.demo.model.entities.Imagen;

public class ImagenNoEncontrada extends Exception{

    public ImagenNoEncontrada(String mensaje){

        super(mensaje);
    }
}
