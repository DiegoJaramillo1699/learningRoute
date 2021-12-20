package com.example.controller;


import com.example.demo.controller.ClienteController;
import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.model.entities.Cliente;
import com.example.demo.model.exceptions.AppExceptionHandler;
import com.example.demo.model.helpers.IMapper;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.services.ClienteServiceImp;
import com.example.demo.services.IClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.contentOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;


@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ControllerTest {


    private MockMvc mvc;

    @Mock
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    IMapper mapper;

    @InjectMocks
            @Autowired
    ClienteController clienteController;

    @Mock
    @Autowired
    IClienteService clienteService;

    private JacksonTester<Cliente> jsonSuperHero;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(clienteController)
                .setControllerAdvice(new AppExceptionHandler())
                .build();
    }


    @Test
    public void canRetrieveByIdWhenExists() throws Exception {

        // when
        MockHttpServletResponse response = mvc.perform(
                        get("/cliente/id?id=98")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    //@Order(2)
    void listarClientes() throws Exception {
        mvc.perform(get("/cliente")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)
                );
    }

}
