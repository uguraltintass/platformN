package com.platformN.orderserviceplatformN.service;

import com.platformN.orderserviceplatformN.dto.OrderRequest;

public interface OrderService {

	public void placeOrder(OrderRequest orderRequest);
	
}
