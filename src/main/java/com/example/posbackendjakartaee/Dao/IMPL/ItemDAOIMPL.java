package com.example.posbackendjakartaee.Dao.IMPL;

import com.example.posbackendjakartaee.DTO.ItemDTO;
import com.example.posbackendjakartaee.Dao.ItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDAOIMPL implements ItemDao {

    static String save_Item="INSERT INTO item VALUES(?,?,?,?)";
    static String delete_item="DELETE FROM item WHERE code=?";
    static String update_item="UPDATE item SET description=?,qty=?,price=? WHERE code=?";
    @Override
    public ItemDTO getItem(String code, Connection connection) {
        return null;
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO, Connection connection) {
      try{
          PreparedStatement preparedStatement=connection.prepareStatement(save_Item);
            preparedStatement.setString(1,itemDTO.getCode());
            preparedStatement.setString(2,itemDTO.getDescription());
            preparedStatement.setString(3,itemDTO.getQty());
            preparedStatement.setString(4,itemDTO.getPrice());
           return preparedStatement.executeUpdate()!=0;
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
    }

    @Override
    public boolean updateItem(String code, ItemDTO itemDTO, Connection connection) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(update_item);
                preparedStatement.setString(1,itemDTO.getDescription());
                preparedStatement.setString(2,itemDTO.getQty());
                preparedStatement.setString(3,itemDTO.getPrice());
                preparedStatement.setString(4,code);
                return preparedStatement.executeUpdate()!=0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteItem(String code, Connection connection) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(delete_item);
            preparedStatement.setString(1,code);
            return preparedStatement.executeUpdate()!=0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
