package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.CrudDAO;
import dao.DAOFactory;
import entity.Customer;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO{

    CrudDAO<Customer,String> customerDAO= (CrudDAO<Customer, String>) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers=customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS=new ArrayList<>();
        for (int i = 0; i < customers.size(); i++) {
            customerDTOS.add(new CustomerDTO(customers.get(i).getId(),customers.get(i).getName(),customers.get(i).getAddress()));
        }
        return customerDTOS;
    }

    @Override
    public boolean saveCustomer(CustomerDTO data) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(data.getId(),data.getName(),data.getAddress()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO data) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(data.getId(),data.getName(),data.getAddress()));
    }

    @Override
    public boolean existCustomer(String s) throws SQLException, ClassNotFoundException {
        return customerDAO.exits(s);
    }

    @Override
    public void deleteCustomer(String s) throws SQLException, ClassNotFoundException {
        customerDAO.delete(s);
    }

    @Override
    public String generateNewCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }

    @Override
    public CustomerDTO searchCustomer(String Value) throws SQLException, ClassNotFoundException {
        Customer customer=customerDAO.search(Value);
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
    }
}
