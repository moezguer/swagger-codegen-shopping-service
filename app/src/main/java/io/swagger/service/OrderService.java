package io.swagger.service;

import io.swagger.database.model.OrderEntity;
import io.swagger.database.repository.OrderRepository;
import io.swagger.dto.model.Order;
import io.swagger.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderRepository;
    private Object NotFoundException;

    public List<Order> getOrdersByCustomer_Email(String email) {

        final List<OrderEntity> orderEntityList = orderRepository.getOrderEntitiesByCustomer_Email(email);

        final List<Order> orderList = orderEntityList.stream()
                                                     .map(orderEntity -> modelMapper.map(orderEntity, Order.class))
                                                     .collect(Collectors.toList());
        return orderList;
    }

    public Order getOrderById(UUID orderId) throws NotFoundException {

        final OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(404, "Order Not Found"));
        final Order order = modelMapper.map(orderEntity, Order.class);
        return order;

    }



}
