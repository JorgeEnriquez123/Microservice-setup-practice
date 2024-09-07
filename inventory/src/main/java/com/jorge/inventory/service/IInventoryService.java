package com.jorge.inventory.service;

import com.jorge.inventory.model.Inventory;
import com.jorge.inventory.dto.InventoryDto;

public interface IInventoryService {
    void createInventory(InventoryDto inventoryDto);
    Inventory getInventory(Long productId);
    void updateInventory(InventoryDto inventoryDto);
    void deleteInventory(Long productId);
}
