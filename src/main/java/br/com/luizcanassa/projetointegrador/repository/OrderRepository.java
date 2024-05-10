package br.com.luizcanassa.projetointegrador.repository;

import br.com.luizcanassa.projetointegrador.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> getAllOrdersByOrderDate(LocalDateTime now);

}
