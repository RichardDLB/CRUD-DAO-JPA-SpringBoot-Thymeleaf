package com.colegio.interfazGrafica.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.colegio.interfazGrafica.Entity.Alumno;
import com.colegio.interfazGrafica.Entity.Curso;
import com.colegio.interfazGrafica.Repository.I_AlumnoRepository;
import com.colegio.interfazGrafica.Repository.I_CursoRepository;

@Controller
public class ControllerAlumno {

  private String mensaje = "Por Favor ingrese los datos del Alumno";

  @Autowired
  I_AlumnoRepository alumnoRepository;

  @Autowired
  I_CursoRepository cursoRepository;

  
  @PostMapping("/guardarAlumnos")
  public String guardarAlumnos(@ModelAttribute("alumnosGuardar") Alumno al) {
    alumnoRepository.save(al);
    return "redirect:alumnos";
  }

  @GetMapping("/alumnos")
  public String mostrarAlumnos(@RequestParam(required = false) String buscarAlumno, Model model) {

    List<Curso> listaSelect = cursoRepository.findAll();
    Alumno alumno = new Alumno();
    List<Alumno> listaAlumnos;
    if (buscarAlumno != null && !buscarAlumno.isEmpty()) {
      listaAlumnos = alumnoRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(buscarAlumno, buscarAlumno);
    } else {
      listaAlumnos = alumnoRepository.findAll();
    }

    model.addAttribute("mensaje", mensaje);
    model.addAttribute("alumnoSelect", listaSelect);
    model.addAttribute("alumnosGuardar", alumno);
    model.addAttribute("alumnosHtmlTabla", listaAlumnos);

    return "alumnos";
  }

  @GetMapping("/borrarAlumno/{id}")
  public String borrarAlumno(@PathVariable(name = "id") Long id) {
    alumnoRepository.deleteById(id);
    return "redirect:/alumnos";

  }

}
