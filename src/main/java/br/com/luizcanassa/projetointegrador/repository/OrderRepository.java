package br.com.luizcanassa.projetointegrador.repository;

import br.com.luizcanassa.projetointegrador.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "from OrderEntity o where o.orderDate BETWEEN :startDate AND :endDate")
    List<OrderEntity> getAllBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
