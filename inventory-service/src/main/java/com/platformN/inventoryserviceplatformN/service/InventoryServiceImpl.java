package com.platformN.inventoryserviceplatformN.service;

import com.platformN.inventoryserviceplatformN.dto.InventoryResponse;
import com.platformN.inventoryserviceplatformN.model.Inventory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platformN.inventoryserviceplatformN.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;
	
	@Override
	@Transactional(readOnly = true)
	public boolean isInStock(String skuCode) {
		inventoryRepository.findBySkuCode(skuCode).isPresent();
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public List<InventoryResponse> areInStock(List<String> skuCodes) {
		return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
				.map(inventory ->
					InventoryResponse.builder()
							.skuCode(inventory.getSkuCode())
							.isInStock(inventory.getQuantity() > 0)
							.build()
				).toList();

	}

	
}
