package com.platformN.inventoryserviceplatformN;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.platformN.inventoryserviceplatformN.model.Inventory;
import com.platformN.inventoryserviceplatformN.repository.InventoryRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServicePlatformNApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServicePlatformNApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("test");
			inventory.setQuantity(1);
			
			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("test-2");
			inventory2.setQuantity(0);
			
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory2);
		};
	}

}
