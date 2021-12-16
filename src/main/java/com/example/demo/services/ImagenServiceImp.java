package com.example.demo.services;

import com.example.demo.model.entities.Imagen;
import com.example.demo.repository.ImagenRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class ImagenServiceImp implements ImagenService{

    @Autowired
    ImagenRepository imagenRepository;

public Imagen save(String id, MultipartFile file){

    try{
        byte[] image = Base64.encodeBase64(file.getBytes());
        String result = new String(image);
        //System.out.println(result);
        return this.imagenRepository.save(new Imagen(id,result));

    } catch(Exception e) {
        e.printStackTrace();
        return null;
    }


}

public List<Imagen> findAll(){
    return this.imagenRepository.findAll();

}



}
