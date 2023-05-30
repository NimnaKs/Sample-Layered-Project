package bo.custom.impl;

import bo.custom.PurchaseOrderBO;
import dao.CrudDAO;
import dao.Custom.impl.OrderDAOImpl;
import dao.Custom.impl.OrderDetailsDAOImpl;
import db.DBConnection;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    CrudDAO<OrderDTO,String> orderDAO=new OrderDAOImpl();

    CrudDAO<OrderDetailDTO,String> orderDetailsDAO=new OrderDetailsDAOImpl();

    ItemBOImpl itemBO=new ItemBOImpl();

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    @Override
    public boolean exitsOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.exits(id);
    }

    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;

        try {
            connection = DBConnection.getDbConnection().getConnection();
//            PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
//            stm.setString(1, orderId);
            /*if order id already exist*/
            if (exitsOrder(orderId)) {
                return false;
            }
//            connection.setAutoCommit(false);
//            stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
//            stm.setString(1, orderId);
//            stm.setDate(2, Date.valueOf(orderDate));
//            stm.setString(3, customerId);
            if (!saveOrder(new OrderDTO(orderId, Date.valueOf(orderDate), customerId))) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO detail : orderDetails) {
                if (saveOrder(new OrderDetailDTO(orderId, detail.getItemCode(), detail.getQty(), detail.getUnitPrice()))) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                ItemDTO itemDTO = findItem(detail.getItemCode());
                itemDTO.setQtyOnHand(itemDTO.getQtyOnHand() - detail.getQty());
                if (!(itemBO.updateItem(itemDTO))) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveOrder(OrderDTO data) throws SQLException, ClassNotFoundException {
        return orderDAO.save(data);
    }

    @Override
    public boolean saveOrder(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.save(orderDetailDTO);
    }

    @Override
    public ItemDTO findItem(String itemCode) {
        try {
            return itemBO.searchItem(itemCode);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
