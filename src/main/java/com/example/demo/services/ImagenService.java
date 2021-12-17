package com.example.demo.services;

import com.example.demo.model.entities.Imagen;
import com.example.demo.model.exceptions.ImagenNoEncontrada;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ImagenService {

    public Imagen save(String id, MultipartFile file);
    public List<Imagen> findAll();
    public void borrarImagen(String id) throws ImagenNoEncontrada;


}
