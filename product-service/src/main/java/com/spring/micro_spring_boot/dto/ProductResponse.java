package com.spring.micro_spring_boot.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductResponse {



    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
