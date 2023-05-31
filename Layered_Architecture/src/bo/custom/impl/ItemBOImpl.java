package bo.custom.impl;

import bo.custom.ItemBO;
import dao.CrudDAO;
import dao.DAOFactory;
import entity.Item;
import dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    CrudDAO<Item,String> item = (CrudDAO<Item, String>) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items=item.getAll();
        ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
        for (int i = 0; i < items.size() ; i++) {
            itemDTOS.add(new ItemDTO(items.get(i).getCode(),items.get(i).getDescription(),items.get(i).getUnitPrice(),items.get(i).getQtyOnHand()));
        }
        return itemDTOS;
    }

    @Override
    public boolean saveItem(ItemDTO data) throws SQLException, ClassNotFoundException {
        return item.save(new Item(data.getCode(),data.getDescription(),data.getUnitPrice(),data.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO data) throws SQLException, ClassNotFoundException {
        return item.update(new Item(data.getCode(),data.getDescription(),data.getUnitPrice(),data.getQtyOnHand()));
    }

    @Override
    public boolean exitsItem(String id) throws SQLException, ClassNotFoundException {
        return item.exits(id);
    }

    @Override
    public void deleteItem(String id) throws SQLException, ClassNotFoundException {
        item.delete(id);
    }

    @Override
    public String generateNewItemId() throws SQLException, ClassNotFoundException {
        return item.generateNewId();
    }

    @Override
    public ItemDTO searchItem(String Value) throws SQLException, ClassNotFoundException {
        Item items=item.search(Value);
        return new ItemDTO(items.getCode(),items.getDescription(),items.getUnitPrice(),items.getQtyOnHand());
    }
}
