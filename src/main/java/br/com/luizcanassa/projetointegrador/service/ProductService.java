package br.com.luizcanassa.projetointegrador.service;

import br.com.luizcanassa.projetointegrador.domain.dto.ProductsDTO;
import br.com.luizcanassa.projetointegrador.domain.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductsDTO> findAll();

    ProductsDTO findById(Long id);

    ProductEntity save(ProductsDTO productsDTO);

    ProductEntity update(ProductsDTO productsDTO, Long id);
}
