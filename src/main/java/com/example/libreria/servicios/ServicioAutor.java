package com.example.libreria.servicios;

import com.example.libreria.errores.ErroresDeServicio;
import com.example.libreria.entidades.EntidadAutor;
import com.example.libreria.entidades.EntidadFoto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.example.libreria.repositorios.RepositorioAutor;

@Service
public class ServicioAutor {

    @Autowired
    private RepositorioAutor repositorioAutor;
    
    @Autowired
    private ServicioFoto servicioFoto;

    //Metodo para guardar un autor nuevo
    @Transactional(propagation = Propagation.NESTED)
    public void guardarAutor(String nombre, Boolean alta, MultipartFile archivo) throws ErroresDeServicio {

        validar(nombre, alta);

        //Se setean los atributos
        EntidadAutor autor = new EntidadAutor();

        autor.setNombre(nombre);
        autor.setAlta(alta);
        
        EntidadFoto foto = servicioFoto.guardarFoto(archivo);
        autor.setFoto(foto);

        repositorioAutor.save(autor);
    }

    //Metodo para validar los campos
    public void validar(String nombre, Boolean alta) throws ErroresDeServicio {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErroresDeServicio("Este campo es obligatorio.");
        }

        if (alta == null || alta.toString().trim().isEmpty()) {
            throw new ErroresDeServicio("Debe indicar si esta de alta o no.");
        }
    }

    @Transactional(readOnly = true)
    public EntidadAutor buscarPorId(String id) {
        return repositorioAutor.buscarPorId(id);
    }

    @Transactional(readOnly = true)
    public List<EntidadAutor> mostrarTodos() {
        return repositorioAutor.findAll();
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificarAutor(String id, String nombre, Boolean alta, MultipartFile archivo) throws ErroresDeServicio {
        validar(nombre, alta);
        
        Optional<EntidadAutor> optional = repositorioAutor.findById(id);
        
        if (optional.isPresent()) {
            EntidadAutor autor = optional.get();
            
            autor.setNombre(nombre);
            autor.setAlta(alta);
            
            String idFoto = null;
            if(autor.getFoto() != null) {
                idFoto = autor.getFoto().getId();
            }
            
            EntidadFoto foto = servicioFoto.actualizarFoto(idFoto, archivo);
            autor.setFoto(foto);
            
            repositorioAutor.save(autor);
        } else {
            throw new ErroresDeServicio("El autor que desea modificar no existe.");
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorNombre(String nombre) {
        Optional<EntidadAutor> optional = repositorioAutor.findById(nombre);

        if (optional.isPresent()) {
            repositorioAutor.delete(optional.get());
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId(String id) {
        Optional<EntidadAutor> optional = repositorioAutor.findById(id);

        if (optional.isPresent()) {
            repositorioAutor.delete(optional.get());
        }
    }
}
