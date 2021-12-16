package com.example.demo.repository;

import com.example.demo.model.entities.Imagen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagenRepository extends MongoRepository<Imagen,String> {

    public Optional<Imagen> findById(String id);

}
