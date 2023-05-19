package com.example.model;

import java.util.List;

public class ClientTableModel extends AbstractEntityTableModel<Client> {
    public ClientTableModel(List<Client> clients) {
        super(clients);
    }

    
    /** 
     * @return Class object
     */
    @Override
    protected Class<?> getEntityClass() {
        return Client.class;
    }
}
