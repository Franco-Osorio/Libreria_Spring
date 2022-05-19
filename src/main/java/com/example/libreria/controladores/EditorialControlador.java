package com.example.libreria.controladores;

import com.example.libreria.entidades.EntidadEditorial;
import com.example.libreria.errores.ErroresDeServicio;
import com.example.libreria.servicios.ServicioEditorial;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
    
    @Autowired
    private ServicioEditorial servicioEditorial;
    
    @GetMapping("/editorialVista")
    public String editorial() {
        return "Editorial.html";
    }
    
    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre) {
        try {
            servicioEditorial.guardarEditorial(nombre);
            modelo.put("exito", "Registrado con éxito.");
        } catch (ErroresDeServicio ex) {
            modelo.put("error", ex.getMessage());
            return "Editorial.html";
        }
        return "Editorial.html";
    }
    
    @GetMapping("/mostrarEditorial")
    public String mostrarEditorial(ModelMap modelo) {
        modelo.addAttribute("listaEditoriales", servicioEditorial.mostrarTodos());
        return "MostrarEditorial.html";
    }
    
    @GetMapping("/modificarEditorial")
    public String modificarEditorial(ModelMap modelo) {
        modelo.addAttribute("editorialModificar", servicioEditorial.mostrarTodos());
        return "ModificarEditorial.html";
    }
    
    @PostMapping("/modificarEditorial")
    public String modificarNombreEditorial(ModelMap modelo, @RequestParam String id, @RequestParam String nombre) {
        
        try {
            servicioEditorial.modificarEditorial(id, nombre);
            modelo.put("exito", "Editorial modificada con éxito.");
        } catch (ErroresDeServicio ex) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "ModificarEditorial.html";
        }
        
        return "ModificarEditorial.html";
    }
}
