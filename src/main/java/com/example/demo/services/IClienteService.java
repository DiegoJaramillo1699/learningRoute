package com.example.demo.services;

import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.model.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClienteService {

    public void create(Cliente cliente);
    public List<Cliente> findAll();
    public Cliente findByDocumentoAndTipo(String documento, String tipo);
    public ClienteDTO clienteToClienteDTO(Cliente cliente);
    public List<ClienteDTO> clientesToClientesDTO(List<Cliente> clientes);
    public List<Cliente> findByEdadGreaterThan(Long edad);
    public void deleteCliente(Long id);
}
