package com.example.posbackendjakartaee.Dao;

import com.example.posbackendjakartaee.DTO.CustomerDTO;

import java.sql.Connection;

public interface CustomerDao {
    CustomerDTO getCustomer(String CID, Connection connection);
    boolean saveCustomer(CustomerDTO customerDTO,Connection connection);
    boolean deleteCustomer(String CID,Connection connection);
    boolean updateCustomer(String CID,CustomerDTO customerDTO,Connection connection);
}
