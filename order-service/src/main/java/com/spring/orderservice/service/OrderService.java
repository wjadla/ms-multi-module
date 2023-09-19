package com.spring.orderservice.service;

import com.spring.orderservice.dto.InventoryResponse;
import com.spring.orderservice.dto.OrderLineItemsDto;
import com.spring.orderservice.dto.OrderRequest;
import com.spring.orderservice.model.Order;
import com.spring.orderservice.model.OrderLineItems;
import com.spring.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClient;

    private final Tracer tracer;
    public String placeOrder(OrderRequest request){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = request.getOrderLineItemsDtos().stream()
                .map(this::mapToDto)
                .toList();


        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList()
                .stream().map(OrderLineItems::getSkuCode).toList();

        log.info("Calling inventory service");

        //todo : zipkin span
        Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");
        try {
            tracer.withSpan(inventoryServiceLookup.start());
        } finally {
            inventoryServiceLookup.end();
        }
        //todo : call inventory service and place order if product is in stock
        InventoryResponse[] InventoryResponseArray = webClient.build().get().uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                // async request to http localhost
                .block();
        boolean allProductInStock = Arrays.stream(InventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);

        if (allProductInStock){
            orderRepository.save(order);
            log.info("order {} have been saved ", order.getId());
            return "order placed with success";
        } else {
            throw  new IllegalArgumentException("product is not found in stock please try again");
        }

    }

    private OrderLineItems mapToDto (OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItems.getQuantity());
        orderLineItems.setSkuCode(orderLineItems.getSkuCode());
        return orderLineItems;
    }
}
