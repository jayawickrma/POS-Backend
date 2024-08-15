package com.example.posbackendjakartaee.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO {
    private String code;
    private String description;
    private String qty;
    private String price;
}
