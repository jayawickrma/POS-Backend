package com.example.posbackendjakartaee.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO implements Serializable {
    @Id
    private String id;
    private String name;
    private String address;
    private String mobile;
}
