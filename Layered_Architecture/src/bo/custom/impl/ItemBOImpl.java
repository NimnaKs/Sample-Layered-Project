package bo.custom.impl;

import bo.custom.ItemBO;
import dao.CrudDAO;
import dao.Custom.impl.ItemDAOImpl;
import dao.DAOFactory;
import model.CustomerDTO;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    CrudDAO<ItemDTO,String> itemDAO = (CrudDAO<ItemDTO, String>) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

    @Override
    public boolean saveItem(ItemDTO data) throws SQLException, ClassNotFoundException {
        return itemDAO.save(data);
    }

    @Override
    public boolean updateItem(ItemDTO data) throws SQLException, ClassNotFoundException {
        return itemDAO.update(data);
    }

    @Override
    public boolean exitsItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exits(id);
    }

    @Override
    public void deleteItem(String id) throws SQLException, ClassNotFoundException {
        itemDAO.delete(id);
    }

    @Override
    public String generateNewItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }

    @Override
    public ItemDTO searchItem(String Value) throws SQLException, ClassNotFoundException {
        return itemDAO.search(Value);
    }
}
