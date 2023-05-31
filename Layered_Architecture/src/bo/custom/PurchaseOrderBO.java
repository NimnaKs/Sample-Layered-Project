package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface PurchaseOrderBO extends SuperBO {
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
    boolean exitsOrder(String id) throws SQLException, ClassNotFoundException;
    boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
    boolean saveOrder(OrderDTO data) throws SQLException, ClassNotFoundException;
    boolean saveOrder(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException;
    ItemDTO findItem(String itemCode);
}
