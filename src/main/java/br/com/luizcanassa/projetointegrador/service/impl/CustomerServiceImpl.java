package br.com.luizcanassa.projetointegrador.service.impl;

import br.com.luizcanassa.projetointegrador.domain.dto.CustomersDTO;
import br.com.luizcanassa.projetointegrador.domain.dto.CustomersEditDTO;
import br.com.luizcanassa.projetointegrador.domain.dto.NewCustomerDTO;
import br.com.luizcanassa.projetointegrador.domain.entity.CustomerEntity;
import br.com.luizcanassa.projetointegrador.exception.ObjectNotFoundException;
import br.com.luizcanassa.projetointegrador.repository.CustomerRepository;
import br.com.luizcanassa.projetointegrador.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomersDTO> findAll() {
        return customerRepository.findAll().stream().map((this::mapToDto)).collect(Collectors.toList());
    }

    @Override
    public CustomersEditDTO findById(final Long id) {
        return customerRepository.findById(id).map(this::mapToEditDto).orElse(null);
    }

    @Override
    public CustomerEntity save(final NewCustomerDTO newCustomerDTO) {
        var newCustomer = new CustomerEntity();

        newCustomer.setCustomerName(newCustomerDTO.getCustomerName());
        newCustomer.setCustomerDocument(newCustomerDTO.getCustomerDocument());
        newCustomer.setCustomerMobilePhone(newCustomerDTO.getCustomerMobilePhone());
        newCustomer.setCustomerAddress(newCustomerDTO.getCustomerAddress());
        newCustomer.setCustomerAddressNumber(newCustomerDTO.getCustomerAddressNumber());
        newCustomer.setCustomerCity(newCustomerDTO.getCustomerAddressCity());

        return customerRepository.save(newCustomer);
    }

    @Override
    public CustomerEntity update(CustomersEditDTO customersEditDTO) {
        var customerEntity = customerRepository.findById(customersEditDTO.getId()).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!"));

        customerEntity.setId(customersEditDTO.getId());
        customerEntity.setCustomerName(customersEditDTO.getCustomerName());
        customerEntity.setCustomerDocument(customersEditDTO.getCustomerDocument());
        customerEntity.setCustomerMobilePhone(customersEditDTO.getCustomerMobilePhone());
        customerEntity.setCustomerAddress(customersEditDTO.getCustomerAddress());
        customerEntity.setCustomerAddressNumber(customersEditDTO.getCustomerAddressNumber());
        customerEntity.setCustomerCity(customersEditDTO.getCustomerAddressCity());

        return customerRepository.save(customerEntity);
    }

    private CustomersDTO mapToDto(final CustomerEntity customerEntity) {
        return new CustomersDTO(
                customerEntity.getId(),
                customerEntity.getCustomerName(),
                customerEntity.getCustomerDocument(),
                customerEntity.getCustomerMobilePhone()
        );
    }

    private CustomersEditDTO mapToEditDto(final CustomerEntity customerEntity) {
        return new CustomersEditDTO(
                customerEntity.getId(),
                customerEntity.getCustomerName(),
                customerEntity.getCustomerDocument(),
                customerEntity.getCustomerMobilePhone(),
                customerEntity.getCustomerAddress(),
                customerEntity.getCustomerAddressNumber(),
                customerEntity.getCustomerCity()
        );
    }
}
