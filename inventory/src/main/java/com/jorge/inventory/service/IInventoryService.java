package com.jorge.inventory.service;

import com.jorge.inventory.model.Inventory;
import com.jorge.inventory.dto.InventoryDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IInventoryService {
    void createInventory(InventoryDto inventoryDto);
    Inventory getInventory(Long productId);
    List<Inventory> getAllInventory();
    boolean checkAvailability(Long productId, Integer quantity);
    void updateInventory(Long productId, Integer quantity);
    void deleteInventory(Long productId);
}
