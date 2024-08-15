package com.example.posbackendjakartaee.Dao;

import com.example.posbackendjakartaee.DTO.ItemDTO;

import java.sql.Connection;

public interface ItemDao {
    ItemDTO getItem(String code , Connection connection);
    boolean saveItem(ItemDTO itemDTO,Connection connection);
    boolean updateItem(String code,ItemDTO itemDTO,Connection connection);
    boolean deleteItem(String code,Connection connection);
}
