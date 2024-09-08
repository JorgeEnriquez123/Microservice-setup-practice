package com.jorge.inventory.controller;

import com.jorge.inventory.dto.InventoryDto;
import com.jorge.inventory.model.Inventory;
import com.jorge.inventory.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final IInventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Void> createInventory(@RequestBody InventoryDto inventoryDto) {
        inventoryService.createInventory(inventoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory(){
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> getProductInventory(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.getInventory(productId));
    }

    @GetMapping("/{productId}/check-availability")
    public ResponseEntity<Boolean> checkAvailability(@PathVariable Long productId, @RequestParam Integer quantity) {
        boolean isAvailable = inventoryService.checkAvailability(productId, quantity);
        return ResponseEntity.ok(isAvailable);
    }

    @PutMapping("/{productId}/reduce-stock")
    public ResponseEntity<Void> updateInventory(@PathVariable Long productId, @RequestParam Integer quantity) {
        inventoryService.updateInventory(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long productId) {
        inventoryService.deleteInventory(productId);
        return ResponseEntity.noContent().build();
    }
}
