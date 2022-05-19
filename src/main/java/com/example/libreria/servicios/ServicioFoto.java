package com.example.libreria.servicios;

import com.example.libreria.entidades.EntidadFoto;
import com.example.libreria.errores.ErroresDeServicio;
import com.example.libreria.repositorios.RepositorioFoto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServicioFoto {
    
    @Autowired
    RepositorioFoto repositorioFoto;

    public EntidadFoto guardarFoto(MultipartFile archivo) throws ErroresDeServicio {

        if (archivo != null) {
            try {
                EntidadFoto foto = new EntidadFoto();

                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return repositorioFoto.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
    
    public EntidadFoto actualizarFoto(String id, MultipartFile archivo) throws ErroresDeServicio {
        if (archivo != null) {
            try {
                EntidadFoto foto = new EntidadFoto();
                
                if(id != null) {
                    Optional<EntidadFoto> optional = repositorioFoto.findById(id);
                    
                    if(optional.isPresent()) {
                        foto = optional.get();
                    }
                }
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return repositorioFoto.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
