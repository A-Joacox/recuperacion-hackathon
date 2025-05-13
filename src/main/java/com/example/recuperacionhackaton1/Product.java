package com.example.recuperacionhackaton1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer stock;

    public Product(String name, Integer stock) {
        this.name = name;
        this.stock = stock;
    }

    public ProductDTO toDTO() {
        return new ProductDTO(this.id, this.name, this.stock);
    }
}