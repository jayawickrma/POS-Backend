package com.example.posbackendjakartaee.Dao.IMPL;

import com.example.posbackendjakartaee.DTO.CustomerDTO;
import com.example.posbackendjakartaee.Dao.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAOIMPL implements CustomerDao {
        static String save_customer="INSERT INTO customer VALUES (?,?,?,?)";

        @Override
        public CustomerDTO getCustomer(String CID, Connection connection) {
                return null;
        }

        public boolean saveCustomer(CustomerDTO customerDTO, Connection connection){
                try {
                        PreparedStatement preparedStatement=connection.prepareStatement(save_customer);
                        preparedStatement.setString(1,customerDTO.getId());
                        preparedStatement.setString(2,customerDTO.getName());
                        preparedStatement.setString(3,customerDTO.getAddress());
                        preparedStatement.setString(4,customerDTO.getMobile());
                                return preparedStatement.executeUpdate()!=0;
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return false;
        }

        @Override
        public boolean deleteCustomer(String CID, Connection connection) {
                return false;
        }

        @Override
        public boolean updateCustomer(String CID, CustomerDTO customerDTO, Connection connection) {
                return false;
        }
}
