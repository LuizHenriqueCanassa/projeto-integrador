package br.com.luizcanassa.projetointegrador.controller;

import br.com.luizcanassa.projetointegrador.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final OrderService orderService;

    public HomeController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String home(ModelMap model) {
        model.addAttribute("title", "Projeto Integrador");
        model.addAttribute("ordersOpenToday", orderService.findAllOrderOpenToday());

        return "home";
    }
}
