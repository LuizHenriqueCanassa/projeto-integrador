package br.com.luizcanassa.projetointegrador.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCustomerDTO {

    private String customerName;

    private String customerDocument;

    private String customerMobilePhone;

    private String customerAddress;

    private Integer customerAddressNumber;

    private String customerAddressCity;
}
