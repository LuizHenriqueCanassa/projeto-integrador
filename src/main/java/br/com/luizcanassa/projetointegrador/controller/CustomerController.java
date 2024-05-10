package br.com.luizcanassa.projetointegrador.controller;

import br.com.luizcanassa.projetointegrador.domain.dto.NewCustomerDTO;
import br.com.luizcanassa.projetointegrador.domain.entity.CustomerEntity;
import br.com.luizcanassa.projetointegrador.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String listAllCustomers(ModelMap model) {
        model.addAttribute("title", "Projeto Integrador - Clientes");
        model.addAttribute("customers", customerService.findAll());

        return "customer/index";
    }

    @GetMapping("/new")
    public String newCustomer(ModelMap model) {
        model.addAttribute("title", "Projeto Integrador - Cadastrar Cliente");

        return "customer/new_customer";
    }

    @PostMapping
    public String saveNewCustomer(@ModelAttribute NewCustomerDTO newCustomer, ModelMap model) {

        try {
            customerService.save(newCustomer);

            return "redirect:/customers";
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return "redirect:/customers/new";
        }
    }
}
