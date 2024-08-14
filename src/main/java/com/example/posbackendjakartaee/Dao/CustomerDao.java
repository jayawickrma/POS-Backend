package com.example.posbackendjakartaee.Dao;

import com.example.posbackendjakartaee.DTO.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerDao {
    CustomerDTO getCustomer(String CID, Connection connection);
    boolean saveCustomer(CustomerDTO customerDTO,Connection connection);
    boolean deleteCustomer(String CID,Connection connection) throws SQLException;
    boolean updateCustomer(String CID,CustomerDTO customerDTO,Connection connection);
}
