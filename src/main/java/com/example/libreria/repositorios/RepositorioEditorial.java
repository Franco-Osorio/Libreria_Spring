package com.example.libreria.repositorios;

import com.example.libreria.entidades.EntidadEditorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioEditorial extends JpaRepository<EntidadEditorial, String> {
    
    @Query("SELECT n FROM EntidadEditorial n WHERE n.nombre = :nombre")
    public EntidadEditorial buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT n FROM EntidadEditorial n WHERE n.id = :id")
    public EntidadEditorial buscarPorId(@Param("id") String id);
}
