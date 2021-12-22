package com.example.demo.services;

import com.example.demo.model.entities.Imagen;
import com.example.demo.model.exceptions.ImagenIncompletaException;
import com.example.demo.model.exceptions.ImagenNoEncontrada;
import com.example.demo.repository.ImagenRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ImagenServiceImp implements ImagenService{

    @Autowired
    ImagenRepository imagenRepository;

    public Imagen save(String id, MultipartFile file) throws ImagenIncompletaException {

    if(id.isBlank() && file.isEmpty()){
        throw new ImagenIncompletaException("Debe especificar un id y un archivo para la imagen");
    }
    else if(id.isBlank()){
        throw new ImagenIncompletaException("Debe especificar un id para la imagen");
    }
    else if(file.isEmpty()){
        throw new ImagenIncompletaException("El archivo de la imagen no debe estar vacío.");
    }


    try{

        return this.imagenRepository.save(new Imagen(id,convertirImagen(file)));

    } catch(Exception e) {
        e.printStackTrace();
        return null;
    }

}

public List<Imagen> findAll(){
    return this.imagenRepository.findAll();

}

public void borrarImagen(String id) throws ImagenNoEncontrada {

    if(id.isBlank()){
        throw new ImagenNoEncontrada("Debe especificar un id para la imagen a eliminar.");
    }
    Imagen imagen = this.imagenRepository.findById(id).orElseThrow(() -> new ImagenNoEncontrada("No se encontró una imagen con el id indicado"));

     this.imagenRepository.deleteById(id);

}

public void actualizarImagen(String id, MultipartFile file) throws IOException, ImagenIncompletaException {
    if(id.isBlank() && file.isEmpty()){
        throw new ImagenIncompletaException("Debe especificar un id y un archivo para la imagen");
    }
    else if(id.isBlank()){
        throw new ImagenIncompletaException("Debe especificar un id para la imagen");
    }
    else if(file.isEmpty()){
        throw new ImagenIncompletaException("El archivo de la imagen no debe estar vacío.");
    }

        this.imagenRepository.save(new Imagen(id,convertirImagen(file)));

}

private String convertirImagen(MultipartFile file) throws IOException {

    byte[] image = Base64.encodeBase64(file.getBytes());

    return new String(image);

}

}
