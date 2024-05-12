package br.com.luizcanassa.projetointegrador.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDTO {

    private Long id;

    private String productName;

    private BigDecimal productPrice;

    private String productDescription;
}
