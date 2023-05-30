package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.CrudDAO;
import dao.Custom.impl.CustomerDAOImpl;
import dao.DAOFactory;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO{

    CrudDAO<CustomerDTO,String> customerDTO= (CrudDAO<CustomerDTO, String>) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return customerDTO.getAll();
    }

    @Override
    public boolean saveCustomer(CustomerDTO data) throws SQLException, ClassNotFoundException {
        return customerDTO.save(data);
    }

    @Override
    public boolean updateCustomer(CustomerDTO data) throws SQLException, ClassNotFoundException {
        return customerDTO.update(data);
    }

    @Override
    public boolean existCustomer(String s) throws SQLException, ClassNotFoundException {
        return customerDTO.exits(s);
    }

    @Override
    public void deleteCustomer(String s) throws SQLException, ClassNotFoundException {
        customerDTO.delete(s);
    }

    @Override
    public String generateNewCustomerId() throws SQLException, ClassNotFoundException {
        return customerDTO.generateNewId();
    }

    @Override
    public CustomerDTO searchCustomer(String Value) throws SQLException, ClassNotFoundException {
        return customerDTO.search(Value);
    }
}
