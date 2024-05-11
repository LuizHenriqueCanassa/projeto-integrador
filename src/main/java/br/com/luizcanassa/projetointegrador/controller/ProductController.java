package br.com.luizcanassa.projetointegrador.controller;

import br.com.luizcanassa.projetointegrador.domain.dto.ProductsDTO;
import br.com.luizcanassa.projetointegrador.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listAllProducts(final ModelMap model) {
        model.addAttribute("title", "Projeto Integrador - Produtos");
        model.addAttribute("products", productService.findAll());

        return "products/index";
    }

    @GetMapping("/new")
    public String newProduct(final ModelMap model) {
        model.addAttribute("title", "Projeto Integrador - Cadastrar Produtos");

        return "products/new_product";
    }

    @PostMapping("/new")
    public String saveNewProduct(@ModelAttribute final ProductsDTO productsDTO, final ModelMap model) {
        try {
            productService.save(productsDTO);

            return "redirect:/products";
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return "redirect:/products/new";
        }
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable final Long id, ModelMap model) {
        model.addAttribute("title", "Projeto Integrador - Editar Produto");
        model.addAttribute("product", productService.findById(id));

        return "products/edit_product";
    }

    @PostMapping("/edit/{id}")
    public String saveEditProduct(@ModelAttribute final ProductsDTO productsDTO, @PathVariable final Long id) {
        try {
            productService.update(productsDTO, id);

            return "redirect:/products";
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return "redirect:/products/edit/" + id;
        }
    }
}
