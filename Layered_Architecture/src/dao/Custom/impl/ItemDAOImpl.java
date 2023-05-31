package dao.Custom.impl;

import dao.CrudDAO;
import dao.SQLUtil;
import entity.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements CrudDAO<Item,String> {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()){
            items.add(new Item(rst.getString(1),rst.getString(2),rst.getBigDecimal(3),rst.getInt(4)));
        }
        return items;
    }

    @Override
    public boolean save(Item data) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",data.getCode(),data.getDescription(),data.getUnitPrice(),data.getQtyOnHand());
    }

    @Override
    public boolean update(Item data) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",data.getDescription(),data.getUnitPrice(),data.getQtyOnHand(),data.getCode());
    }

    @Override
    public boolean exits(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT code FROM Item WHERE code=?",s);
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM Item WHERE code=?");
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public Item search(String Value) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?",Value);
        rst.next();
        Item item = new Item(Value + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        return item;
    }

//    @Override
//    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
//
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
//        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
//        while (rst.next()){
//            itemDTOS.add(new ItemDTO(rst.getString(1),rst.getString(2),rst.getBigDecimal(3),rst.getInt(4)));
//        }
//        return itemDTOS;
//    }
//
//    @Override
//    public void deleteItems(String code) throws SQLException, ClassNotFoundException {
//
//        SQLUtil.execute("DELETE FROM Item WHERE code=?");
//
//    }
//
//    @Override
//    public void saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
//
//        SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand());
//
//    }
//
//    @Override
//    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
//
//        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand(),itemDTO.getCode());
//
//    }
//
//    @Override
//    public boolean exitsItem(String code) throws SQLException, ClassNotFoundException {
//
//        return SQLUtil.execute("SELECT code FROM Item WHERE code=?");
//
//    }
//
//    @Override
//    public String generateNewId() throws SQLException, ClassNotFoundException {
//
//        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
//        if (rst.next()) {
//            String id = rst.getString("code");
//            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
//            return String.format("I00-%03d", newItemId);
//        } else {
//            return "I00-001";
//        }
//    }
//
//    @Override
//    public ItemDTO findItem(String code) throws SQLException, ClassNotFoundException {
//
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?",code);
//        rst.next();
//        return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
//    }
//
//    @Override
//    public ItemDTO exitsItems(String newItemCode) throws SQLException, ClassNotFoundException {
//
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?",newItemCode);
//        rst.next();
//        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
//        return item;
//    }


}
