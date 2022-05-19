package com.example.libreria.repositorios;

import com.example.libreria.entidades.EntidadAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAutor extends JpaRepository<EntidadAutor, String> {
    
    @Query("SELECT a FROM EntidadAutor a WHERE a.nombre = :nombre")
    public EntidadAutor buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT a FROM EntidadAutor a WHERE a.id = :id")
    public EntidadAutor buscarPorId(@Param("id") String id);
}
