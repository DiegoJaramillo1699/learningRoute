package com.example.demo.services;

import com.example.demo.model.entities.Imagen;
import com.example.demo.model.exceptions.ImagenIncompletaException;
import com.example.demo.model.exceptions.ImagenNoEncontrada;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ImagenService {

    public Imagen save(String id, MultipartFile file) throws ImagenIncompletaException;
    public List<Imagen> findAll();
    public void borrarImagen(String id) throws ImagenNoEncontrada;
    public void actualizarImagen(String id, MultipartFile file) throws IOException,ImagenIncompletaException;


}
