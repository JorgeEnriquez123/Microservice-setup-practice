package com.jorge.inventory.service.impl;

import com.jorge.inventory.handler.exception.ProductInventoryNotFoundException;
import com.jorge.inventory.mapper.InventoryMapper;
import com.jorge.inventory.model.Inventory;
import com.jorge.inventory.repository.InventoryRepository;
import com.jorge.inventory.dto.InventoryDto;
import com.jorge.inventory.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService {
    public final InventoryMapper mapper;
    public final InventoryRepository inventoryRepository;

    @Transactional
    @Override
    public void createInventory(InventoryDto inventoryDto) {
        Inventory inventory = mapper.toEntity(inventoryDto);
        inventoryRepository.save(inventory);
    }

    @Transactional
    @Override
    public Inventory getInventory(Long productId) {
        return inventoryRepository.findByProductId(productId).orElseThrow(
                () -> new ProductInventoryNotFoundException("No Inventory by product id: " + productId));
    }

    @Transactional
    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Transactional
    @Override
    public void updateInventory(InventoryDto inventoryDto) {
        Inventory inventory = inventoryRepository.findByProductId(inventoryDto.getProductId()).orElseThrow(
                () -> new ProductInventoryNotFoundException("No Inventory by product id: " + inventoryDto.getProductId())
        );
        inventory.setQuantity(inventoryDto.getQuantity());
        inventory.setLastUpdated(LocalDateTime.now());

        inventoryRepository.save(inventory);
    }

    @Transactional
    @Override
    public void deleteInventory(Long productId) {
        inventoryRepository.deleteByProductId(productId);
    }
}
