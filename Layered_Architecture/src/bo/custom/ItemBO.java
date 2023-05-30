package bo.custom;

import bo.SuperBO;
import model.CustomerDTO;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {

    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    public boolean saveItem(ItemDTO data) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDTO data) throws SQLException, ClassNotFoundException;

    public boolean exitsItem(String id) throws SQLException, ClassNotFoundException;

    public void deleteItem(String id) throws SQLException, ClassNotFoundException;

    public String generateNewItemId() throws SQLException, ClassNotFoundException;

    public ItemDTO searchItem(String Value) throws SQLException, ClassNotFoundException;

}
