package br.com.luizcanassa.projetointegrador.service.impl;

import br.com.luizcanassa.projetointegrador.domain.dto.ProductsDTO;
import br.com.luizcanassa.projetointegrador.domain.entity.ProductEntity;
import br.com.luizcanassa.projetointegrador.exception.ObjectNotFoundException;
import br.com.luizcanassa.projetointegrador.repository.ProductRepository;
import br.com.luizcanassa.projetointegrador.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductsDTO> findAll() {
        return productRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ProductsDTO findById(Long id) {
        return productRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    @Override
    public ProductEntity save(ProductsDTO productsDTO) {
        final var newProduct = new ProductEntity();

        newProduct.setProductName(productsDTO.getProductName());
        newProduct.setPrice(productsDTO.getProductPrice());
        newProduct.setDescription(productsDTO.getProductDescription());

        return productRepository.save(newProduct);
    }

    @Override
    public ProductEntity update(final ProductsDTO productsDTO, final Long id) {
        final var updatedProduct = productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado!"));

        updatedProduct.setProductName(productsDTO.getProductName());
        updatedProduct.setPrice(productsDTO.getProductPrice());
        updatedProduct.setDescription(productsDTO.getProductDescription());

        return productRepository.save(updatedProduct);
    }

    private ProductsDTO mapToDto(ProductEntity productEntity) {
        return new ProductsDTO(
                productEntity.getId(),
                productEntity.getProductName(),
                productEntity.getPrice(),
                productEntity.getDescription()
        );
    }
}
