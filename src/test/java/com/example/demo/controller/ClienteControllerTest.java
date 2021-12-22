package com.example.demo.controller;

import com.example.demo.model.entities.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureMockMvc
@SpringBootTest
class ClienteControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

     Cliente cliente;



    @BeforeEach
    void setUp() {

        cliente = new Cliente();
        cliente.setId(11L);
        cliente.setNombres("Carlos");
        cliente.setApellidos("Cárdenas");
        cliente.setDocumento("12312435");
        cliente.setEdad(24L);
        cliente.setTipo("CC");
        cliente.setCiudadNacimiento("Bogotá");
        cliente.setTelefono("3356466");


    }

    @Test
    void guardarCliente() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String input = objectMapper.writeValueAsString(cliente);
        mvc.perform(post("/cliente").contentType(MediaType.APPLICATION_JSON_VALUE).content(input)
                //.param("cliente", String.valueOf(cliente))
        ).andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    void listarClientes() throws Exception {
        mvc.perform(get("/cliente")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)
                );
    }

    @Test
    void filtroEdad() throws Exception {
        mvc.perform(get("/cliente/mayorQue?edad=" + cliente.getEdad() + "")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)));
    }

    @Test
    void getByDocumentoAndTipo() throws Exception {
        mvc.perform(get("/cliente/documentoytipo?documento=" + cliente.getDocumento() + "&tipo=" + cliente.getTipo())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombres").value(cliente.getNombres()));
    }

    @Test
    void eliminarCliente() throws Exception {
        mvc.perform(delete("/cliente?id="+cliente.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

}