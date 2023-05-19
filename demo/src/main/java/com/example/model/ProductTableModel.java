package com.example.model;

import java.util.List;

public class ProductTableModel extends AbstractEntityTableModel<Product> {
    public ProductTableModel(List<Product> products) {
        super(products);
    }

    
    /** 
     * @return Class product
     * 
     */
    @Override
    protected Class<?> getEntityClass() {
        return Product.class;
    }
}
