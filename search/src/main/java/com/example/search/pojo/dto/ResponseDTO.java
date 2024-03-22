package com.example.search.pojo.dto;

import com.example.hibernatedemo.pojo.entity.Employee;
import lombok.Data;

import java.util.Date;

@Data
public class ResponseDTO {
    private Employee[] employee;
    private String detailsMessage;
    private int code;
    private Date timeStamp;

}
