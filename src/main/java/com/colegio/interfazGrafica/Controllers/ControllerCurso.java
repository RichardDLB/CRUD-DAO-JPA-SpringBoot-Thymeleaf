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

import com.colegio.interfazGrafica.Entity.Curso;
import com.colegio.interfazGrafica.Repository.I_CursoRepository;

@Controller
public class ControllerCurso {

  private String mensaje = "Por Favor ingrese los datos del Curso";

  @Autowired
  private I_CursoRepository cursoRepository;

  @GetMapping("/cursos")
  public String listaCursos(@RequestParam(required = false) String buscarCurso, Model model) {
    List<Curso> cursos;
    List<Object[]> alumnosPorCursos = cursoRepository.cantidadDeAlumnos();
    Curso curso = new Curso();

    if (buscarCurso != null && !buscarCurso.isEmpty()) {
      cursos = cursoRepository.findByTituloContainingOrProfesorContaining(buscarCurso, buscarCurso);
    } else {
      cursos = cursoRepository.findAll();
    }
    model.addAttribute("mensaje", mensaje);
    model.addAttribute("cursoTabla", cursos);
    model.addAttribute("cursoGuardar", curso);
    model.addAttribute("alumnosPorCursos", alumnosPorCursos);
    return "cursos";
  }

  @PostMapping("/guardar")
  public String guardar(@ModelAttribute("cursoGuardar") Curso cr) {
    cursoRepository.save(cr);
    return "redirect:cursos";
  } 

  @GetMapping("/borrar/{id}")
  public String borrar(@PathVariable(name = "id") Long id) {
    cursoRepository.deleteById(id);
    return "redirect:/cursos";

  }

}
