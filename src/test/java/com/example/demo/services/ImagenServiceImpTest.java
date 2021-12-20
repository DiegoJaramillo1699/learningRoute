package com.example.demo.services;

import com.example.demo.model.entities.Imagen;
import com.example.demo.model.exceptions.ImagenIncompletaException;
import com.example.demo.model.exceptions.ImagenNoEncontrada;
import com.example.demo.repository.ImagenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ImagenServiceImpTest {


    @Mock
    ImagenRepository imagenRepository;

    @InjectMocks
    ImagenServiceImp imagenServiceImp;

    Imagen imagen;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        imagen = new Imagen("", "prueba");
    }

    @Test
    void save() {
        when(imagenRepository.save(imagen)).thenReturn(imagen);
        assertNotNull(imagenRepository.save(imagen));
        assertDoesNotThrow(() -> new ImagenIncompletaException(""));
    }

    @Test
    void findAll() {
        when(imagenRepository.findAll()).thenReturn(Arrays.asList(imagen));
        assertNotNull(imagenServiceImp.findAll());
    }


}