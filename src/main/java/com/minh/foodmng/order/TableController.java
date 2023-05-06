package com.minh.foodmng.order;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/table")
@PreAuthorize("hasAnyAuthority('admin','staff')")
@RequiredArgsConstructor
public class TableController {
    final private TableService tableService;

    @GetMapping("/all")
    public ResponseEntity<Page<Table>> getAllTable(int page, int size) {
        Page<Table> tables = tableService.findAll(page, size);
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Table> getTableBy(@PathVariable Integer id) {
        Table table = tableService.findById(id);
        return ResponseEntity.ok(table);
    }

}
