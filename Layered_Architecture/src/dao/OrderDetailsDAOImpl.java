package dao;

import model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements CrudDAO<OrderDetailDTO,String> {

    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetailDTO data) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)", data.getOrderId(), data.getItemCode(), data.getUnitPrice(), data.getQty());
    }

    @Override
    public boolean update(OrderDetailDTO data) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exits(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetailDTO search(String Value) throws SQLException, ClassNotFoundException {
        return null;
    }
}
