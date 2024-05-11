package br.com.luizcanassa.projetointegrador.domain.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomersEditDTO extends CustomersDTO {

    private String customerAddress;

    private Integer customerAddressNumber;

    private String customerAddressCity;

    public CustomersEditDTO(Long id, String name, String document, String mobilePhone, String customerAddress, Integer customerAddressNumber, String customerAddressCity) {
        super(id, name, document, mobilePhone);
        this.customerAddress = customerAddress;
        this.customerAddressNumber = customerAddressNumber;
        this.customerAddressCity = customerAddressCity;
    }
}
