package com.example.bussinessLogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.bussinessLogic.validators.AgeValidator;
import com.example.bussinessLogic.validators.EmailValidator;
import com.example.bussinessLogic.validators.NameValidator;
import com.example.bussinessLogic.validators.Validator;
import com.example.dataAccess.ClientDAO;
import com.example.model.Client;

public class ClientBLL {

    private List<Validator<Client>> validators;
    ClientDAO clientDAO = new ClientDAO();

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new NameValidator());
        validators.add(new AgeValidator());
    }

    
    /** 
     * @param id
     * @return Client
     * @throws SQLException
     */
    public Client findClientById(int id) throws SQLException {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return cl;
    }

    
    /** 
     * @param client
     * @return int
     * @throws SQLException
     */
    public int insertClient(Client client) throws SQLException {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return clientDAO.insert(client);
    }

    
    /** 
     * @param client
     * @throws SQLException
     */
    public void updateClient(Client client) throws SQLException {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        clientDAO.update(client);
        return;
    }
}
