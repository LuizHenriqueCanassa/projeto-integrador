package br.com.luizcanassa.projetointegrador.repository;

import br.com.luizcanassa.projetointegrador.domain.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
