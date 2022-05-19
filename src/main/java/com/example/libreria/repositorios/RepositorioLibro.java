package com.example.libreria.repositorios;

import com.example.libreria.entidades.EntidadAutor;
import com.example.libreria.entidades.EntidadLibro;
import com.example.libreria.enumeraciones.GeneroLiterario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioLibro extends JpaRepository<EntidadLibro, String> {
    
    @Query("SELECT l FROM EntidadLibro l WHERE l.titulo = :titulo")
    public EntidadLibro buscarPorNombre(@Param("titulo") String titulo);
    
    @Query("SELECT l FROM EntidadLibro l WHERE l.id = :id")
    public EntidadLibro buscarPorId(@Param("id") String id);
    
    @Query("SELECT l FROM EntidadLibro l WHERE l.generoLiterario = :generoLiterario")
    public List<EntidadLibro> buscarPorGeneroLiterario(@Param("generoLiterario") GeneroLiterario generoLiterario);
    
    @Query("SELECT l FROM EntidadLibro l WHERE l.autor = :autor")
    public List<EntidadLibro> buscarPorAutor(@Param("autor") EntidadAutor autor);
}
