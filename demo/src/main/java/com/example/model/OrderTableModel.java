package com.example.model;

import java.util.List;

public class OrderTableModel extends AbstractEntityTableModel<Order> {
    public OrderTableModel(List<Order> orders) {
        super(orders);
    }

    
    /** 
     * @return Class order
     */
    @Override
    protected Class<?> getEntityClass() {
        return Order.class;
    }
}
