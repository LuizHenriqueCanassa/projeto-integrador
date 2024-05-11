package br.com.luizcanassa.projetointegrador.controller;

import br.com.luizcanassa.projetointegrador.domain.dto.CustomersEditDTO;
import br.com.luizcanassa.projetointegrador.domain.dto.NewCustomerDTO;
import br.com.luizcanassa.projetointegrador.domain.entity.CustomerEntity;
import br.com.luizcanassa.projetointegrador.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("title", "Projeto Integrador - Editar Cliente");
        model.addAttribute("customer", customerService.findById(id));

        return "customer/edit_customer";
    }

    @PostMapping("/edit/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @ModelAttribute CustomersEditDTO customersEdit, ModelMap model) {
        try {
            customerService.update(customersEdit);

            return "redirect:/customers";
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return "redirect:/customers/edit/" + id;
        }
    }
}
