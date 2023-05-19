package com.example.model;

import java.util.List;

public class ClientTableModel extends AbstractEntityTableModel<Client> {
    public ClientTableModel(List<Client> clients) {
        super(clients);
    }

    
    /** 
     * @return Class<?>
     */
    @Override
    protected Class<?> getEntityClass() {
        return Client.class;
    }
}
