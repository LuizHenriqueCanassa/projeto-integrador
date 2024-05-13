package br.com.luizcanassa.projetointegrador.service.impl;

import br.com.luizcanassa.projetointegrador.domain.dto.EditOrderDTO;
import br.com.luizcanassa.projetointegrador.domain.dto.NewOrderDTO;
import br.com.luizcanassa.projetointegrador.domain.dto.OrderDTO;
import br.com.luizcanassa.projetointegrador.domain.entity.CustomerEntity;
import br.com.luizcanassa.projetointegrador.domain.entity.OrderEntity;
import br.com.luizcanassa.projetointegrador.domain.entity.OrderItemEntity;
import br.com.luizcanassa.projetointegrador.domain.entity.ProductEntity;
import br.com.luizcanassa.projetointegrador.domain.enums.OrderStatus;
import br.com.luizcanassa.projetointegrador.exception.ObjectNotFoundException;
import br.com.luizcanassa.projetointegrador.repository.CustomerRepository;
import br.com.luizcanassa.projetointegrador.repository.OrderItemRepository;
import br.com.luizcanassa.projetointegrador.repository.OrderRepository;
import br.com.luizcanassa.projetointegrador.repository.ProductRepository;
import br.com.luizcanassa.projetointegrador.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(final OrderRepository orderRepository, final OrderItemRepository orderItemRepository, final CustomerRepository customerRepository, final ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<OrderDTO> findAllOrders() {
        return orderRepository.findAll().stream().map(this::mapToOrderDTO).collect(Collectors.toList());
    }

    @Override
    public OrderEntity save(final NewOrderDTO newOrderDTO) {
        final var customerEntity = customerRepository.findById(newOrderDTO.getCustomerId()).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!"));
        final var products = productRepository.findAllById(newOrderDTO.getProductIds());

        if (products.isEmpty()) {
            throw new ObjectNotFoundException("Nenhum produto encontrado!");
        }

        final var newOrder = orderRepository.save(mapToOrderEntity(customerEntity, products));

        final var orderItemEntities = orderItemRepository.saveAll(products.stream().map(productEntity -> mapToOrderItem(productEntity, newOrder)).collect(Collectors.toList()));

        newOrder.setOrderItems(orderItemEntities);

        return orderRepository.save(newOrder);
    }

    @Override
    public EditOrderDTO findById(final Long id) {
        return mapToEditOrderDTO(orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado!")));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderEntity updateOrder(final Long id, final EditOrderDTO editOrderDTO) {
        final var orderEntity = orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado!"));
        final var products = productRepository.findAllById(editOrderDTO.getProductIds());

        if (!Objects.equals(orderEntity.getCustomer().getId(), editOrderDTO.getCustomerId())) {
            orderEntity.setCustomer(customerRepository.findById(editOrderDTO.getCustomerId()).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!")));
        }

        orderItemRepository.deleteByOrderId(id);

        final var orderItemEntities = orderItemRepository.saveAll(products.stream().map(productEntity -> mapToOrderItem(productEntity, orderEntity)).collect(Collectors.toList()));

        orderEntity.setOrderItems(orderItemEntities);
        orderEntity.setAmount(products.stream().map(ProductEntity::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        orderEntity.setStatus(OrderStatus.statusValueToEnum(editOrderDTO.getStatus()));

        return orderRepository.save(orderEntity);
    }

    private OrderDTO mapToOrderDTO(final OrderEntity orderEntity) {
        final var orderDto = new OrderDTO();

        orderDto.setId(orderEntity.getId());
        orderDto.setCustomerName(orderEntity.getCustomer().getCustomerName());
        orderDto.setOrderStatus(orderEntity.getStatus().getDescription());
        orderDto.setTotalPrice(orderEntity.getAmount());
        orderDto.setQuantityItems(orderEntity.getOrderItems().size());
        orderDto.setOrderDate(orderEntity.getOrderDate());
        orderDto.setOrderItems(orderEntity.getOrderItems().stream().map(this::mapToOrderItemDTO).collect(Collectors.toList()));

        return orderDto;
    }

    private EditOrderDTO mapToEditOrderDTO(final OrderEntity orderEntity) {
        final var orderDto = new EditOrderDTO();

        orderDto.setId(orderEntity.getId());
        orderDto.setCustomerId(orderEntity.getCustomer().getId());
        orderDto.setProductIds(orderEntity.getOrderItems().stream().map(orderItemEntity -> orderItemEntity.getProduct().getId()).collect(Collectors.toList()));
        orderDto.setStatus(orderEntity.getStatus().getStatusValue());

        return orderDto;
    }

    private OrderEntity mapToOrderEntity(final CustomerEntity customer, final List<ProductEntity> products) {
        final var orderEntity = new OrderEntity();

        orderEntity.setCustomer(customer);
        orderEntity.setAmount(products.stream().map(ProductEntity::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

        return orderEntity;
    }

    private OrderItemEntity mapToOrderItem(final ProductEntity product, final OrderEntity orderEntity) {
        final var orderItemEntity = new OrderItemEntity();

        orderItemEntity.setOrder(orderEntity);
        orderItemEntity.setProduct(product);

        return orderItemEntity;
    }

    private OrderDTO.OrderItemDTO mapToOrderItemDTO(final OrderItemEntity orderItemEntity) {
        final var orderItemDto = new OrderDTO.OrderItemDTO();

        orderItemDto.setProductId(orderItemEntity.getProduct().getId());
        orderItemDto.setProductName(orderItemEntity.getProduct().getProductName());
        orderItemDto.setProductPrice(orderItemEntity.getProduct().getPrice());

        return orderItemDto;
    }
}
