package com.example.libreria.servicios;

import com.example.libreria.entidades.EntidadAutor;
import com.example.libreria.entidades.EntidadEditorial;
import com.example.libreria.entidades.EntidadFoto;
import com.example.libreria.entidades.EntidadLibro;
import com.example.libreria.enumeraciones.GeneroLiterario;
import com.example.libreria.errores.ErroresDeServicio;
import com.example.libreria.repositorios.RepositorioLibro;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServicioLibro {

    @Autowired
    private RepositorioLibro repositorioLibro;
    
    @Autowired
    private ServicioFoto servicioFoto;

    @Transactional(propagation = Propagation.NESTED)
    public void guardarLibroLong(Long isbn, String titulo, Date año, GeneroLiterario generoLiterario, Integer ejemplaresTotales, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, EntidadAutor autor, EntidadEditorial editorial, MultipartFile archivo) throws ErroresDeServicio {

        validar(isbn, titulo, año, generoLiterario, ejemplaresTotales, ejemplaresPrestados, ejemplaresRestantes, alta, autor, editorial);

        //Se setean los atributos
        EntidadLibro libro = new EntidadLibro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAño(año);
        libro.setGeneroLiterario(generoLiterario);
        libro.setEjemplaresTotales(ejemplaresTotales);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplaresRestantes);
        libro.setAlta(alta);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        EntidadFoto foto = servicioFoto.guardarFoto(archivo);
        libro.setFoto(foto);

        repositorioLibro.save(libro);
    }

    //Se validan los campos
    public void validar(Long isbn, String titulo, Date año, GeneroLiterario generoLiterario, Integer ejemplaresTotales, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, EntidadAutor autor, EntidadEditorial editorial) throws ErroresDeServicio {

        if (isbn == null || isbn.toString().trim().isEmpty()) {
            throw new ErroresDeServicio("El campo isbn es olbigatorio.");
        }

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ErroresDeServicio("El campo Titulo es obligatorio.");
        }

        if (año == null || año.toString().trim().isEmpty()) {
            throw new ErroresDeServicio("El campo Año es obligatorio.");
        }

        if (generoLiterario == null || generoLiterario.toString().trim().isEmpty()) {
            throw new ErroresDeServicio("El campo Genero Literario es obligatorio.");
        }

        if (ejemplaresTotales == null || ejemplaresTotales.toString().trim().isEmpty()) {
            throw new ErroresDeServicio("El campo Ejemplares Totales es obligatorio.");
        }

        if (ejemplaresPrestados == null || ejemplaresPrestados.toString().trim().isEmpty()) {
            throw new ErroresDeServicio("El campo Ejemplares Prestados es obligatorio.");
        }

        if (ejemplaresRestantes == null || ejemplaresRestantes.toString().trim().isEmpty()) {
            throw new ErroresDeServicio("El campo Ejemplares Restantes es obligatorio.");
        }

        if (alta == null || alta.toString().trim().isEmpty()) {
            throw new ErroresDeServicio("Debe indicar si está de alta o no.");
        }

        if (autor == null || autor.toString().trim().isEmpty()) {
            throw new ErroresDeServicio("El campo Autor es obligatorio.");
        }

        if (editorial == null || editorial.toString().trim().isEmpty()) {
            throw new ErroresDeServicio("El campo Editorial es obligatorio.");
        }
    }

    @Transactional(readOnly = true)
    public EntidadLibro buscarPorId(String id) {
        Optional<EntidadLibro> optional = repositorioLibro.findById(id);
        
        return repositorioLibro.buscarPorId(id);
        
//        if (optional.isPresent()) {
//            return repositorioLibro.buscarPorId(id);
//        } else {
//            return null;
//        }
    }

    @Transactional(readOnly = true)
    public List<EntidadLibro> buscarPorGenero(GeneroLiterario generoLiterario) {
        return repositorioLibro.buscarPorGeneroLiterario(generoLiterario);
    }
    
    @Transactional(readOnly = true)
    public List<EntidadLibro> buscarPorAutor(EntidadAutor autor) {
        return repositorioLibro.buscarPorAutor(autor);
    }

    @Transactional(readOnly = true)
    public List<EntidadLibro> mostrarTodos() {
        return repositorioLibro.findAll();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void modificarRegistroDeLibro(String id, Long isbn, String titulo, Date año, GeneroLiterario generoLiterario, Integer ejemplaresTotales, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, EntidadAutor autor, EntidadEditorial editorial, MultipartFile archivo) throws ErroresDeServicio {
        validar(isbn, titulo, año, generoLiterario, ejemplaresTotales, ejemplaresPrestados, ejemplaresRestantes, alta, autor, editorial);

        Optional<EntidadLibro> optional = repositorioLibro.findById(id);

        if (optional.isPresent()) {
            EntidadLibro libro = optional.get();

            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAño(año);
            libro.setGeneroLiterario(generoLiterario);
            libro.setEjemplaresTotales(ejemplaresTotales);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAlta(alta);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            
            String idFoto = null;
            if(libro.getFoto() != null) {
                idFoto = libro.getFoto().getId();
            }
            
            EntidadFoto foto = servicioFoto.actualizarFoto(idFoto, archivo);
            libro.setFoto(foto);

            repositorioLibro.save(libro);
        } else {
            throw new ErroresDeServicio("El libro que desea modificar no existe.");
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorNombre(String nombre) {
        Optional<EntidadLibro> optional = repositorioLibro.findById(nombre);

        if (optional.isPresent()) {
            repositorioLibro.delete(optional.get());
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId(String id) {
        Optional<EntidadLibro> optional = repositorioLibro.findById(id);

        if (optional.isPresent()) {
            repositorioLibro.delete(optional.get());
        }
    }
}
