package com.example.demo.controller;

import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.model.entities.Cliente;
import com.example.demo.model.exceptions.ClienteNoEncontradoException;
import com.example.demo.services.IClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@RestController
@RequestMapping(path = "/cliente")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST})

public class ClienteController {

    @Autowired
    IClienteService clienteService;

    @Operation(summary = "Crea un cliente en la base de datos.", description = "No es necesario enviar el archivo de la imagen, se puede hacer mediante el controlador de Imagen.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente creado.", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "400", description = "Ya existe un cliente registrado con el documento", content = @Content)})
    @Transactional
    //@ApiOperation(response = Cliente.class ,value = "Crear un cliente", notes = "Esta operaci�n retorna el cliente creado dada su informaci�n.")
    @PostMapping
    private ResponseEntity<String> createCliente(@Valid @RequestBody Cliente cliente) {

        //Cliente aux = clienteService.create(cliente);

        clienteService.create(cliente);
        return new ResponseEntity<String>("Cliente creado con éxito", HttpStatus.CREATED);

    }


    @Operation(summary = "Actualiza un cliente en la base de datos.", description = "Es necesario enviar el cliente junto con el id.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente actualizado.", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "400", description = "Ya existe un cliente registrado con el documento", content = @Content)})
    @Transactional
    //@ApiOperation(response = Cliente.class ,value = "Crear un cliente", notes = "Esta operaci�n retorna el cliente creado dada su informaci�n.")
    @PutMapping
    private ResponseEntity<String> actualizarCliente(@Valid @RequestBody Cliente cliente) {

        //Cliente aux = clienteService.create(cliente);

        clienteService.create(cliente);
        return new ResponseEntity<String>("Cliente actualizado con éxito", HttpStatus.CREATED);

    }


    @Operation(summary = "Borra un cliente de la base de datos.", description = "Se debe especificar el id del cliente.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente borrado."), @ApiResponse(responseCode = "400", description = "No se encontró el cliente a borrar.", content = @Content)})
    @Transactional
    @DeleteMapping
    private ResponseEntity<String> deleteCliente(@RequestParam @NotEmpty Long id) {

        this.clienteService.deleteCliente(id);
        return new ResponseEntity<String>("Cliente borrado con éxito", HttpStatus.OK);

    }

    @Operation(summary = "Obtiene todos los clientes de la base de datos.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Clientes obtenidos con éxito.", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "400", description = "No se pudieron obtener los clientes.", content = @Content)})
    @GetMapping
    private ResponseEntity<List<ClienteDTO>> obtenerClientes() {
        List<Cliente> clientes = clienteService.findAll();


        //System.out.println(clientes.get(0).getVisitas().get(0).getFechaInicio().toString());
        return new ResponseEntity<>(clienteService.clientesToClientesDTO(clienteService.findAll()), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un cliente de la base de datos.", description = "Se debe especificar el id del cliente.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente obtenido.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))}), @ApiResponse(responseCode = "400", description = "No se encontró el cliente solicitado o los parámetros no son apropiados.", content = @Content)})
    @GetMapping("id")
    private ResponseEntity<Object> findById(@RequestParam Long id) throws ClienteNoEncontradoException {

        Cliente cliente = clienteService.findById(id);

        ClienteDTO clienteDTO = this.clienteService.clienteToClienteDTO(cliente);
        //System.out.println(clientes.get(0).getVisitas().get(0).getFechaInicio().toString());
        return new ResponseEntity<>(clienteDTO, HttpStatus.FOUND);
    }

    @Operation(summary = "Obtiene un cliente de la base de datos.", description = "Se debe especificar el tipo de documento y documento del cliente.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente obtenido.", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "400", description = "No se encontró el cliente solicitado o los parámetros no son apropiados.", content = @Content)})
    @GetMapping("documentoytipo")
    private ResponseEntity<Object> findByDocumentoAndTipo(@RequestParam String documento, @RequestParam String tipo) {
        Cliente cliente = clienteService.findByDocumentoAndTipo(documento, tipo);

        if (cliente == null) {
            return new ResponseEntity<>("No se encontró el cliente", HttpStatus.NOT_FOUND);
        }

        ClienteDTO clienteDTO = this.clienteService.clienteToClienteDTO(cliente);
        //System.out.println(clientes.get(0).getVisitas().get(0).getFechaInicio().toString());
        return new ResponseEntity<>(clienteDTO, HttpStatus.FOUND);
    }

    @Operation(summary = "Obtiene clientes de la base de datos mayores a cierta edad.", description = "Se debe especificar la edad que corresponde al límite inferior de la búsqueda.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Clientes obtenidos.", content = {@Content(mediaType = "application/json")}), @ApiResponse(responseCode = "400", description = "No se encontraron los clientes o los parámetros no son apropiados.", content = @Content)})
    @GetMapping("mayorQue")
    private ResponseEntity<Object> findByAgeGreaterThan(@RequestParam Long edad) {
        List<Cliente> clientes = clienteService.findByEdadGreaterThan(edad);

        if (clientes == null) {
            return new ResponseEntity<>("No se encontraron clientes que cumplan con el parámetro especificado", HttpStatus.NOT_FOUND);
        }

        List<ClienteDTO> clientesDTO = this.clienteService.clientesToClientesDTO(clientes);
        //System.out.println(clientes.get(0).getVisitas().get(0).getFechaInicio().toString());
        return new ResponseEntity<>(clientesDTO, HttpStatus.FOUND);
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
