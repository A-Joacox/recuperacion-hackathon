package com.example.recuperacionhackaton1;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init() {
        if (productRepository.count() == 0) {
            log.info("Inicializando productos de ejemplo en la base de datos");
            productRepository.save(new Product("Producto1", 100));
            productRepository.save(new Product("Producto2", 50));
            productRepository.save(new Product("Producto3", 75));
        }
    }

    @Transactional
    public Map<String, Integer> reduceStock(List<String> productos) {
        Map<String, Integer> updatedStock = new HashMap<>();

        for (String productoName : productos) {
            Optional<Product> productOpt = productRepository.findByName(productoName);

            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                int currentStock = product.getStock();

                if (currentStock > 0) {
                    // Reducir el stock en 1 unidad
                    product.setStock(currentStock - 1);
                    product = productRepository.save(product);
                    updatedStock.put(productoName, product.getStock());
                    log.info("Stock reducido para {}: {} -> {}", productoName, currentStock, product.getStock());
                } else {
                    log.warn("No hay stock disponible para: {}", productoName);
                    updatedStock.put(productoName, 0);
                }
            } else {
                log.warn("Producto no encontrado en inventario: {}", productoName);
                updatedStock.put(productoName, -1); // -1 indica que el producto no existe
            }
        }

        return updatedStock;
    }

    public int getStock(String productoName) {
        Optional<Product> product = productRepository.findByName(productoName);
        return product.map(Product::getStock).orElse(-1);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(Product::toDTO)
                .collect(Collectors.toList());
    }
}