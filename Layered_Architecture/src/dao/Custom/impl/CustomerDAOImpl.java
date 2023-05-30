package dao.Custom.impl;

import dao.Custom.CustomerDAO;
import dao.SQLUtil;
import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");

        ArrayList<CustomerDTO> customerDataList = new ArrayList<>();

        while (rst.next()) {
            customerDataList.add(new CustomerDTO(rst.getString(1), rst.getString(2), rst.getString(3)));
        }

        return customerDataList;

    }

    @Override
    public boolean save(CustomerDTO data) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",data.getId(),data.getName(),data.getAddress());
    }

    @Override
    public boolean update(CustomerDTO data) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",data.getName(),data.getAddress(),data.getId());
    }

    @Override
    public boolean exits(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT id FROM Customer WHERE id=?",s);
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM Customer WHERE id=?",s);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public CustomerDTO search(String Value) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?");
        rst.next();
        CustomerDTO customerDTO = new CustomerDTO(Value + "", rst.getString("name"), rst.getString("address"));
        return customerDTO;
    }

//    @Override
//    public  ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
//
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
//
//        ArrayList<CustomerDTO> customerDataList=new ArrayList<>();
//
//        while (rst.next()){
//            customerDataList.add(new CustomerDTO(rst.getString(1),rst.getString(2),rst.getString(3)));
//        }
//
//        return customerDataList;
//    }
//
//    @Override
//    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
//
//        return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
//
//
//    }
//
//    @Override
//    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
//
//        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",customerDTO.getName(),customerDTO.getAddress(),customerDTO.getId());
//
//    }
//
//    @Override
//    public boolean exitsCustomer(String id) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute("SELECT id FROM Customer WHERE id=?");
//
//    }
//
//    @Override
//    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException {
//        SQLUtil.execute("DELETE FROM Customer WHERE id=?",id);
//    }
//
//    @Override
//    public String generateNewId() throws SQLException, ClassNotFoundException {
//
//        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
//        if (rst.next()) {
//            String id = rst.getString("id");
//            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
//            return String.format("C00-%03d", newCustomerId);
//        } else {
//            return "C00-001";
//        }
//    }
//
//    @Override
//    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException {
//
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?");
//        rst.next();
//        CustomerDTO customerDTO = new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));
//        return customerDTO;
//
//    }
}
