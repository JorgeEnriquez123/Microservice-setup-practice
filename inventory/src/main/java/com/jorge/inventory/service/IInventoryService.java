package com.jorge.inventory.service;

import com.jorge.inventory.model.Inventory;
import com.jorge.inventory.dto.InventoryDto;

import java.util.List;

public interface IInventoryService {
    void createInventory(InventoryDto inventoryDto);
    Inventory getInventory(Long productId);
    List<Inventory> getAllInventory();
    void updateInventory(InventoryDto inventoryDto);
    void deleteInventory(Long productId);
}
