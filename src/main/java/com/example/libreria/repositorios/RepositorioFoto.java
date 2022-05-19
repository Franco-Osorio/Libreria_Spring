package com.example.libreria.repositorios;

import com.example.libreria.entidades.EntidadFoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioFoto extends JpaRepository<EntidadFoto, String> {
    
}
