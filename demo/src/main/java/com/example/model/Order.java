package com.example.model;

public class Order {
    private int id;

    
    /** 
     * @return int
     */
    public int getOrderId() {
        return id;
    }

    
    /** 
     * @param orderId
     */
    public void setOrderId(int orderId) {
        this.id = orderId;
    }

    private int clientId;

    
    /** 
     * @return int
     */
    public int getClientId() {
        return clientId;
    }

    
    /** 
     * @param clientId
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    private int productId;

    
    /** 
     * @return int
     */
    public int getProductId() {
        return productId;
    }

    
    /** 
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    private int quantity;

    
    /** 
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    
    /** 
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order(int clientId, int productId, int quantity) {
        super();
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Order(int orderId, int clientId, int productId, int quantity) {
        super();
        this.id = orderId;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
