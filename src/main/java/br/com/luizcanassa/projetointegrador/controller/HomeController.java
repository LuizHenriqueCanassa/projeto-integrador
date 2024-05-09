package br.com.luizcanassa.projetointegrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(ModelMap model) {
        model.addAttribute("title", "Projeto Integrador");

        return "home";
    }
}
