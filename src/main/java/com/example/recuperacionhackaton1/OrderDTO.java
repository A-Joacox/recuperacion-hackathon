package com.example.recuperacionhackaton1;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String email;
    private List<String> productos;
}
