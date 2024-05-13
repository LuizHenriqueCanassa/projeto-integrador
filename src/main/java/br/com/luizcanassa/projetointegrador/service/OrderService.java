package br.com.luizcanassa.projetointegrador.service;

import br.com.luizcanassa.projetointegrador.domain.dto.EditOrderDTO;
import br.com.luizcanassa.projetointegrador.domain.dto.NewOrderDTO;
import br.com.luizcanassa.projetointegrador.domain.dto.OrderDTO;
import br.com.luizcanassa.projetointegrador.domain.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    List<OrderDTO> findAllOrders();

    OrderEntity save(NewOrderDTO newOrderDTO);

    EditOrderDTO findById(Long id);

    OrderEntity updateOrder(Long id, EditOrderDTO editOrderDTO);
}
