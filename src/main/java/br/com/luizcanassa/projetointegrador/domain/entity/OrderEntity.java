package br.com.luizcanassa.projetointegrador.domain.entity;

import br.com.luizcanassa.projetointegrador.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "CUSTOMER_ID", foreignKey = @ForeignKey(name = "FK_CUSTOMER_ID"))
    @ManyToOne(cascade = CascadeType.ALL)
    private CustomerEntity customer;

    @Column(name = "AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "STATUS", nullable = false)
    private String status = OrderStatus.AWAITING.toString();

    @OneToMany(mappedBy = "order")
    List<OrderItemEntity> orderItems;

    @CreationTimestamp
    @Column(name = "ORDER_DATE", nullable = false)
    private LocalDateTime orderDate;
}
