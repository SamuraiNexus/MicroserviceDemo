package com.example.search.controller;

import com.example.search.pojo.dto.ResponseDTO;
import com.example.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private final SearchService service;

    @Autowired
    public SearchController(@Qualifier("searchServiceImpl") SearchService service) {
        this.service = service;
    }

    @GetMapping("/employee/search")
    public ResponseEntity<?> getEmployee() {
        ResponseDTO responseDTO = service.findEmployeeDetails();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
