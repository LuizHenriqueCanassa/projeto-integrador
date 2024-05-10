package br.com.luizcanassa.projetointegrador.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomersDTO {

    private Long id;

    private String name;

    private String document;

    private String mobilePhone;
}
