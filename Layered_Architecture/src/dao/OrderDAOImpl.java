package dao;

import db.DBConnection;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements CrudDAO<OrderDTO,String> {
    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDTO data) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",data.getOrderId(),data.getOrderDate(),data.getCustomerId());
    }

    @Override
    public boolean update(OrderDTO data) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exits(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",s);
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public OrderDTO search(String Value) throws SQLException, ClassNotFoundException {
        return null;
    }

//    @Override
//    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
//
//        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
//        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
//    }
//
//    @Override
//    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) {
//        Connection connection = null;
//        try {
//
//            /*if order id already exist*/
//            if (existsOrderId(orderId)) {
//
//            }
//
//            connection.setAutoCommit(false);
//
//            if (saveOrder(orderId, orderDate, customerId)) {
//                connection.rollback();
//                connection.setAutoCommit(true);
//                return false;
//            }
//
//
//            if (saveOrderDetails(orderDetails, orderId)) {
//                connection.rollback();
//                connection.setAutoCommit(true);
//                return false;
//            }
//
//            connection.commit();
//            connection.setAutoCommit(true);
//            return true;
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return false;
//
//    }
//
//    public boolean saveOrderDetails(List<OrderDetailDTO> orderDetails,String orderId) throws SQLException, ClassNotFoundException {
//
//        Connection connection = DBConnection.getDbConnection().getConnection();
//
//        for (OrderDetailDTO detail : orderDetails) {
//            if (SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)", orderId, detail.getItemCode(), detail.getUnitPrice(), detail.getQty())) {
//                return false;
//            }
//
//            ItemDTO item = findItem(detail.getItemCode());
//            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());
//
//            ItemDAOImpl itemDAO = new ItemDAOImpl();
//
//            if (!itemDAO.updateItem(item)) {
//                connection.rollback();
//                connection.setAutoCommit(true);
//                return false;
//            }
//
//
//        }
//        return true;
//    }
//
//    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
//
//        return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",orderId,Date.valueOf(orderDate),customerId);
//
//    }
//
//    public boolean existsOrderId(String orderId) throws SQLException, ClassNotFoundException {
//
//        return SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);
//
//    }
//
//    public ItemDTO findItem(String code) {
//        try {
//            ItemDAOImpl itemDAO = new ItemDAOImpl();
//            return itemDAO.findItem(code);
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to find the Item " + code, e);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
