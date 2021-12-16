package com.example.demo.repository;

import com.example.demo.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    public Cliente findByDocumentoAndTipo(String documento, String tipo);
    public List<Cliente> findByEdadGreaterThan(Long edad);
}
