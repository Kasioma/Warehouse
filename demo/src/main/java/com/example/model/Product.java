package com.example.model;

public class Product {
    private int id;

    
    /** 
     * @return int
     */
    public int getProductId() {
        return id;
    }

    
    /** 
     * @param productId
     */
    public void setProductId(int productId) {
        this.id = productId;
    }

    private String name;

    
    /** 
     * @return String
     */
    public String getProductName() {
        return name;
    }

    
    /** 
     * @param productName
     */
    public void setProductName(String productName) {
        this.name = productName;
    }

    private int stock;

    
    /** 
     * @return int
     */
    public int getStock() {
        return stock;
    }

    
    /** 
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    public Product(int id, String name, int stock) {
        super();
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public Product(String name, int stock) {
        super();
        this.name = name;
        this.stock = stock;
    }
}
