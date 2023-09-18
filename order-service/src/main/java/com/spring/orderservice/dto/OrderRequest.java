package com.spring.orderservice.dto;

import com.spring.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {

    private List<OrderLineItemsDto> orderLineItemsDtos;
}
