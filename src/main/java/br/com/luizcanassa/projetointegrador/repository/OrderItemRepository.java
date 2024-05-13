package br.com.luizcanassa.projetointegrador.repository;

import br.com.luizcanassa.projetointegrador.domain.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    void deleteByOrderId(Long orderId);
}
