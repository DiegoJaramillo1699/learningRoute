package com.example.demo.services;

import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.model.entities.Cliente;
import com.example.demo.model.entities.Imagen;
import com.example.demo.model.exceptions.ClienteNoEncontradoException;
import com.example.demo.model.helpers.IMapper;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteServiceImp implements IClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ImagenRepository imagenRepository;

    @Autowired
    IMapper mapper;


    public void create(Cliente cliente){

        this.clienteRepository.save(cliente);

    }

    public List<Cliente> findAll(){


        return this.clienteRepository.findAll();

    }

    public Cliente findById(Long id) throws ClienteNoEncontradoException{

        System.out.println(id);
        return this.clienteRepository.findById(id).orElseThrow(()-> new ClienteNoEncontradoException("No se encontró el cliente"));
    }

    public Cliente findByDocumentoAndTipo(String documento,String tipo){

        return this.clienteRepository.findByDocumentoAndTipo(documento,tipo);
    }

    public List<Cliente> findByEdadGreaterThan(Long edad){

        return this.clienteRepository.findByEdadGreaterThan(edad);

    }

    public void deleteCliente(Long id){

        this.clienteRepository.deleteById(id);
        this.imagenRepository.deleteById(String.valueOf(id));


    }

    public void actualizarCliente(Cliente cliente){

        this.clienteRepository.save(cliente);

    }

    //función para convertir cliente a clienteDTO
    public ClienteDTO clienteToClienteDTO(Cliente cliente){
        if(cliente == null){
            return null;
        }

        else{
            ClienteDTO clienteDTO = this.mapper.modelMapper().map(cliente,ClienteDTO.class);

            clienteDTO.setImagen(this.imagenRepository.findById(String.valueOf(cliente.getId())).orElse(new Imagen()).getImagen());

            return clienteDTO;
        }


    }

    //función para convertir lista de clientes a clientesDTO
    public List<ClienteDTO> clientesToClientesDTO(List<Cliente> clientes) {
        List<ClienteDTO> clientesDTO = new ArrayList<>();

        for (int i=0; i< clientes.size(); i++) {
            clientesDTO.add(clienteToClienteDTO(clientes.get(i)));

        }

        return clientesDTO;
    }

}
