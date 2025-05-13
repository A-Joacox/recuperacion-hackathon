package com.example.recuperacionhackaton1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(inventoryService.getAllProducts());
    }

    @GetMapping("/{name}/stock")
    public ResponseEntity<Integer> getProductStock(@PathVariable String name) {
        int stock = inventoryService.getStock(name);
        if (stock == -1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stock);
    }
}