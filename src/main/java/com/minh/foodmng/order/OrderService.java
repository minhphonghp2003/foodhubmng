package com.minh.foodmng.order;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {
    final private TableRepository tableRepository;
    final private OrderRepository orderRepository;
    public Table findById(Integer id){
        Table table= tableRepository.findById(id).get();
        return table;
    }
    public Page<Table> findAllTable(int page, int size ){
        return tableRepository.findAll(PageRequest.of(page,size));
    }
    public Page<Order> findAllOrder(int page, int size){
       return orderRepository.findAll(PageRequest.of(page,size));
    }
}
