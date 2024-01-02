package com.platformN.orderserviceplatformN.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.platformN.orderserviceplatformN.dto.OrderRequest;
import com.platformN.orderserviceplatformN.service.OrderServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderServiceImpl orderServiceImpl;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		orderServiceImpl.placeOrder(orderRequest);
		return "Order is placed.";
	}
	
}
