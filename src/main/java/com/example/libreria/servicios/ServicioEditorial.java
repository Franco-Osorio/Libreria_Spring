package com.example.libreria.servicios;

import com.example.libreria.entidades.EntidadEditorial;
import com.example.libreria.errores.ErroresDeServicio;
import com.example.libreria.repositorios.RepositorioEditorial;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioEditorial {
    
    @Autowired
    private RepositorioEditorial repositorioEditorial;
    
    //Metodo para generar una nueva Editorial
    @Transactional(propagation = Propagation.NESTED) //Transactional se usa para metodos que van a persistir un dato.
    public void guardarEditorial(String nombre) throws ErroresDeServicio {
        validar(nombre);
        
        EntidadEditorial editorial = new EntidadEditorial();
        
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        
        repositorioEditorial.save(editorial);
    }
    
    public void validar(String nombre) throws ErroresDeServicio {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErroresDeServicio("El campo nombre es obligatorio y no debe ser nulo.");
        }
    }
    
    @Transactional(readOnly = true)
    public EntidadEditorial buscarPorId(String id) {
        return repositorioEditorial.buscarPorId(id);
    }
    
    @Transactional(readOnly = true)
    public List<EntidadEditorial> mostrarTodos() {
        return repositorioEditorial.findAll();
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificarEditorial(String id, String nombre) throws ErroresDeServicio {
        validar(nombre);
        
        Optional<EntidadEditorial> optional = repositorioEditorial.findById(id);
        
        if (optional.isPresent()) {
            EntidadEditorial editorial = optional.get();
            
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            
            repositorioEditorial.save(editorial);
        } else {
            throw new ErroresDeServicio("La editorial que desea modificar no existe.");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorNombre(String nombre) {
        Optional <EntidadEditorial> optional = repositorioEditorial.findById(nombre);
        
        if (optional.isPresent()) {
            repositorioEditorial.delete(optional.get());
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId(String id) {
        Optional <EntidadEditorial> optional = repositorioEditorial.findById(id);
        
        if (optional.isPresent()) {
            repositorioEditorial.delete(optional.get());
        }
    }
}
