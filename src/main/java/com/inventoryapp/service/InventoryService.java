package com.inventoryapp.service;

import com.inventoryapp.database.ProductDAO;
import com.inventoryapp.model.Product;

import java.util.List;

public class InventoryService {
    private ProductDAO productDAO;

    public InventoryService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void addProduct(Product product) {
        // Additional business logic (e.g., validation) can go here
        productDAO.addProduct(product);
    }

    public Product getProduct(int id) {
        return productDAO.getProduct(id);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }
}
