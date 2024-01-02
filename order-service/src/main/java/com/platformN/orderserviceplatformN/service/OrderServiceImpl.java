package com.platformN.orderserviceplatformN.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.platformN.orderserviceplatformN.dto.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platformN.orderserviceplatformN.dto.OrderLineItemsDto;
import com.platformN.orderserviceplatformN.dto.OrderRequest;
import com.platformN.orderserviceplatformN.model.Order;
import com.platformN.orderserviceplatformN.model.OrderLineItems;
import com.platformN.orderserviceplatformN.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;

	private final WebClient webClient;

	@Override
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
				.stream()
				.map(this::mapToDto)
				.toList();
		
		order.setOrderLineItems(orderLineItems);

		List<String> skuCodes = order.getOrderLineItems().stream()
				.map(orderLineItems1 -> orderLineItems1.getSkuCode())
				.toList();

		// Call Inventory Service and place order if item is in stock
		InventoryResponse[] inventoryResponses = webClient.get()
				.uri("http://localhost:8083/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();

		boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(inventoryResponse -> inventoryResponse.isInStock());

		if(allProductsInStock) {
			orderRepository.save(order);
		}
		else {
			throw new IllegalArgumentException("Product is not in stock");
		}
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}
	
}
