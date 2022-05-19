package com.example.libreria.entidades;

import com.example.libreria.enumeraciones.GeneroLiterario;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class EntidadLibro {

    @Id
    @GeneratedValue(generator = "uuid")                      //Genera un Id nuevo
    @GenericGenerator(name = "uuid", strategy = "uuid2")     //Hace que los Id no se repitan
    private String id;
    
    private Long isbn;
    
    @Column(nullable = false) //No permite campo nulo
    private String titulo;

    @Temporal(TemporalType.DATE)
    private Date año;
    
    @Enumerated(EnumType.STRING)
    private GeneroLiterario generoLiterario;

    @Transient //Hace que el dato no persista en la Base de Datos
    private Integer ejemplaresTotales;
    @Transient
    private Integer ejemplaresPrestados;
    @Transient
    private Integer ejemplaresRestantes;

    private Boolean alta;
    
    @ManyToOne
    private EntidadAutor autor;
    
    @ManyToOne
    private EntidadEditorial editorial;
    
    @OneToOne
    private EntidadFoto foto;

    public EntidadLibro() {
    }

    public EntidadLibro(String id, Long isbn, String titulo, Date año, GeneroLiterario generoLiterario, Integer ejemplaresTotales, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, EntidadAutor autor, EntidadEditorial editorial, EntidadFoto foto) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.año = año;
        this.generoLiterario = generoLiterario;
        this.ejemplaresTotales = ejemplaresTotales;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getAño() {
        return año;
    }

    public void setAño(Date año) {
        this.año = año;
    }

    public GeneroLiterario getGeneroLiterario() {
        return generoLiterario;
    }

    public void setGeneroLiterario(GeneroLiterario generoLiterario) {
        this.generoLiterario = generoLiterario;
    }

    public Integer getEjemplaresTotales() {
        return ejemplaresTotales;
    }

    public void setEjemplaresTotales(Integer ejemplaresTotales) {
        this.ejemplaresTotales = ejemplaresTotales;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public EntidadAutor getAutor() {
        return autor;
    }

    public void setAutor(EntidadAutor autor) {
        this.autor = autor;
    }

    public EntidadEditorial getEditorial() {
        return editorial;
    }

    public void setEditorial(EntidadEditorial editorial) {
        this.editorial = editorial;
    }

    public EntidadFoto getFoto() {
        return foto;
    }

    public void setFoto(EntidadFoto foto) {
        this.foto = foto;
    }
    
    
}
