package com.example.libreria.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class EntidadAutor {
    
    @Id
    @GeneratedValue(generator = "uuid")                      //Genera un Id nuevo
    @GenericGenerator(name = "uuid", strategy = "uuid2")     //Hace que los Id no se repitan
    private String id;
    
    @Column(unique = true)
    private String nombre;
    
    private Boolean alta;
    
    @OneToOne
    private EntidadFoto foto;

    public EntidadAutor() {
    }

    public EntidadAutor(String id, String nombre, Boolean alta, EntidadFoto foto) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public EntidadFoto getFoto() {
        return foto;
    }

    public void setFoto(EntidadFoto foto) {
        this.foto = foto;
    }
    
    
}
