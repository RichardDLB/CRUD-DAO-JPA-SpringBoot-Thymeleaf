package com.colegio.interfazGrafica.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ControllerHome {
     @RequestMapping({"/", "index", "home"})
  public String home() {
    return "home";
  }
}
