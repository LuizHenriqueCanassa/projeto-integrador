package br.com.luizcanassa.projetointegrador.controller;

import br.com.luizcanassa.projetointegrador.domain.dto.CustomersDTO;
import br.com.luizcanassa.projetointegrador.domain.dto.EditOrderDTO;
import br.com.luizcanassa.projetointegrador.domain.dto.NewOrderDTO;
import br.com.luizcanassa.projetointegrador.domain.dto.ProductsDTO;
import br.com.luizcanassa.projetointegrador.domain.enums.OrderStatus;
import br.com.luizcanassa.projetointegrador.service.CustomerService;
import br.com.luizcanassa.projetointegrador.service.OrderService;
import br.com.luizcanassa.projetointegrador.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderController(final OrderService orderService, final CustomerService customerService, final ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping
    public String index(final ModelMap model) {
        model.addAttribute("title", "Projeto Integrador - Pedidos");
        model.addAttribute("orders", orderService.findAllOrders());

        return "orders/index";
    }

    @GetMapping("/new")
    public String newOrder(final ModelMap model) {
        model.addAttribute("title", "Projeto Integrador - Novo Pedido");
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("products", productService.findAll());

        return "orders/new_order";
    }

    @PostMapping("/new")
    public String saveOrder(final NewOrderDTO newOrderDTO, final ModelMap model) {
        try {
            orderService.save(newOrderDTO);

            return "redirect:/orders";
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return "redirect:/orders/new";
        }
    }

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable("id") final Long id, final ModelMap model) {
        model.addAttribute("title", "Projeto Integrador - Editar Pedido");
        final EditOrderDTO order = orderService.findById(id);
        System.out.println(order);
        final List<CustomersDTO> allCustomers = customerService.findAll();
        final List<ProductsDTO> allProducts = productService.findAll();
        final CustomersDTO customerSelected = allCustomers.stream().filter(customer -> customer.getId().equals(order.getCustomerId())).findFirst().orElse(null);

        model.addAttribute("customers", allCustomers);
        model.addAttribute("products", allProducts);
        model.addAttribute("order", order);
        model.addAttribute("customerSelected", customerSelected);
        model.addAttribute("orderStatus", OrderStatus.values());

        return "orders/edit_order";
    }

    @PostMapping("/edit/{id}")
    public String saveEditOrder(@PathVariable("id") final Long id, final EditOrderDTO editOrderDTO, final ModelMap model) {
        try {
            orderService.updateOrder(id, editOrderDTO);

            return "redirect:/orders";
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return "redirect:/orders/edit/" + id;
        }
    }
}
