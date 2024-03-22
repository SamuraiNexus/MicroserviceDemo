package com.example.search.service;

import com.example.search.pojo.dto.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface SearchService {
    ResponseDTO findEmployeeDetails();
}
