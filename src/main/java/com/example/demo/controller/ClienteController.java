package com.example.demo.controller;

import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.model.entities.Cliente;
import com.example.demo.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/cliente")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE,RequestMethod.POST })

public class ClienteController {

    @Autowired
    IClienteService clienteService;


    @Transactional
    //@ApiOperation(response = Cliente.class ,value = "Crear un cliente", notes = "Esta operaci�n retorna el cliente creado dada su informaci�n.")
    @PostMapping
    private ResponseEntity<String> createCliente(@Valid @RequestBody Cliente cliente){

        //Cliente aux = clienteService.create(cliente);

        clienteService.create(cliente);
        return new ResponseEntity<String>("Cliente creado con éxito", HttpStatus.CREATED);

    }

    @Transactional
    @DeleteMapping
    private ResponseEntity<String> deleteCliente(@RequestParam Long id){

        this.clienteService.deleteCliente(id);
        return new ResponseEntity<String>("Cliente borrado con éxito", HttpStatus.OK);

    }


    @GetMapping
    private ResponseEntity<List<ClienteDTO>> obtenerClientes() {
        List<Cliente> clientes = clienteService.findAll();


        //System.out.println(clientes.get(0).getVisitas().get(0).getFechaInicio().toString());
        return new ResponseEntity<>(clienteService.clientesToClientesDTO(clienteService.findAll()), HttpStatus.OK);
    }

    @GetMapping("documentoytipo")
    private ResponseEntity<Object> findByDocumentoAndTipo(@RequestParam String documento,
    @RequestParam String tipo) {
        Cliente cliente = clienteService.findByDocumentoAndTipo(documento, tipo);

        if(cliente == null){
            return new ResponseEntity<>("No se encontró el cliente", HttpStatus.NOT_FOUND);
        }

        ClienteDTO clienteDTO = this.clienteService.clienteToClienteDTO(cliente);
        //System.out.println(clientes.get(0).getVisitas().get(0).getFechaInicio().toString());
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    @GetMapping("mayorQue")
    private ResponseEntity<Object> findByAgeGreaterThan(@RequestParam Long edad) {
        List<Cliente> clientes = clienteService.findByEdadGreaterThan(edad);

        if(clientes == null){
            return new ResponseEntity<>("No se encontraron clientes que cumplan con el parámetro especificado"
                    , HttpStatus.NOT_FOUND);
        }

        List<ClienteDTO> clientesDTO = this.clienteService.clientesToClientesDTO(clientes);
        //System.out.println(clientes.get(0).getVisitas().get(0).getFechaInicio().toString());
        return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
    }

    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/
}
