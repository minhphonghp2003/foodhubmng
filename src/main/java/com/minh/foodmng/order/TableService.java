package com.minh.foodmng.order;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class TableService {
    final private TableRepository tableRepository;
    public Table findById(Integer id){
        Table table= tableRepository.findById(id).get();
        return table;
    }
    public Page<Table> findAll(int page, int size ){
        return tableRepository.findAll(PageRequest.of(page,size));
    }
}
