package com.platformN.inventoryserviceplatformN.service;

import com.platformN.inventoryserviceplatformN.dto.InventoryResponse;
import com.platformN.inventoryserviceplatformN.model.Inventory;

import java.util.List;

public interface InventoryService {

	public boolean isInStock(String skuCode);
	public List<InventoryResponse> areInStock(List<String> skuCodes);
	
}
