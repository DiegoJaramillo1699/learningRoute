package com.example.demo.services;

import com.example.demo.model.entities.Cliente;
import com.example.demo.model.exceptions.ClienteNoEncontradoException;
import com.example.demo.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ClienteServiceImpTest {

    @Mock
    ClienteRepository clienteRepository;

    Cliente cliente;
    @InjectMocks
    ClienteServiceImp clienteServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        cliente.setId(10L);
        cliente.setNombres("Carlos");
        cliente.setApellidos("Cárdenas");
        cliente.setDocumento("12312435");
        cliente.setEdad(24L);
        cliente.setTipo("CC");
        cliente.setCiudadNacimiento("Bogotá");
        cliente.setTelefono("3356466");


    }

    @Test
    void findAll() throws ClienteNoEncontradoException {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
        assertNotNull(clienteServiceImp.findAll());
    }

    @Test
    void findById() throws ClienteNoEncontradoException {
        when(clienteRepository.findById(10L)).thenReturn(Optional.ofNullable(cliente));
        assertNotNull(clienteServiceImp.findById(10L));
    }

    @Test
    void findByDocumentoAndTipo(){
        when(clienteRepository.findByDocumentoAndTipo(cliente.getDocumento(),cliente.getTipo())).thenReturn(cliente);
        assertNotNull(clienteServiceImp.findByDocumentoAndTipo(cliente.getDocumento(),cliente.getTipo()));
    }

    @Test
    void findByEdadGreaterThan() {
        when(clienteRepository.findByEdadGreaterThan(cliente.getEdad()-1)).thenReturn(Arrays.asList(cliente));
        assertNotNull(clienteServiceImp.findByEdadGreaterThan(cliente.getEdad()-1));
    }

    @Test
    void create() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        assertDoesNotThrow(() -> new SQLIntegrityConstraintViolationException("Ya existe un cliente registrado con este documento"));

    }
}