package br.com.luizcanassa.projetointegrador.service;

import br.com.luizcanassa.projetointegrador.domain.dto.CustomersDTO;
import br.com.luizcanassa.projetointegrador.domain.dto.NewCustomerDTO;
import br.com.luizcanassa.projetointegrador.domain.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    List<CustomersDTO> findAll();

    CustomerEntity findById(Long id);

    CustomerEntity save(NewCustomerDTO newCustomerDTO);
}
