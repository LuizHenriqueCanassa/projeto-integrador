package br.com.luizcanassa.projetointegrador.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "CUSTOMER", uniqueConstraints = { @UniqueConstraint(name = "UK_DOCUMENT", columnNames = {"DOCUMENT"})})
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 80)
    private String customerName;

    @Column(name = "DOCUMENT", nullable = false, length = 11)
    private String customerDocument;

    @Column(name = "MOBILE_PHONE", nullable = false, length = 11)
    private String customerMobilePhone;

    @Column(name = "ADDRESS", nullable = false, length = 100)
    private String customerAddress;

    @Column(name = "CITY", nullable = false, length = 100)
    private String customerCity;

    @Column(name = "ADDRESS_NUMBER", nullable = false, length = 100)
    private Integer customerAddressNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<OrderEntity> orders;

    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
