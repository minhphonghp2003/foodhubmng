package com.minh.foodmng.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@PreAuthorize("hasAnyAuthority('admin','staff')")
@RequiredArgsConstructor
public class OrderController {
    final private OrderService orderService;


    @GetMapping("/table/all ")
    public ResponseEntity<Page<Table>> getAllTable(int page, int size) {
        Page<Table> tables = orderService.findAllTable(page, size);
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/table/{id}")
    public ResponseEntity<Table> getTableBy(@PathVariable Integer id) {
        Table table = orderService.findById(id);
        return ResponseEntity.ok(table);
    }
    @GetMapping("/all")
    public ResponseEntity<Page<Order>> getAllOrder(int page, int size) {
        Page<Order> orders = orderService.findAllOrder(page, size);
        return ResponseEntity.ok(orders);
    }

}
