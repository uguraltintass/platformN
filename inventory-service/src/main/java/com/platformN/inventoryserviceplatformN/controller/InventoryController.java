package com.platformN.inventoryserviceplatformN.controller;

import com.platformN.inventoryserviceplatformN.dto.InventoryResponse;
import com.platformN.inventoryserviceplatformN.model.Inventory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.platformN.inventoryserviceplatformN.service.InventoryServiceImpl;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryServiceImpl inventoryServiceImpl;
	
	@GetMapping("/{sku-code}")
	@ResponseStatus(HttpStatus.OK)
	public boolean isInStock(@PathVariable("sku-code") String skuCode) {
		return inventoryServiceImpl.isInStock(skuCode);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> areInStock(@RequestParam List<String> skuCode) {
		return inventoryServiceImpl.areInStock(skuCode);
	}

}
