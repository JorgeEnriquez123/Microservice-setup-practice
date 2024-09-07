package com.jorge.inventory.service.impl;

import com.jorge.inventory.mapper.InventoryMapper;
import com.jorge.inventory.model.Inventory;
import com.jorge.inventory.repository.InventoryRepository;
import com.jorge.inventory.dto.InventoryDto;
import com.jorge.inventory.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
        return inventoryRepository.findByProductId(productId).orElse(null);
    }

    @Transactional
    @Override
    public void updateInventory(InventoryDto inventoryDto) {
        Inventory inventory = inventoryRepository.findByProductId(inventoryDto.getProductId()).orElse(null);
        assert inventory != null;
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
